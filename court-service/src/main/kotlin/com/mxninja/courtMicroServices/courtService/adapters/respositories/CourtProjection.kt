package com.mxninja.courtMicroServices.courtService.adapters.respositories

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalTime
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * 9/3/2018
 * @author Mohammad Ali
 */


@Document(collection = "Courts")
data class CourtProjection(
        @Id
        @GeneratedValue
        var id: String?,
        var nameAr: String,
        var nameEn: String,
        @Indexed()
        var courtTypeId: ObjectId,
        var size: Int,
        var openTime: LocalTime,
        var closeTime: LocalTime
)