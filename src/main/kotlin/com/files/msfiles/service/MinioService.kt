package com.files.msfiles.service

import com.files.msfiles.dto.FileDto
import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.UUID

@Service
class MinioService constructor(
        private val minioClient: MinioClient
) {
    @Value("\${minio.bucket-name}")
    private var bucket: String? = null
    @Value("\${minio.url}")
    private var minioUrl: String? = null

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MinioService::class.java)
    }
    fun uploadFile(file: MultipartFile): FileDto? {
        logger.info("Uploading file to minio")
        verifyBucket()
        val cleanedFileName = file.originalFilename?.replace("\\s".toRegex(), "")
        val fileName = "${cleanedFileName}-${UUID.randomUUID()}.${file.originalFilename?.split(".")?.get(1)}"
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .`object`(fileName)
                        .stream(file.inputStream, file.size, -1)
                        .contentType(file.contentType)
                        .build()
        )
        logger.info("File uploaded successfully")
        val fileDto = FileDto(
                fileName = fileName,
                md5 = "md5"
        )
        return fileDto
    }


    fun verifyBucket() {
        if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucket)
                            .build()
            )
        }
    }

}