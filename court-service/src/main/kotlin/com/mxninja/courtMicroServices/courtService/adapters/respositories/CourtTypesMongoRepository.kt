package com.mxninja.courtMicroServices.courtService.adapters.respositories

import com.mxninja.courtMicroServices.courtService.domains.CourtTypesAggregation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@Component
class CourtTypesMongoRepository @Autowired constructor(
        private val courtTypesDAO: CourtTypesDAO
) {

    fun findAll(): List<CourtTypeProjection> {
        return courtTypesDAO.findAll()
    }

    fun findById(id: String): Optional<CourtTypeProjection> {
        return courtTypesDAO.findById(id)
    }

    fun save(aggregate: CourtTypesAggregation?): CourtTypeProjection {
        return courtTypesDAO.save(convertToProjection(aggregate))
    }

    fun update(aggregate: CourtTypesAggregation?): CourtTypeProjection {
        return courtTypesDAO.save(convertToProjection(aggregate))
    }

    fun convertToProjection(aggregation: CourtTypesAggregation?): CourtTypeProjection {
        return CourtTypeProjection(
                aggregation!!.id,
                aggregation.nameAr,
                aggregation.nameEn,
                aggregation.imageUrl
        )
    }

}