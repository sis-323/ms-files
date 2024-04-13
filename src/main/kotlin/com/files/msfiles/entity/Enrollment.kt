package com.files.msfiles.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "inscripcion")
class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    var enrollmentId: Long? = null

    @ManyToOne
    @JoinColumn(name = "id_persona")
    var studentId: Person? = null

    @ManyToOne
    @JoinColumn(name = "id_propuesta")
    var proposalId: Proposal? = null

    @Column(name = "estado")
    var status: Boolean = true

    @Column(name = "fecha_propuesta")
    var enrollmentDate: Date = Date()

    @Column(name = "semestre")
    var semester: String = ""
}