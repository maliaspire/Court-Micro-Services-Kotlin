package com.mxninja.courtMicroServices.courtService.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@Configuration
@EnableMongoRepositories(basePackages = ["com.mxninja.courtMicroServices.courtService.adapters.respositories"])
class MongoDBConfig @Autowired constructor(
        private val mongoDbFactory: MongoDbFactory,
        private val mongoMappingContext: MongoMappingContext
) {

    /**
     * @return MappingMongoConverter
     */
    @Bean
    fun mappingMongoConverter(): MappingMongoConverter {

        val dbRefResolver = DefaultDbRefResolver(mongoDbFactory)
        val converter = MappingMongoConverter(dbRefResolver, mongoMappingContext)
        converter.setTypeMapper(DefaultMongoTypeMapper(null))

        return converter
    }

    @Bean
    fun validatingMongoEventListener(): ValidatingMongoEventListener {
        return ValidatingMongoEventListener(validator())
    }

    @Bean
    fun validator(): LocalValidatorFactoryBean {
        return LocalValidatorFactoryBean()
    }

}