package uk.gov.justice.digital.hmpps.educationdataaggregation.prison.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class PrisonWebClientConfig(
  @Value("\${prison.api.url}") private val prisonApiRootUri: String,
  private val webClientBuilder: WebClient.Builder
) {

  @Bean
  fun prisonApiWebClient(authorizedClientManager: ReactiveOAuth2AuthorizedClientManager): WebClient {
    val oauth2Client = ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager)
    oauth2Client.setDefaultClientRegistrationId("prison-api")
    return webClientBuilder
      .baseUrl(prisonApiRootUri)
      .filter(oauth2Client)
      .build()
  }
}
