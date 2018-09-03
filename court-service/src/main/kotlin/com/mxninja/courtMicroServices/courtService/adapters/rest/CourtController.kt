package com.mxninja.courtMicroServices.courtService.adapters.rest

import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtMongoRepository
import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtProjection
import com.mxninja.courtMicroServices.courtService.modules.ResponseModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@RestController
@RequestMapping("/courts")
class CourtController @Autowired constructor(
        private val courtRepository: CourtMongoRepository) {

    @GetMapping
    fun findAll(): ResponseModel<List<CourtProjection>> {
        return ResponseModel(courtRepository.findAll())
    }

}