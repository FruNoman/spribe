package com.spribe.test.rest.services.player;

import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;

public interface PlayerService {
    Response getPlayer(int playerId);

    Response getAllPlayers();

    Response deletePlayer(UserRole userRole, int playerId);

    Response createPlayer(UserRole userRole, PlayerRequestDto requestDto);

    Response updatePlayer(UserRole userRole, int id, PlayerRequestDto requestDto);
}
