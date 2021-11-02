plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "3.3.12"
  kotlin("plugin.spring") version "1.5.31"
}

configurations {
  testImplementation { exclude(group = "org.junit.vintage") }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("io.springfox:springfox-boot-starter:3.0.0")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")

  testImplementation("com.github.tomakehurst:wiremock-standalone:2.27.2")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.testcontainers:junit-jupiter:1.16.2")
  testImplementation("io.projectreactor:reactor-test")
}

tasks {
  compileKotlin {
    kotlinOptions {
      jvmTarget = "16"
    }
  }
}
