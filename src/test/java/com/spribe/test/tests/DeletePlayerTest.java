package com.spribe.test.tests;

import com.spribe.test.asserts.PlayerAssert;
import com.spribe.test.dataproviders.PlayerRoleDataProvider;
import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeletePlayerTest extends BaseTest {
    private PlayerRequestDto reqDto;
    private Integer playerId;
    private Integer nonExistPlayerId = Integer.MAX_VALUE;

    @BeforeMethod
    public void beforeTest() {
        reqDto = PlayerRequestDto.generatePlayer();
        Response response = playerService.createPlayer(UserRole.SUPERVISOR, reqDto);
        playerId = response.as(PlayerResponseDto.class).getId();
    }

    @Test(dataProvider = "validRolesProvider",
            dataProviderClass = PlayerRoleDataProvider.class)
    public void validDeleteTest(UserRole userRole) {
        Response response = playerService.deletePlayer(userRole, playerId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);

        //TODO: need wait fix for get player response
//        Assert.assertEquals(playerService.getPlayer(playerId).getStatusCode(), HttpStatus.SC_NOT_FOUND);

    }

    @Test(dataProvider = "nonCreatePermissionProvide",
            dataProviderClass = PlayerRoleDataProvider.class)
    public void invalidRolesDeleteTest(UserRole userRole) {
        Response response = playerService.deletePlayer(userRole, playerId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void nonExistingPlayerIdDeleteTest() {
        Response response = playerService.deletePlayer(UserRole.SUPERVISOR, nonExistPlayerId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }
}
