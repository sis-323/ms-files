package com.files.msfiles.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "propuestas")
class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propuesta")
    var proposalId: Long? = null

    @ManyToOne
    @JoinColumn(name = "id_archivo")
    var fileId: File? = null

    @Column(name = "titulo")
    var description: String? = null

    @Column(name = "estado")
    var status: Boolean = true

    @OneToOne
    @JoinColumn(name = "id_persona")
    var person: Person? = null




}