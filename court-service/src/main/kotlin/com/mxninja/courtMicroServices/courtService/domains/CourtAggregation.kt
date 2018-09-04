package com.mxninja.courtMicroServices.courtService.domains

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalTime

/**
 * 9/4/2018
 * @author Mohammad Ali
 */
data class CourtAggregation(
        @JsonIgnore
        var id: String?,
        var nameAr: String,
        var nameEn: String,
        var courtTypeId: String,
        var size: Int?,
        var openTime: LocalTime?,
        var closeTime: LocalTime?
) {
    constructor() : this(null, "", "", "", null, null, null)
}