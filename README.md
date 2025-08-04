
Spribe API Automation Framework

This automation framework is built using Java, RestAssured, Spring Boot, Jackson, JavaFaker, and Allure for API testing.
Overview

The framework provides a custom wrapper around RestAssured for simplified and standardized API requests via the RequestBuilder class:

static {
RestAssured.defaultParser = Parser.JSON;
}

public static RequestSpecification newRequest() {
return RestAssured.given()
.relaxedHTTPSValidation()
.filters(new AllureRestAssured())
.filter(new SpribeLogFilter())
.contentType(ContentType.JSON);
}

    This setup automatically enables console logging and integrates AllureRestAssured for rich reporting.

    The custom SpribeLogFilter adds enhanced logging capabilities.

Player API Service

The framework includes a dedicated service class PlayerServiceImpl that encapsulates all interactions with the Player API endpoints, providing clear and reusable methods for common operations such as create, get, update, and delete players.
Configuration

All configurable properties are stored in application.properties

You can execute the tests from the command line using Gradle:
./gradlew clean test

You can add or switch between multiple environments by creating additional properties files and activating them using Spring profiles:

./gradlew clean test -Dspring.profiles.active=env

Running Tests

Or run specific TestNG suites via your IDE, for example:

src/test/resources/suites/all_testng.xml

Reporting

After test execution, an Allure results folder (allure-results) is generated automatically.

To view the detailed Allure report, simply run:

allure serve allure-results

This launches a local web server with an interactive HTML report, including request logs, assertions, screenshots, and more.