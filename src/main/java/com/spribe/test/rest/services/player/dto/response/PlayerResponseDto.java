package com.spribe.test.rest.services.player.dto.response;

public class PlayerResponseDto {
    private Integer id;
    private String login;
    private String password;
    private String screenName;
    private String gender;
    private Integer age;
    private String role;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }
}
