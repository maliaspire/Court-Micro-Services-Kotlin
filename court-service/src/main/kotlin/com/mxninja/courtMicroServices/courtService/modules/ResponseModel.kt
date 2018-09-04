package com.mxninja.courtMicroServices.courtService.modules

/**
 * 9/3/2018
 * @author Mohammad Ali
 */
class ResponseModel<DataType>(val code: Int, val message: String) {

    var data: DataType? = null

    constructor(apiStatus: ApiStatus) : this(apiStatus.code, apiStatus.message)

    constructor(apiStatus: ApiStatus, data: DataType) : this(apiStatus) {
        this.data = data
    }

    constructor(data: DataType) : this(ApiStatus.OK, data)

    constructor() : this(ApiStatus.OK)

}