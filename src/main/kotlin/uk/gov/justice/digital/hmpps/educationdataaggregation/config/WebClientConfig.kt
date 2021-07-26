package uk.gov.justice.digital.hmpps.educationdataaggregation.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository

@Configuration
class WebClientConfig {

  @Bean
  fun authorizedClientManager(
    clientRegistrationRepository: ReactiveClientRegistrationRepository,
    oAuth2AuthorizedClientService: ReactiveOAuth2AuthorizedClientService,
  ): AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager {

    val authorizedClientProvider = ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
      .clientCredentials()
      .build()

    val authorizedClientManager = AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
      clientRegistrationRepository,
      oAuth2AuthorizedClientService
    )

    authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider)

    return authorizedClientManager
  }
}
