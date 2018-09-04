package com.mxninja.courtMicroServices.courtService.domains

import java.time.LocalTime

/**
 * 9/4/2018
 * @author Mohammad Ali
 */
class CourtDTO {

    var id: String? = null
    var nameAr: String? = null
    var nameEn: String? = null
    var openTime: LocalTime? = null
    var closeTime: LocalTime? = null

}