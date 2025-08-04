package com.spribe.test.asserts;

import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class PlayerAssert {
    public static void assertEquals(PlayerRequestDto request, PlayerResponseDto response) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(response, "Response DTO should not be null");

        if (response != null) {
            softAssert.assertEquals(response.getLogin(), request.getLogin(), "Login mismatch:");
            softAssert.assertEquals(response.getRole(), request.getRole(), "Role mismatch:");
            softAssert.assertEquals(response.getGender(), request.getGender(), "Gender mismatch:");
            softAssert.assertEquals(response.getScreenName(), request.getScreenName(), "ScreenName mismatch:");
            softAssert.assertEquals(response.getAge(), request.getAge(), "Age mismatch:");
            softAssert.assertEquals(response.getPassword(), request.getPassword(), "Password mismatch:");

            softAssert.assertNotNull(response.getId(), "Id should not be null:");
            if (response.getId() != null) {
                softAssert.assertTrue(response.getId() > 0, "Id should be positive:");
            }
        }

        softAssert.assertAll();
    }

    public static void assertBodyNotEmpty(Response response){
        Assert.assertFalse(response.getBody().asString().trim().isEmpty(), "Response body is empty");
    }

    public static void assertEquals(PlayerResponseDto expected, PlayerResponseDto actual) {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotNull(actual, "Actual PlayerResponseDto should not be null");

        if (actual != null) {
            softAssert.assertEquals(actual.getId(), expected.getId(), "ID mismatch:");
            softAssert.assertEquals(actual.getLogin(), expected.getLogin(), "Login mismatch:");
            softAssert.assertEquals(actual.getPassword(), expected.getPassword(), "Password mismatch:");
            softAssert.assertEquals(actual.getScreenName(), expected.getScreenName(), "ScreenName mismatch:");
            softAssert.assertEquals(actual.getGender(), expected.getGender(), "Gender mismatch:");
            softAssert.assertEquals(actual.getAge(), expected.getAge(), "Age mismatch:");
            softAssert.assertEquals(actual.getRole(), expected.getRole(), "Role mismatch:");
        }

        softAssert.assertAll();
    }
}
