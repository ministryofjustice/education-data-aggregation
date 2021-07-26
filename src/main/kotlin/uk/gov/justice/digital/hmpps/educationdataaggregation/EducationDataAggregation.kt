package uk.gov.justice.digital.hmpps.educationdataaggregation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EducationDataAggregation

fun main(args: Array<String>) {
  runApplication<EducationDataAggregation>(*args)
}
