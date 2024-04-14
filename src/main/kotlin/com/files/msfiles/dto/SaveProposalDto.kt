package com.files.msfiles.dto

data class SaveProposalDto (
        val fileDto: FileDto,
        val proposalDto: ProposalDto,
        val requirements : List<FileDto>

)