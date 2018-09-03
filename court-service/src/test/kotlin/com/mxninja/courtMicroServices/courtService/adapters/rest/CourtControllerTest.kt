package com.mxninja.courtMicroServices.courtService.adapters.rest

import com.mxninja.courtMicroServices.courtService.adapters.respositories.CourtMongoRepository
import com.mxninja.courtMicroServices.courtService.modules.ApiStatus
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@RunWith(SpringRunner::class)
@WebMvcTest
class CourtControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var courtRepository: CourtMongoRepository

    @Test
    fun whenFindAllReturnEmptyList() {
        BDDMockito.given(courtRepository.findAll()).willReturn(listOf())
        mockMvc.perform(MockMvcRequestBuilders.get("/courts").contentType(MediaType.APPLICATION_ATOM_XML_VALUE))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.code").value(ApiStatus.OK.code))
                .andExpect(jsonPath("$.data").value(hasSize<Any>(0)))
    }

}