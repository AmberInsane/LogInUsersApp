package com.tms.util;

import com.tms.bean.User;

public class RoleUtil {
    private static final String SUPER_ROLE_NAME = "super";

    public static boolean isUserPlaysRole(User user, String roleName) {
        return user.getRoles().stream().anyMatch(role -> role.getName().equals(SUPER_ROLE_NAME) || role.getName().equals(roleName));
    }
}
