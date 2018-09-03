package com.mxninja.courtMicroServices.courtService.modules

/**
 * 9/3/2018
 * @author Mohammad Ali
 */
enum class ApiStatus(val code: Int, val message: String) {

    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

}