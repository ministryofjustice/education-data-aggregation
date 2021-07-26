package uk.gov.justice.digital.hmpps.educationdataaggregation.security.config

import org.springframework.context.annotation.Bean
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter
import reactor.core.publisher.Mono
import uk.gov.justice.digital.hmpps.educationdataaggregation.security.converter.AuthAwareTokenConverter

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {
  @Bean
  fun configure(http: ServerHttpSecurity): SecurityWebFilterChain {
    http
      .oauth2Client()
      .and()
      .authorizeExchange()
      .and()
      .headers().frameOptions().mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN)
      .and()
      .csrf().disable()
      .authorizeExchange { auth ->
        auth
          .pathMatchers(
            "/webjars/**",
            "favicon.ico",
            "/health/**",
            "/info",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v2/api-docs/**",
            "/h2-console/**"
          )
          .permitAll()
          .anyExchange()
          .authenticated()
      }
      .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
      .oauth2ResourceServer().jwt().jwtAuthenticationConverter(grantedAuthoritiesExtractor())

    return http.build()
  }

  private fun grantedAuthoritiesExtractor(): Converter<Jwt, Mono<AbstractAuthenticationToken>> {
    return ReactiveJwtAuthenticationConverterAdapter(AuthAwareTokenConverter())
  }
}
