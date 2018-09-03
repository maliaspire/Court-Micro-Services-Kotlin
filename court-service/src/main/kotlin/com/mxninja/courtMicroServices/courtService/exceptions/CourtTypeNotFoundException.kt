package com.mxninja.courtMicroServices.courtService.exceptions

import com.mxninja.courtMicroServices.courtService.modules.ApiStatus

/**
 * 9/3/2018
 * @author Mohammad Ali
 */
class CourtTypeNotFoundException : RuntimeException("Cannot find court with this id"), ApiException {

    override fun getApiStatus(): ApiStatus {
        return ApiStatus.NOT_FOUND
    }
}