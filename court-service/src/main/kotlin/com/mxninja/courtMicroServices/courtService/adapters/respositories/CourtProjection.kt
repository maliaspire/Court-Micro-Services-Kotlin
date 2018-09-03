package com.mxninja.courtMicroServices.courtService.adapters.respositories

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "Courts")
class CourtProjection {

    @field:Id
    @field:GeneratedValue
    lateinit var id: String

    lateinit var name: String

}