package com.spribe.test.tests;

import com.spribe.test.asserts.PlayerAssert;
import com.spribe.test.dataproviders.PlayerRoleDataProvider;
import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class CreatePlayerTests extends BaseTest {

    @Test(dataProvider = "validRolesProvider", dataProviderClass = PlayerRoleDataProvider.class)
    public void validFlowTest(UserRole role) {
        PlayerRequestDto reqDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.createPlayer(role, reqDto);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        response.then().assertThat().body(not(emptyOrNullString()));

        PlayerResponseDto respDto = response.as(PlayerResponseDto.class);

        Assert.assertNotNull(respDto);

        PlayerAssert.assertEquals(reqDto, respDto);
    }
}
