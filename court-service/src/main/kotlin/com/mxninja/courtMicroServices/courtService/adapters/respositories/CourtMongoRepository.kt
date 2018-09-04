package com.mxninja.courtMicroServices.courtService.adapters.respositories

import com.mongodb.DBObject
import com.mxninja.courtMicroServices.courtService.domains.CourtAggregation
import com.mxninja.courtMicroServices.courtService.domains.CourtDTO
import com.mxninja.courtMicroServices.courtService.domains.CourtTypesAggregation
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregate
import org.springframework.data.mongodb.core.aggregation.MatchOperation
import org.springframework.stereotype.Component
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.AggregationOperation
import org.springframework.data.mongodb.core.query.Criteria


/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@Component
class CourtMongoRepository @Autowired constructor(
        private val courtDAO: CourtDAO,
        private val mongoTemplate: MongoTemplate
) {

    fun findAll(): List<CourtProjection> {
        return listOf()
    }

    fun save(aggregation: CourtAggregation): CourtProjection {
        return courtDAO.save(convertToProjection(aggregation))
    }

    fun findAllByType(typeId: String): List<CourtDTO> {
        val matchOperation = Aggregation.match(Criteria("courtTypeId").`is`(typeId))
        val projection = Aggregation.project("_id", "nameAr", "nameEn")
        val aggregation = Aggregation.newAggregation(matchOperation, projection)
        val result = mongoTemplate.aggregate(aggregation, "Courts", CourtDTO::class.java)
        return result.mappedResults
    }

    fun countByType(): List<Any> {
        val list = listOf(
                Aggregation.lookup("CourtTypes", "courtTypeId", "_id", "types"),
                Aggregation.unwind("types"),
                Aggregation.group("types._id").count().`as`("count"),
                Aggregation.sort(Sort.Direction.DESC, "count")
        )
        val aggregation = Aggregation.newAggregation(list)
        val result = mongoTemplate.aggregate(aggregation, "Courts", DBObject::class.java)

        return result.mappedResults
    }

    fun convertToProjection(aggregation: CourtAggregation): CourtProjection {
        return CourtProjection(
                aggregation.id,
                aggregation.nameAr,
                aggregation.nameEn,
                ObjectId(aggregation.courtTypeId),
                aggregation.size!!,
                aggregation.openTime!!,
                aggregation.closeTime!!
        )
    }

}