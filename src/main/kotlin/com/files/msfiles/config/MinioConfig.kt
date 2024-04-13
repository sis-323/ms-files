package com.files.msfiles.config

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class MinioConfig {

    @Value("\${minio.url}")
    private var minioUrl: String? = null

    @Value("\${minio.access-key}")
    private var minioAccessKey: String? = null

    @Value("\${minio.secret-key}")
    private var minioSecretKey: String? = null

    @Bean
    @Primary
    fun minioClient(): MinioClient {
        return io.minio.MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(minioAccessKey, minioSecretKey)
            .build()
    }
}