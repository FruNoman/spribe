package com.spribe.test.tests;

import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePlayerTests extends BaseTest{
    @Test
    public void updatePlayer() {
        int targetUserId = 202777521;

        PlayerRequestDto requestDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.updatePlayer(UserRole.SUPERVISOR, targetUserId, requestDto);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertFalse(response.getBody().asString().trim().isEmpty(), "Response body is empty");
        PlayerResponseDto dto = response.as(PlayerResponseDto.class);
        Assert.assertEquals(dto.getScreenName(), requestDto.getScreenName());
    }
}
