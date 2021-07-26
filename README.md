# education-data-aggregation

This is an API service which exposes aggregated offender education data from internal hmpps services 
and the third-party education provider Meganexus.

# Build instructions

To set up gradle and pull dependencies:
`$ ./gradlew`

To build the service and run tests:
`$ ./gradlew clean build`

To run tests:
`$ ./gradlew test`


# Running the service 

There are a few defaults to easily run this service.

There is dev docker compose file with upstream dependency images and a spring dev profile pointing to their local urls.

To start the upstream services locally:
`docker-compose -f docker-compose-dev.yml up -d`

To start the application locally:
`./gradlew bootRun --args='--spring.profiles.active=dev'`

Alternatively, you can point to the services remotely. A `application-local.yml.example` file exists with suggested fields to update with remote urls and config.
You can rename this file to `application-local.yml` and fill it in with any remote or custom config.

**note:** `application-local.yml` is git ignored.

To start the application locally with local config:
`./gradlew bootRun --args='--spring.profiles.active=local'`


# API Documentation

Point a browser to  `http://localhost:8080/swagger-ui` to see the API documentation.

#Circle-CI

The project is built automatically for PRs submitted to github, with its pipelines here:

`https://app.circleci.com/pipelines/github/ministryofjustice/education-data-aggregation`