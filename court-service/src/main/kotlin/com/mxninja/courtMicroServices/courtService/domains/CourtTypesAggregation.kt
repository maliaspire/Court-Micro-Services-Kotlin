package com.mxninja.courtMicroServices.courtService.domains

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * 9/3/2018
 * @author Mohammad Ali
 */
data class CourtTypesAggregation(
        @JsonIgnore
        var id: String?,
        var nameAr: String,
        var nameEn: String,
        var imageUrl: String?
) {
    constructor() : this(null, "", "", null)
}