package com.files.msfiles.service

import com.files.msfiles.dto.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "ms-enrollment")
interface EnrollmentService {

    @PostMapping("/api/v1/enrollments/")
    fun saveProposal(@RequestBody requestDto: SaveProposalDto): ResponseEntity<ResponseDto<String>>

    @PostMapping("/api/v1/deliverable/{fileId}")
    fun createDeliverable(@RequestBody deliverableDto: DeliverableDto, @PathVariable fileId: Long):
            ResponseEntity<ResponseDto<String>>
}