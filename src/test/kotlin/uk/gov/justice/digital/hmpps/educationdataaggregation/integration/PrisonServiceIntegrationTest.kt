package uk.gov.justice.digital.hmpps.educationdataaggregation.integration

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.kotlin.test.expectError
import reactor.test.StepVerifier
import uk.gov.justice.digital.hmpps.educationdataaggregation.common.rest.RestResponsePage
import uk.gov.justice.digital.hmpps.educationdataaggregation.integration.wiremock.PrisonApiExtension
import uk.gov.justice.digital.hmpps.educationdataaggregation.prison.dto.Education
import uk.gov.justice.digital.hmpps.educationdataaggregation.prison.service.PrisonService

@ExtendWith(MockitoExtension::class)
class PrisonServiceIntegrationTest : IntegrationTestBase() {

  @Autowired
  private lateinit var prisonService: PrisonService

  @Test
  fun `getOffenderEducation - throws exception for non existent offender`() {
    PrisonApiExtension.prisonApi.stubEducationNotFound(NOMS_ID)

    StepVerifier
      .create(prisonService.getOffenderEducation(NOMS_ID))
      .expectError<WebClientResponseException>()
      .verify()
  }

  @Test
  fun `getOffenderEducation - returns empty paged response`() {
    PrisonApiExtension.prisonApi.stubEducationResponse(NOMS_ID)

    StepVerifier
      .create(prisonService.getOffenderEducation(NOMS_ID))
      .expectNextMatches {
        it is RestResponsePage<Education> && it.content.size == 0
      }
      .verifyComplete()
  }

  @Test
  fun `getOffenderEducation - returns paged response with content`() {

    val education1 = Education(studyArea = "English")
    val education2 = Education(studyArea = "Maths")
    val educations = listOf(education1, education2)

    PrisonApiExtension.prisonApi.stubEducationResponse(NOMS_ID, educations)

    StepVerifier
      .create(prisonService.getOffenderEducation(NOMS_ID))
      .expectNextMatches {
        it.content.size == educations.size &&
          it.content[0].studyArea == education1.studyArea &&
          it.content[1].studyArea == education2.studyArea
      }
      .verifyComplete()
  }

  private companion object {
    const val NOMS_ID = "NOMS_ID1234"
  }
}
