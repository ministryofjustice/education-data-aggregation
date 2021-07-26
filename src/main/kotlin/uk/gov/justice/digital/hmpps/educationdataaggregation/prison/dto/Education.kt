package uk.gov.justice.digital.hmpps.educationdataaggregation.prison.dto

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.NotNull

@ApiModel(description = "Offender Education")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Education(
  @ApiModelProperty(value = "Offender booking id.", example = "14", required = true)
  val bookingId: @NotNull Long? = null,

  @ApiModelProperty(value = "Start date of education", example = "2018-02-11")
  val startDate: @NotNull LocalDate? = null,

  @ApiModelProperty(value = "End date of education", example = "2020-02-11")
  val endDate: LocalDate? = null,

  @ApiModelProperty(value = "The area of study for the offender while in school.", example = "General Studies")
  val studyArea: String? = null,

  @ApiModelProperty(
    value = "The highest level attained for the educational period.",
    example = "Degree Level or Higher"
  )
  val educationLevel: String? = null,

  @ApiModelProperty(value = "The number of educational years completed.", example = "2")
  val numberOfYears: Int? = null,

  @ApiModelProperty(value = "Year of graduation.", example = "2021")
  val graduationYear: String? = null,

  @ApiModelProperty(value = "Comment relating to education.", example = "The education is going well")
  val comment: String? = null,

  @ApiModelProperty(value = "Name of school attended.", example = "School of economics")
  val school: String? = null,

  @ApiModelProperty(value = "Whether this is special education", example = "false", required = true)
  val isSpecialEducation: Boolean? = null,

  @ApiModelProperty(value = "The education schedule", example = "Full Time", required = true)
  val schedule: String? = null,

  @ApiModelProperty(value = "A list of addresses associated with the education", required = true)
  val addresses: @NotNull List<Address> = emptyList(),
)
