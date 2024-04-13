package com.files.msfiles.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "archivo")
class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archivo")
    var fileId: Long? = null

    @Column(name = "nombre_archivo")
    var fileName: String? = null

    @Column(name = "md5")
    var md5: String? = null

    @Column(name = "fecha_subido")
    var uploadDate: Date? = null

}