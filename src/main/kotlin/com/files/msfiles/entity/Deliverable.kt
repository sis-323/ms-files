package com.files.msfiles.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "entregables_cronograma")
class Deliverable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cronograma")
    var deliverableId: Long? = null

    @Column(name = "titulo")
    var title: String? = null

    @Column(name = "fecha_limite")
    var dueDate: Date = Date()

    @Column(name = "descripcion")
    var description: String? = null

    @Column(name = "estado")
    var status: Boolean = true
}