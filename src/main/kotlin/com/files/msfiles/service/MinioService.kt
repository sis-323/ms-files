package com.files.msfiles.service

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class MinioService constructor(
        private val minioClient: MinioClient
) {
    @Value("\${minio.bucket-name}")
    private var bucket: String? = null
    @Value("\${minio.url}")
    private var minioUrl: String? = null

    fun uploadFile(file: MultipartFile): String? {
        return file.originalFilename

    }

}