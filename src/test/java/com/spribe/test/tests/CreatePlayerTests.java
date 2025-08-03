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
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class CreatePlayerTests extends BaseTest {

    @Test(dataProvider = "validRolesProvider",
            dataProviderClass = PlayerRoleDataProvider.class)
    public void validRolesTest(UserRole role) {
        PlayerRequestDto reqDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.createPlayer(role, reqDto);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        response.then().assertThat().body(not(emptyOrNullString()));

        PlayerResponseDto respDto = response.as(PlayerResponseDto.class);

        Assert.assertNotNull(respDto);

        PlayerAssert.assertEquals(reqDto, respDto);
    }

    @Test(dataProvider = "nonCreatePermissionProvide",
            dataProviderClass = PlayerRoleDataProvider.class)
    public void nonPermissionRolesTest(UserRole role) {
        PlayerRequestDto reqDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.createPlayer(role, reqDto);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }

    @Test(dataProvider = "invalidPlayerDataProvider",
            dataProviderClass = PlayerValidationDataProvider.class)
    public void invalidPlayerDataValidationTest(PlayerRequestDto invalidReqDto, String description) {
        Response response = playerService.createPlayer(UserRole.SUPERVISOR, invalidReqDto);

        Assert.assertEquals(response.getStatusCode(),
                HttpStatus.SC_BAD_REQUEST,
                "Expected 400 for: " + description);
    }
}
