package uk.gov.justice.digital.hmpps.educationdataaggregation.integration.wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.data.domain.PageImpl
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import uk.gov.justice.digital.hmpps.educationdataaggregation.prison.dto.Education

class PrisonApiExtension : BeforeAllCallback, AfterAllCallback, BeforeEachCallback {
  companion object {
    @JvmField
    val prisonApi = PrisonApiMockServer()
  }

  override fun beforeAll(context: ExtensionContext) {
    prisonApi.start()
  }

  override fun beforeEach(context: ExtensionContext) {
    prisonApi.resetRequests()
  }

  override fun afterAll(context: ExtensionContext) {
    prisonApi.stop()
  }
}

class PrisonApiMockServer : WireMockServer(WIREMOCK_PORT) {
  companion object {
    private const val WIREMOCK_PORT = 8332
  }

  private val mapper = ObjectMapper()

  fun stubEducationNotFound(nomsId: String): StubMapping =
    stubFor(
      get("/api/education/prisoner/$nomsId")
        .willReturn(
          aResponse()
            .withHeader("Content-Type", "application/json")
            .withStatus(404)
        )
    )

  fun stubEducationResponse(nomsId: String, educations: List<Education> = emptyList()): StubMapping =
    stubFor(
      get("/api/education/prisoner/$nomsId")
        .willReturn(
          aResponse()
            .withHeader("Content-Type", "application/json")
            .withBody(mapper.writeValueAsString(PageImpl(educations)))
            .withStatus(200)
        )
    )
}
