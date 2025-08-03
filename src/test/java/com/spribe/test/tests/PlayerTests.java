package com.spribe.test.tests;

import com.spribe.test.rest.controller.RequestBuilder;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class PlayerTests extends BaseTest{
    @Test
    public void initialCheckGetAll(){
        RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .get("/player/get/all")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void initialCheckGetPlayer(){
        RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .body("{ \"playerId\": 1 }")
                .post("/player/get")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void initialCreatePlayer() {
        String editorLogin = "supervisor";

        String uniqueSuffix = String.valueOf(System.currentTimeMillis());

        RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .contentType("application/json")
                .queryParam("age", 30)
                .queryParam("gender", "male")
                .queryParam("login", "user" + uniqueSuffix)
                .queryParam("password", "pass1234")
                .queryParam("role", "user")
                .queryParam("screenName", "screen" + uniqueSuffix)
                .get("/player/create/" + editorLogin)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void updatePlayer() {
        String editorLogin = "supervisor";
        int targetUserId = 441590145;

        String requestBody = """
        {
            "age": 35,
            "screenName": "UpdatedName123",
            "password": "newPass456"
        }
    """;

        RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(requestBody)
                .patch("/player/update/{editor}/{id}", editorLogin, targetUserId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void deletePlayerById() {
        String editor = "supervisor";
        int playerIdToDelete = 441590145;
        String requestBody = """
        {
            "playerId": %d
        }
    """.formatted(playerIdToDelete);
        RequestBuilder.newRequest()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(requestBody)
                .delete("/player/delete/" + editor)
                .then()
                .log().all()
                .statusCode(204);
    }
}
