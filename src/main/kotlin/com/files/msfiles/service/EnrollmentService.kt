package com.files.msfiles.service

import com.files.msfiles.dto.FileDto
import com.files.msfiles.dto.ProposalDto
import com.files.msfiles.dto.ResponseDto
import com.files.msfiles.dto.SaveProposalDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "ms-enrollment")
interface EnrollmentService {

    @PostMapping("/api/v1/enrollments/")
    fun saveProposal(@RequestBody requestDto: SaveProposalDto): ResponseEntity<ResponseDto<String>>


}