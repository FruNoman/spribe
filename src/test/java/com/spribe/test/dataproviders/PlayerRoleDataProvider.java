package com.spribe.test.dataproviders;

import com.spribe.test.rest.services.player.enums.UserRole;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class PlayerRoleDataProvider {
    @DataProvider(name = "validRolesProvider")
    public static Object[][] validRolesProvider() {
        return Arrays.asList(UserRole.SUPERVISOR, UserRole.ADMIN).stream()
                .map(role -> new Object[]{role})
                .toArray(Object[][]::new);
    }

    @DataProvider(name = "nonCreatePermissionProvide")
    public static Object[][] nonCreatePermissionProvide() {
        return Arrays.asList(UserRole.INVALID_ROLE,UserRole.USER).stream()
                .map(role -> new Object[]{role})
                .toArray(Object[][]::new);
    }
}
