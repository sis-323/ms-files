package com.files.msfiles.api

import com.files.msfiles.bl.DeliverableFileBl
import com.files.msfiles.bl.FileBl
import com.files.msfiles.dto.ResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin(origins = ["*"])
class FileApi constructor(
        private val fileBl: FileBl,
    private val deliverableFileBl: DeliverableFileBl
){

    @PostMapping("/upload")
    fun uploadFile(
            @RequestParam("requirements") requirements: List<MultipartFile>,
            @RequestParam("proposalFile") proposalFile: MultipartFile,
            @RequestParam("proposal") proposalTitle: String,
            @RequestParam("kcId") personKcUuid: String): ResponseEntity<ResponseDto<String>>
    {
        fileBl.uploadProposal(
                requirements,
                proposalFile,
                proposalTitle,
                personKcUuid
        )
        return ResponseEntity.ok(ResponseDto(null,
                "La propuesta se ha subido correctamente",
                true))
    }

    @PostMapping("/deliverable")
    fun uploadDeliverable(
            @RequestParam("file") file: MultipartFile,
    @RequestParam("title") title: String,
    @RequestParam("dueDate") dueDate: String,
    @RequestParam("description") description: String
    ): ResponseEntity<ResponseDto<String>> {
        deliverableFileBl.uploadDeliverable(
                file,
                title,
                dueDate,
                description
        )

        return ResponseEntity.ok(ResponseDto(null,
                "Deliverable created successfully",
                true))
    }

    @GetMapping("/url")
    fun getFileUrl(@RequestParam("fileName") fileName: String): ResponseEntity<ResponseDto<String>>{
        val url = fileBl.findFileUrl(fileName)
        return ResponseEntity.ok(ResponseDto(url, "File url retrieved successfully", true))
    }

    @PostMapping("/upload/student/deliverable")
    fun uploadStudentDeliverable(
            @RequestParam("file") file: MultipartFile,
            @RequestParam("kcId") kcId: String,
            @RequestParam("deliverableId") deliverableId: Long
    ): ResponseEntity<ResponseDto<String>> {
        deliverableFileBl.uploadStudentDeliverable(kcId,file,deliverableId)
        return ResponseEntity.ok(ResponseDto(null,
                "Deliverable uploaded successfully",
                true))
    }



}
