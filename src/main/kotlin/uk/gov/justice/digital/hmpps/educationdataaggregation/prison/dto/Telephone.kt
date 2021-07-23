package uk.gov.justice.digital.hmpps.educationdataaggregation.prison.dto

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

data class Telephone(
  @ApiModelProperty(value = "Phone Id", example = "2234232")
  val phoneId: Long? = null,

  @ApiModelProperty(required = true, value = "Telephone number", example = "0114 2345678")
  val number: @NotBlank String? = null,

  @ApiModelProperty(required = true, value = "Telephone type", example = "TEL")
  val type: @NotBlank String? = null,

  @ApiModelProperty(value = "Telephone extension number", example = "123")
  val ext: String? = null,
)
