package com.mxninja.courtMicroServices.courtService.configuration

import com.google.common.base.Predicate
import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * 9/3/2018
 * @author Mohammad Ali
 */

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths()).build()
    }

    // Describe APIS
    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("List of User APIS endpoints")
                .license("User APIS endpoints")
                .description("List of User APIS endpoints")
                .version("1.0").build()

    }

    // Only select apis that matches the given Predicates.
    private fun paths(): Predicate<String> {
        // Match all paths except /error
        return Predicates.and(PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/error.*")))
    }

}