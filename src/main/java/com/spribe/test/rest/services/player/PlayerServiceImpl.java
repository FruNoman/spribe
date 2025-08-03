package com.spribe.test.rest.services.player;

import com.spribe.test.rest.controller.RequestBuilder;
import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import com.spribe.test.rest.endpoints.Endpoints;
import com.spribe.test.rest.services.player.enums.UserRole;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Value("${base.url}")
    protected String baseUrl;

    public Response getPlayer(int playerId) {
        return RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .body(Map.of("playerId", playerId))
                .when()
                .post(Endpoints.PLAYER_GET);
    }


    public Response getAllPlayers() {
        return RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .when()
                .get(Endpoints.PLAYER_GET_ALL);
    }

    public Response createPlayer(UserRole userRole, PlayerRequestDto requestDto) {
        return RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .queryParam("age", requestDto.getAge())
                .queryParam("gender", requestDto.getGender())
                .queryParam("login", requestDto.getLogin())
                .queryParam("password", requestDto.getPassword())
                .queryParam("role", requestDto.getRole())
                .queryParam("screenName", requestDto.getScreenName())
                .get(Endpoints.PLAYER_CREATE + userRole.getRole());
    }

    public Response updatePlayer(UserRole userRole, int id, PlayerRequestDto requestDto) {
        return RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .body(requestDto)
                .patch(Endpoints.PLAYER_UPDATE + userRole.getRole() + "/" + id);
    }

    public Response deletePlayer(UserRole userRole, int playerId) {
        return RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .body(Map.of("playerId", playerId))
                .delete(Endpoints.PLAYER_DELETE + userRole.getRole());
    }
}
