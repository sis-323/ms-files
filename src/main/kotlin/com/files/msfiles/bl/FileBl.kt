package com.files.msfiles.bl

import com.files.msfiles.service.MinioService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileBl constructor(
        var minioService: MinioService
) {
    fun uploadFile(files: List<MultipartFile>): List<String> {
        val fileNames = mutableListOf<String>()
        for (file in files) {
            fileNames.add(minioService.uploadFile(file)!!)
        }
        return fileNames
    }
}