package com.spribe.test.rest.controller;

import com.spribe.test.rest.filters.SpribeLogFilter;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
    static {
        RestAssured.defaultParser = Parser.JSON;
    }

    public static RequestSpecification newRequest() {
        return RestAssured.given().relaxedHTTPSValidation()
                .filters(new AllureRestAssured())
                .filter(new SpribeLogFilter())
                .contentType(ContentType.JSON);
    }
}
