package com.mxninja.courtMicroServices.courtService.adapters.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.mxninja.courtMicroServices.courtService.Main
import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtMongoRepository
import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtTypeProjection
import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtTypesMongoRepository
import com.mxninja.courtMicroServices.courtService.domains.CourtTypesAggregation
import com.mxninja.courtMicroServices.courtService.exceptions.CourtTypeNotFoundException
import com.mxninja.courtMicroServices.courtService.modules.ApiStatus
import org.bson.types.ObjectId
import org.hamcrest.Matchers.any
import org.hamcrest.Matchers.hasSize
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@RunWith(SpringRunner::class)
@WebMvcTest(Main::class)
class CourtTypesControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var courtTypesRepository: CourtTypesMongoRepository

    @MockBean
    private lateinit var courtTypesController: CourtTypesController

    @field:Rule
    @field:JvmField
    val expectedException = ExpectedException.none()!!

    private val id = ObjectId.get().toHexString()
    private val nameAr = "Name Ar"
    private val nameEn = "Name En"
    private val imageUrl = "http://mxninja.com/images/1.png"
    private val objectMapper = ObjectMapper()

    @Test
    fun whenFindAllThenEmptyList() {
        given(courtTypesRepository.findAll()).willReturn(listOf())

        mockMvc.perform(MockMvcRequestBuilders.get("/courts/types").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code").value(ApiStatus.OK.code))
                .andExpect(jsonPath("$.data").value(hasSize<Any>(0)))
    }

    @Test
    fun whenFindAllThenReturnData() {
        given(courtTypesRepository.findAll()).willReturn(listOf(
                CourtTypeProjection(id, nameAr, nameEn, imageUrl)
        ))

        mockMvc.perform(get("/courts/types").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code").value(ApiStatus.OK.code))
                .andExpect(jsonPath("$.data").value(hasSize<Any>(1)))
                .andExpect(jsonPath("$.data[0].id").value(any(String::class.java)))
                .andExpect(jsonPath("$.data[0].nameAr").value(any(String::class.java)))
                .andExpect(jsonPath("$.data[0].nameEn").value(any(String::class.java)))
                .andExpect(jsonPath("$.data[0].imageUrl").value(any(String::class.java)))
    }

    @Test
    fun whenFindByIdThenReturnData() {
        given(courtTypesRepository.findById(id)).willReturn(Optional.of(CourtTypeProjection(id, nameAr, nameEn, imageUrl)))

        mockMvc.perform(get("/courts/types/id/{id}", id))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code").value(ApiStatus.OK.code))
                .andExpect(jsonPath("$.data.id").value(any(String::class.java)))
                .andExpect(jsonPath("$.data.nameAr").value(any(String::class.java)))
                .andExpect(jsonPath("$.data.nameEn").value(any(String::class.java)))
                .andExpect(jsonPath("$.data.imageUrl").value(any(String::class.java)))
    }

    @Test
    fun whenSaveValidData() {
        given(courtTypesRepository.save(ArgumentMatchers.any()))
                .willReturn(CourtTypeProjection(id, nameAr, nameEn, imageUrl))
        val aggregation = CourtTypesAggregation(null, nameAr, nameEn, imageUrl)
        mockMvc.perform(post("/courts/types")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(aggregation)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code").value(ApiStatus.OK.code))
                .andExpect(jsonPath("$.data.id").value(any(String::class.java)))
                .andExpect(jsonPath("$.data.nameAr").value(nameAr))
                .andExpect(jsonPath("$.data.nameEn").value(nameEn))
                .andExpect(jsonPath("$.data.imageUrl").value(imageUrl))
    }

    @Test
    fun whenUpdateValidData() {
        given(courtTypesRepository.update(ArgumentMatchers.any()))
                .willReturn(CourtTypeProjection(id, nameAr, nameEn, imageUrl))

        val aggregation = CourtTypesAggregation(id, nameAr, nameEn, imageUrl)
        mockMvc.perform(put("/courts/types/id/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(aggregation)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code").value(ApiStatus.OK.code))
                .andExpect(jsonPath("$.data.id").value(id))
                .andExpect(jsonPath("$.data.nameAr").value(nameAr))
                .andExpect(jsonPath("$.data.nameEn").value(nameEn))
                .andExpect(jsonPath("$.data.imageUrl").value(imageUrl))

    }

}