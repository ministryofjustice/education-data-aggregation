package uk.gov.justice.digital.hmpps.educationdataaggregation.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.StringVendorExtension
import springfox.documentation.service.VendorExtension
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.ArrayList
import java.util.Date
import java.util.Optional

@Configuration
@EnableSwagger2
class SwagggerConfiguration {
  @Bean
  fun api(): Docket? {
    val docket = Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("uk.gov.justice.digital.hmpps.educationdataaggregation.resource"))
      .paths(PathSelectors.any())
      .build()
      .apiInfo(apiInfo())
    docket.genericModelSubstitutes(Optional::class.java)
    docket.directModelSubstitute(ZonedDateTime::class.java, Date::class.java)
    docket.directModelSubstitute(LocalDateTime::class.java, Date::class.java)
    return docket
  }

  private fun apiInfo(): ApiInfo? {
    val vendorExtension = StringVendorExtension("", "")
    val vendorExtensions: MutableCollection<VendorExtension<*>> = ArrayList()
    vendorExtensions.add(vendorExtension)
    return ApiInfo(
      "HMPPS Education data aggregation Documentation",
      "API for Education data aggregation",
      "1.0",
      "https://gateway.nomis-api.service.justice.gov.uk/auth/terms",
      contactInfo(),
      "Open Government Licence v3.0",
      "https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/",
      vendorExtensions
    )
  }

  private fun contactInfo(): Contact? {
    return Contact(
      "HMPPS Digital Studio",
      "",
      "feedback@digital.justice.gov.uk"
    )
  }
}
