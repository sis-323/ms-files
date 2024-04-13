package com.files.msfiles.api

import com.files.msfiles.bl.FileBl
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/files")

class FileApi constructor(
        private val fileBl: FileBl
){

    @PostMapping("/upload")
    fun uploadFile(
            @RequestParam("files") files: List<MultipartFile>
    ): List<String>
    {
        return fileBl.uploadFile(files)
    }

}