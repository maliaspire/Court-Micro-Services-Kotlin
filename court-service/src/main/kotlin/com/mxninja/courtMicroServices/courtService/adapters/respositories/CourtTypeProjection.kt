package com.mxninja.courtMicroServices.courtService.adapters.respositories

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@Document(collection = "CourtTypes")
class CourtTypeProjection(
        @Id
        @GeneratedValue
        var id: String?,
        var nameAr: String,
        var nameEn: String,
        var imageUrl: String?
) {
    constructor() : this("", "", "", "")
}