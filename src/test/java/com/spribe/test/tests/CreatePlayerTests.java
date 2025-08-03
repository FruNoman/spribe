package com.spribe.test.tests;

import com.spribe.test.asserts.PlayerAssert;
import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreatePlayerTests extends BaseTest {
    @Test
    public void validFlowTest() {
        PlayerRequestDto reqDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.createPlayer(UserRole.SUPERVISOR, reqDto);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        PlayerResponseDto respDto = response.as(PlayerResponseDto.class);

        Assert.assertNotNull(respDto);

        PlayerAssert.assertEquals(reqDto, respDto);
    }
}
