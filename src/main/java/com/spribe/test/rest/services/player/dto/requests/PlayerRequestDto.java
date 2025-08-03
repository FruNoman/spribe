package com.spribe.test.rest.services.player.dto.requests;

import com.github.javafaker.Faker;

public class PlayerRequestDto {
    private Integer age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public static PlayerRequestDto generatePlayer() {
        Faker faker = new Faker();
        String login = faker.name().username() + faker.number().numberBetween(100, 999);
        String screenName = faker.name().firstName() + faker.number().numberBetween(100, 999);

        String password = faker.regexify("[a-zA-Z0-9]{7,15}");

        return new PlayerRequestDto.Builder()
                .age(faker.number().numberBetween(17, 60))
                .gender(faker.options().option("male", "female"))
                .login(login)
                .password(password)
                .role(faker.options().option("user", "admin"))
                .screenName(screenName)
                .build();
    }

    public PlayerRequestDto() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public static class Builder {
        private Integer age;
        private String gender;
        private String login;
        private String password;
        private String role;
        private String screenName;

        public Builder() {
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder screenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public PlayerRequestDto build() {
            PlayerRequestDto dto = new PlayerRequestDto();
            dto.setAge(age);
            dto.setGender(gender);
            dto.setLogin(login);
            dto.setPassword(password);
            dto.setRole(role);
            dto.setScreenName(screenName);
            return dto;
        }
    }
}
