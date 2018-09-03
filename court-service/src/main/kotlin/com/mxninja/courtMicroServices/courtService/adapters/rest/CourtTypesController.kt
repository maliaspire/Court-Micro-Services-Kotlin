package com.mxninja.courtMicroServices.courtService.adapters.rest

import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtTypeProjection
import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtTypesMongoRepository
import com.mxninja.courtMicroServices.courtService.domains.CourtTypesAggregation
import com.mxninja.courtMicroServices.courtService.exceptions.CourtTypeNotFoundException
import com.mxninja.courtMicroServices.courtService.modules.ResponseModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@RestController
@RequestMapping("/courts/types")
class CourtTypesController @Autowired constructor(
        private val courtTypesMongoRepository: CourtTypesMongoRepository) {

    @GetMapping
    fun findAll(): ResponseModel<List<CourtTypeProjection>> {
        return ResponseModel(courtTypesMongoRepository.findAll())
    }

    @GetMapping("/id/{id}")
    fun findById(@PathVariable("id") id: String): ResponseModel<CourtTypeProjection> {
        val optional = courtTypesMongoRepository.findById(id)
        if (!optional.isPresent) {
            throw CourtTypeNotFoundException()
        }
        return ResponseModel(optional.get())
    }

    @PostMapping
    fun save(@RequestBody aggregation: CourtTypesAggregation): ResponseModel<CourtTypeProjection> {
        return ResponseModel(courtTypesMongoRepository.save(aggregation))
    }

    @PutMapping("/id/{id}")
    fun update(@RequestBody aggregation: CourtTypesAggregation, @PathVariable("id") id: String): ResponseModel<CourtTypeProjection> {
        if (!courtTypesMongoRepository.findById(id).isPresent) {
            throw CourtTypeNotFoundException()
        }
        aggregation.id = id
        return ResponseModel(courtTypesMongoRepository.update(aggregation))
    }

}