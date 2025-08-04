package com.spribe.test.tests;

import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePlayerTest extends BaseTest {



    @Test
    public void deletePlayerById() {
        int playerIdToDelete = 2120815003;

        Response response = playerService.deletePlayer(UserRole.SUPERVISOR, playerIdToDelete);
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
