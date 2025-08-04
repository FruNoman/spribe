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

public class UpdatePlayerTests extends BaseTest {
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
    public void validFlowUpdateTest(UserRole userRole) {
        PlayerRequestDto requestDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.updatePlayer(userRole, playerId, requestDto);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        PlayerAssert.assertBodyNotEmpty(response);

        PlayerResponseDto dto = response.as(PlayerResponseDto.class);

        PlayerAssert.assertEquals(requestDto, dto);
    }


    @Test(dataProvider = "nonCreatePermissionProvide",
            dataProviderClass = PlayerRoleDataProvider.class)
    public void nonPermissionsRolesUpdateTest(UserRole userRole) {
        PlayerRequestDto requestDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.updatePlayer(userRole, playerId, requestDto);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void nonExistingIdUpdateTest() {
        PlayerRequestDto requestDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.updatePlayer(UserRole.SUPERVISOR, nonExistPlayerId, requestDto);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }
}
