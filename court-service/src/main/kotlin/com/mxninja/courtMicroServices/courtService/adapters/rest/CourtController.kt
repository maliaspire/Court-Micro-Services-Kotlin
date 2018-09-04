package com.mxninja.courtMicroServices.courtService.adapters.rest

import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtMongoRepository
import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtProjection
import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtTypesMongoRepository
import com.mxninja.courtMicroServices.courtService.domains.CourtAggregation
import com.mxninja.courtMicroServices.courtService.exceptions.CourtTypeNotFoundException
import com.mxninja.courtMicroServices.courtService.modules.ResponseModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@RestController
@RequestMapping("/courts")
class CourtController @Autowired constructor(
        private val courtRepository: CourtMongoRepository,
        private val courtTypeRepository: CourtTypesMongoRepository) {

    @GetMapping
    fun findAll(): ResponseModel<List<CourtProjection>> {
        return ResponseModel(courtRepository.findAll())
    }

    @GetMapping("type/{typeId}")
    fun findAllByType(@PathVariable("typeId") typeId: String): ResponseModel<Any> {
        return ResponseModel(courtRepository.findAllByType(typeId))
    }

    @GetMapping("type/group/count")
    fun countByType(): ResponseModel<Any> {
        return ResponseModel(courtRepository.countByType())
    }

    @PostMapping
    fun save(@RequestBody body: CourtAggregation): ResponseModel<CourtProjection> {
        val typeOptional = courtTypeRepository.findById(body.courtTypeId)
        if (!typeOptional.isPresent) {
            throw CourtTypeNotFoundException()
        }
        return ResponseModel(courtRepository.save(body))
    }

}