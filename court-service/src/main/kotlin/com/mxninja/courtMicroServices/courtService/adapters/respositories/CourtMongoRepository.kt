package com.mxninja.courtMicroServices.courtService.adapters.respositories

import org.springframework.stereotype.Component

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@Component
class CourtMongoRepository {

    fun findAll(): List<CourtProjection> {
        return listOf()
    }

}