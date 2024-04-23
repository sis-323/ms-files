package com.files.msfiles.api

import com.files.msfiles.bl.DeliverableFileBl
import com.files.msfiles.bl.FileBl
import com.files.msfiles.dto.ResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/files")

class FileApi constructor(
        private val fileBl: FileBl,
    private val deliverableFileBl: DeliverableFileBl
){

    @PostMapping("/upload")
    fun uploadFile(
            @RequestParam("requirements") requirements: List<MultipartFile>,
            @RequestParam("proposalFile") proposalFile: MultipartFile,
            @RequestParam("proposal") proposalTitle: String,
            @RequestParam("kcId") personKcUuid: String

    ): String
    {
        fileBl.uploadProposal(
                requirements,
                proposalFile,
                proposalTitle,
                personKcUuid
        )
        return "Files uploaded successfully"
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




}