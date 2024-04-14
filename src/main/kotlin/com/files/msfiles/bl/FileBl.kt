package com.files.msfiles.bl

import com.files.msfiles.dao.FileRepository
import com.files.msfiles.dto.FileDto
import com.files.msfiles.dto.ProposalDto
import com.files.msfiles.dto.ResponseDto
import com.files.msfiles.dto.SaveProposalDto
import com.files.msfiles.entity.File
import com.files.msfiles.service.EnrollmentService
import com.files.msfiles.service.MinioService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import kotlin.math.log

@Service
class FileBl constructor(
        var minioService: MinioService,
        val fileRepository: FileRepository,
        val enrollmentService: EnrollmentService
) {

    companion object{
        val logger: Logger = LoggerFactory.getLogger(FileBl::class.java)
    }
    fun uploadProposal(files: List<MultipartFile>, proposalFile: MultipartFile,
                       proposalTitle: String, personKcUuid: String) {
        val requirements = mutableListOf<FileDto>()
        val requirementsOut = mutableListOf<FileDto>()

        val proposalFileDto1 = minioService.uploadFile(proposalFile)
        val fileAux1 = File(
                fileName = proposalFileDto1?.fileName!!,
                md5 = proposalFileDto1.md5
        )
        val proposalFileEntity = fileRepository.save(fileAux1)


        val proposalDto = ProposalDto(
                fileId = proposalFileEntity.fileId!!,
                title = proposalTitle,
                personKcUuid = personKcUuid
        )


        var fileAux = File(
                fileName = "",
                md5 = ""
        )
        for (file in files) {
            val fileDto = minioService.uploadFile(file)
            requirements.add(fileDto!!)
        }

        for (file in requirements) {
            val fileEntity = File(
                    fileName = file.fileName,
                    md5 = file.md5
            )
            fileAux = fileRepository.save(fileEntity)
            val requirement = FileDto(
                    fileId = fileAux.fileId,
                    fileName = fileAux.fileName!!,
                    md5 = fileAux.md5!!
            )
            requirementsOut.add(requirement)

        }


        val proposalFileDto = FileDto(
                fileId = proposalFileEntity.fileId,
                fileName = proposalFileEntity.fileName!!,
                md5 = proposalFileEntity.md5!!
        )



        val requestDto = SaveProposalDto(
                fileDto = proposalFileDto,
                proposalDto = proposalDto,
                requirements = requirementsOut
        )

        logger.info("proposal: ${requestDto.fileDto}")
        logger.info("requirements: ${requestDto.requirements}")


        enrollmentService.saveProposal(requestDto)


    }







}