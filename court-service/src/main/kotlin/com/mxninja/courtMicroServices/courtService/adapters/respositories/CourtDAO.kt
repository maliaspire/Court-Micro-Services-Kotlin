package com.mxninja.courtMicroServices.courtService.adapters.respositories

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * 9/4/2018
 * @author Mohammad Ali
 */
interface CourtDAO : MongoRepository<CourtProjection, String>