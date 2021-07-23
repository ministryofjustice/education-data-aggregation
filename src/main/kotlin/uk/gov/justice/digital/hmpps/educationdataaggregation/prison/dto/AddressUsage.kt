package uk.gov.justice.digital.hmpps.educationdataaggregation.prison.dto

import io.swagger.annotations.ApiModelProperty

data class AddressUsage(
  @ApiModelProperty(value = "Address ID of the associated address", example = "23422313", position = 1)
  val addressId: Long? = null,

  @ApiModelProperty(value = "The address usages", example = "HDC", position = 2)
  val addressUsage: String? = null,

  @ApiModelProperty(value = "The address usages description", example = "HDC Address", position = 3)
  val addressUsageDescription: String? = null,

  @ApiModelProperty(value = "Active Flag", example = "Y", allowableValues = "Y,N", position = 4)
  val activeFlag: Boolean? = null,
)
