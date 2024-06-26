package com.files.msfiles.bl

import com.files.msfiles.dao.FileRepository
import com.files.msfiles.dto.DeliverableDto
import com.files.msfiles.dto.FileDto
import com.files.msfiles.entity.DeliverableFile
import com.files.msfiles.entity.File
import com.files.msfiles.service.EnrollmentService
import com.files.msfiles.service.MinioService
import com.netflix.discovery.converters.Auto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.text.SimpleDateFormat
import java.util.*

@Service
class DeliverableFileBl (
    @Autowired private val fileRepository: FileRepository,
    @Autowired private val minioService: MinioService,
    @Autowired private val enrollmentService: EnrollmentService
) {
    fun uploadDeliverable(
        file: MultipartFile,
        title: String,
        dueDate: String,
        description: String
    ) {
        val fileDto = minioService.uploadFile(file)
        val fileEntity = File(
            fileName = fileDto?.fileName!!,
            md5 = fileDto.md5
        )
        val savedFile = fileRepository.save(fileEntity)



        val deliverableDto = DeliverableDto(
            title = title,
            dueDate = dueDate,
            description = description
        )

        enrollmentService.createDeliverable(deliverableDto, savedFile.fileId!!)

    }

    fun uploadStudentDeliverable(studentKcId: String,
        file: MultipartFile,
        deliverableId: String
    ) : FileDto{
        val deliverableIdLong = deliverableId.toLong()
        val fileDto = minioService.uploadFile(file)
        val fileEntity = File(
            fileName = fileDto?.fileName!!,
            md5 = fileDto.md5
        )
        val savedFile = fileRepository.save(fileEntity)
        val fileDto2 = FileDto(
            fileId = savedFile.fileId!!,
            fileName = fileDto.fileName,
            md5 = fileDto.md5
        )
        enrollmentService.uploadStudentDeliverable(studentKcId, deliverableIdLong, fileDto2)
        return fileDto
    }

}