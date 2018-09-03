package com.mxninja.courtMicroServices.courtService.exceptions

import com.fasterxml.jackson.databind.ObjectMapper
import com.mxninja.courtMicroServices.courtService.modules.ApiStatus
import com.mxninja.courtMicroServices.courtService.modules.ResponseModel
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@Component
class ExceptionHandler : AbstractHandlerExceptionResolver() {

    private val objectMapper = ObjectMapper()

    override fun doResolveException(request: HttpServletRequest, response: HttpServletResponse, handler: Any?, ex: Exception): ModelAndView? {
        val apiStatus = if (ex is ApiException) {
            (ex as ApiException).getApiStatus()
        } else {
            ApiStatus.INTERNAL_SERVER_ERROR
        }
        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val respModel = ResponseModel<Any>(apiStatus)
        response.writer.write(objectMapper.writeValueAsString(respModel))
        response.writer.flush()
        response.writer.close()
        return ModelAndView()
    }
}