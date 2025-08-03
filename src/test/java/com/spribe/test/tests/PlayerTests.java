package com.spribe.test.tests;

import com.spribe.test.rest.services.player.PlayerService;
import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PlayerTests extends BaseTest {
    @Autowired
    private PlayerService playerService;

    @Test
    public void initialCheckGetAll() {
        Response response = playerService.getAllPlayers();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        List<PlayerResponseDto> players = response.jsonPath().getList("players", PlayerResponseDto.class);
        Assert.assertNotNull(players);
        Assert.assertFalse(players.isEmpty());
    }

    @Test
    public void initialCheckGetPlayer() {
        Response response = playerService.getPlayer(142302909);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertFalse(response.getBody().asString().trim().isEmpty(), "Response body is empty");
        PlayerResponseDto dto = response.as(PlayerResponseDto.class);
        Assert.assertEquals(dto.getId(), 142302909);
    }

    @Test
    public void initialCreatePlayer() {
        PlayerRequestDto requestDto = PlayerRequestDto.generatePlayer();

        Response response = playerService.createPlayer(UserRole.INVALID_ROLE, requestDto);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);

        PlayerResponseDto dto = response.as(PlayerResponseDto.class);

        Assert.assertNotNull(dto);
        Assert.assertEquals(dto.getLogin(), requestDto.getLogin());
    }

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

    @Test
    public void deletePlayerById() {
        int playerIdToDelete = 2120815003;

        Response response = playerService.deletePlayer(UserRole.SUPERVISOR, playerIdToDelete);
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
