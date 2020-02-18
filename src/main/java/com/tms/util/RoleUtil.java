package com.tms.util;

import com.tms.bean.Role;
import com.tms.bean.User;
import com.tms.model.RoleDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoleUtil {
    public static final String SUPER_ROLE_NAME = "all";
    public static final String DEFAULT_ROLE_NAME = "default";

    private static RoleDao roleDao = new RoleDao();

    public static boolean isUserPlaysRole(User user, String roleName) {
        return user.getRoles().stream().anyMatch(role -> role.getName().equals(SUPER_ROLE_NAME) || role.getName().equals(roleName));
    }

    public static Map<String, Boolean> getAllUserRoles(User user) {
        Map<String, Boolean> rolesMap = new HashMap<>();

        rolesMap.put(SUPER_ROLE_NAME, isUserPlaysRole(user, SUPER_ROLE_NAME));
        rolesMap.put("show", isUserPlaysRole(user, "show"));
        rolesMap.put("edit", isUserPlaysRole(user, "edit"));
        rolesMap.put("delete", isUserPlaysRole(user, "delete"));
        rolesMap.put(DEFAULT_ROLE_NAME, isUserPlaysRole(user, DEFAULT_ROLE_NAME));

        return rolesMap;
    }

    public static Role getRole(String name) {
        Optional<Role> userRole = roleDao.getByName(name);
        Role newRole;
        if (userRole.isPresent()) {
            newRole = userRole.get();
        } else {
            newRole = new Role();
            newRole.setName(name);
        }
        return newRole;
    }
}
