package com.mxninja.courtMicroServices.courtService.adapters.respositories

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * 9/3/2018
 * @author Mohammad Ali
 */
interface CourtTypesDAO : MongoRepository<CourtTypeProjection, String>