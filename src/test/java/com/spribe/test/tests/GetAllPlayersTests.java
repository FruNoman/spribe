package com.spribe.test.tests;

import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.dto.response.PlayerResponseDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GetAllPlayersTests extends BaseTest {
    private List<PlayerResponseDto> createdPlayers = new ArrayList<>();

    @BeforeMethod
    public void setupPlayers() {
        int playersToCreate = 5;
        createdPlayers.addAll(
                java.util.stream.IntStream.range(0, playersToCreate)
                        .mapToObj(i -> {
                            PlayerRequestDto reqDto = PlayerRequestDto.generatePlayer();
                            Response response = playerService.createPlayer(UserRole.SUPERVISOR, reqDto);
                            return response.as(PlayerResponseDto.class);
                        })
                        .toList()
        );
    }

    @Test
    public void validFlowTest() {
        Response response = playerService.getAllPlayers();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Wrong status code from getAllPlayers");

        List<PlayerResponseDto> allPlayers = response.jsonPath().getList("players", PlayerResponseDto.class);

        Assert.assertNotNull(allPlayers, "Response does not contain 'players'");
        Assert.assertFalse(allPlayers.isEmpty(), "'players' list is empty");

        Set<Integer> createdIds = createdPlayers.stream()
                .map(PlayerResponseDto::getId)
                .collect(Collectors.toSet());

        Set<Integer> allIds = allPlayers.stream()
                .map(PlayerResponseDto::getId)
                .collect(Collectors.toSet());

        for (Integer createdId : createdIds) {
            Assert.assertTrue(allIds.contains(createdId), "Created player with id " + createdId + " not found in all players");
        }
    }

    @Test
    public void autoIncrementTest() {
        Response response = playerService.getAllPlayers();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Failed to fetch all players");

        List<PlayerResponseDto> players = response.jsonPath().getList("players", PlayerResponseDto.class);

        List<Integer> sortedIds = players.stream()
                .map(PlayerResponseDto::getId)
                .sorted()
                .collect(Collectors.toList());

        for (int i = 1; i < sortedIds.size(); i++) {
            Integer previous = sortedIds.get(i - 1);
            Integer current = sortedIds.get(i);
            Assert.assertEquals(current.longValue(), previous + 1,
                    String.format("IDs are not sequential: expected %d but got %d", previous + 1, current));
        }
    }
}
