package uk.gov.justice.digital.hmpps.educationdataaggregation.prison.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import uk.gov.justice.digital.hmpps.educationdataaggregation.common.rest.RestResponsePage
import uk.gov.justice.digital.hmpps.educationdataaggregation.prison.dto.Education

@Service
class PrisonService(@Qualifier("prisonApiWebClient") private val client: WebClient) {

  fun getOffenderEducation(nomisId: String): Mono<RestResponsePage<Education>> =
    client.get()
      .uri("/education/prisoner/$nomisId")
      .retrieve()
      .bodyToMono<RestResponsePage<Education>>()
      .doOnError(logError("getOffenderEducation failed"))

  private companion object {
    val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun logError(message: String) = { error: Throwable -> log.error("$message. Reason: ${error.message}", error) }
  }
}
