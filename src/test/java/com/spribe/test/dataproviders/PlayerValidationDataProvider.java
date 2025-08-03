package com.spribe.test.dataproviders;

import com.spribe.test.rest.services.player.dto.requests.PlayerRequestDto;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class PlayerValidationDataProvider {

    @DataProvider(name = "invalidPlayerDataProvider")
    public static Object[][] invalidPlayerDataProvider() {
        List<Object[]> cases = new ArrayList<>();

        PlayerRequestDto lessAge = PlayerRequestDto.generatePlayer();
        lessAge.setAge(15);
        cases.add(new Object[]{lessAge, "Age less than 16"});

        PlayerRequestDto greaterAge = PlayerRequestDto.generatePlayer();
        greaterAge.setAge(61);
        cases.add(new Object[]{greaterAge, "Age greater than 60"});

        PlayerRequestDto invalidRole = PlayerRequestDto.generatePlayer();
        invalidRole.setRole("supervisor");
        cases.add(new Object[]{invalidRole, "Invalid role - supervisor"});

        PlayerRequestDto shortPwd = PlayerRequestDto.generatePlayer();
        shortPwd.setPassword("abc12");
        cases.add(new Object[]{shortPwd, "Password too short"});

        PlayerRequestDto longPwd = PlayerRequestDto.generatePlayer();
        longPwd.setPassword("abc123456789012345");
        cases.add(new Object[]{longPwd, "Password too long"});

        PlayerRequestDto nonLatinPwd = PlayerRequestDto.generatePlayer();
        nonLatinPwd.setPassword("пароль123");
        cases.add(new Object[]{nonLatinPwd, "Password with non-latin characters"});

        PlayerRequestDto genderOther = PlayerRequestDto.generatePlayer();
        genderOther.setGender("other");
        cases.add(new Object[]{genderOther, "Invalid gender - other"});

        PlayerRequestDto genderEmpty = PlayerRequestDto.generatePlayer();
        genderEmpty.setGender("");
        cases.add(new Object[]{genderEmpty, "Empty gender"});

        return cases.toArray(new Object[0][0]);
    }
}

