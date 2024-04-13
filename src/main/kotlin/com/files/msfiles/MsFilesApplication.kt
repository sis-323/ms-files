package com.files.msfiles

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = ["com.files.*"])
class MsFilesApplication

fun main(args: Array<String>) {
	runApplication<MsFilesApplication>(*args)
}
