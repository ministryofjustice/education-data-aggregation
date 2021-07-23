package uk.gov.justice.digital.hmpps.educationdataaggregation.prison.dto

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

class Address(
  @ApiModelProperty(value = "Address Id", example = "543524", position = 1)
  val addressId: Long? = null,

  @ApiModelProperty(value = "Address Type", notes = "Reference domain is ADDR_TYPE", example = "BUS", position = 2)
  val addressType: String? = null,

  @ApiModelProperty(value = "Flat", example = "3B", position = 3)
  val flat: String? = null,

  @ApiModelProperty(value = "Premise", example = "Liverpool Prison", position = 4)
  val premise: String? = null,

  @ApiModelProperty(value = "Street", example = "Slinn Street", position = 5)
  val street: String? = null,

  @ApiModelProperty(value = "Locality", example = "Brincliffe", position = 6)
  val locality: String? = null,

  @ApiModelProperty(value = "Town/City", notes = "Reference domain is CITY", example = "Liverpool", position = 7)
  val town: String? = null,

  @ApiModelProperty(value = "Postal Code", example = "LI1 5TH", position = 8)
  val postalCode: String? = null,

  @ApiModelProperty(value = "County", notes = "Reference domain is COUNTY", example = "HEREFORD", position = 9)
  val county: String? = null,

  @ApiModelProperty(value = "Country", notes = "Reference domain is COUNTRY", example = "ENG", position = 10)
  val country: String? = null,

  @ApiModelProperty(value = "Comment", example = "This is a comment text", position = 11)
  val comment: String? = null,

  @ApiModelProperty(required = true, value = "Primary Address", example = "Y", position = 12)
  val primary: Boolean? = null,

  @ApiModelProperty(required = true, value = "No Fixed Address", example = "N", position = 13)
  val noFixedAddress: Boolean? = null,

  @ApiModelProperty(value = "Date Added", example = "2005-05-12", position = 14)
  val startDate: LocalDate? = null,

  @ApiModelProperty(value = "Date ended", example = "2021-02-12", position = 15)
  val endDate: LocalDate? = null,

  @ApiModelProperty(value = "The phone number associated with the address", position = 16)
  val phones: List<Telephone>? = null,

  @ApiModelProperty(value = "The address usages/types", position = 17)
  val addressUsages: List<AddressUsage>? = null,
)
