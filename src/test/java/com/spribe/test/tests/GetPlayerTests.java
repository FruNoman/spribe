package com.spribe.test.tests;

import com.spribe.test.asserts.PlayerAssert;
import com.spribe.test.dataproviders.PlayerRoleDataProvider;
import com.spribe.test.dataproviders.PlayerValidationDataProvider;
import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetPlayerTests extends BaseTest {
    private PlayerRequestDto reqDto;
    private Integer playerId;
    private Integer nonExistPlayerId = Integer.MAX_VALUE;

    @BeforeClass
    public void beforeClass() {
        reqDto = PlayerRequestDto.generatePlayer();
        Response response = playerService.createPlayer(UserRole.SUPERVISOR, reqDto);
        playerId = response.as(PlayerResponseDto.class).getId();
    }

    @Test
    public void validFlowGetPlayerTest() {
        Response response = playerService.getPlayer(playerId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        PlayerAssert.assertBodyNotEmpty(response);

        PlayerResponseDto dto = response.as(PlayerResponseDto.class);

        Assert.assertEquals(dto.getId(), playerId);

        PlayerAssert.assertEquals(reqDto, dto);
    }

    @Test
    public void nonExistingPlayerIdTest() {
        Response response = playerService.getPlayer(nonExistPlayerId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test(dataProvider = "invalidPlayerIds",
            dataProviderClass = PlayerValidationDataProvider.class)
    public void invalidIdTests(Integer invalidId) {
        Response response = playerService.getPlayer(invalidId);

        Assert.assertEquals(response.getStatusCode(),
                HttpStatus.SC_BAD_REQUEST,
                "Expected 400 BAD_REQUEST: " + invalidId);
    }
}
