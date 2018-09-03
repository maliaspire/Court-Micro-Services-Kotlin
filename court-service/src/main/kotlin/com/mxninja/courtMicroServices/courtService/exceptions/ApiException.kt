package com.mxninja.courtMicroServices.courtService.exceptions

import com.mxninja.courtMicroServices.courtService.modules.ApiStatus

/**
 * 9/3/2018
 * @author Mohammad Ali
 */
interface ApiException {

    fun getApiStatus(): ApiStatus

}