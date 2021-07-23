package uk.gov.justice.digital.hmpps.educationdataaggregation.resource

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import uk.gov.justice.digital.hmpps.educationdataaggregation.common.rest.RestResponsePage
import uk.gov.justice.digital.hmpps.educationdataaggregation.config.ErrorResponse
import uk.gov.justice.digital.hmpps.educationdataaggregation.prison.dto.Education
import uk.gov.justice.digital.hmpps.educationdataaggregation.prison.service.PrisonService

@Api(tags = ["Education"])
@RestController
@RequestMapping(value = ["\${api.base.path}/education"], produces = [MediaType.APPLICATION_JSON_VALUE])
class EducationController(private val prisonService: PrisonService) {

  @PreAuthorize("hasRole('CURIOUS_API')")
  @GetMapping("/{nomsId}")
  @ApiOperation(
    value = "List of education for an offender",
    nickname = "getEducations"
  )
  @ApiResponses(
    value = [
      ApiResponse(
        code = 400,
        message = "Invalid request.",
        response = ErrorResponse::class
      ),
      ApiResponse(
        code = 500,
        message = "Unrecoverable error occurred whilst processing request.",
        response = ErrorResponse::class
      )
    ]
  )
  fun getEducations(@PathVariable nomsId: String): Mono<RestResponsePage<Education>> {
    return prisonService.getOffenderEducation(nomsId)
  }
}
