package com.spribe.test.asserts;

import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
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
}
