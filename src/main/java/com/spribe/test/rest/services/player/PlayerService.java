package com.spribe.test.rest.services.player;

import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;

public interface PlayerService {
    Response getPlayer(Integer playerId);

    Response getAllPlayers();

    Response deletePlayer(UserRole userRole, Integer playerId);

    Response createPlayer(UserRole userRole, PlayerRequestDto requestDto);

    Response updatePlayer(UserRole userRole, Integer id, PlayerRequestDto requestDto);
}
