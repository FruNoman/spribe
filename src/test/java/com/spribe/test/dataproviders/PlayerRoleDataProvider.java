package com.spribe.test.dataproviders;

import com.spribe.test.rest.services.player.enums.UserRole;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class PlayerRoleDataProvider {
    @DataProvider(name = "validRolesProvider")
    public static Object[][] validRolesProvider() {
        return UserRole.validCreationRoles().stream()
                .map(role -> new Object[]{role})
                .toArray(Object[][]::new);
    }
}
