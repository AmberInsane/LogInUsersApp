package com.tms.util;

import com.tms.bean.Role;
import com.tms.bean.User;
import com.tms.bean.UserInfo;
import com.tms.model.Dao;
import com.tms.model.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class UserUtil {
    private static final String SUPER_ROLE_NAME = "super";
    private static final String DEFAULT_ROLE_NAME = "default";

    private static UserDao userDao = new UserDao();

    final static Logger logger = Logger.getLogger(UserUtil.class);

    public static User saveUser(HttpServletRequest req) {
        User newUser = createUserFromParams(req);
        if (!userDao.hasUserEmail(newUser.getEmail())) {
            userDao.save(newUser);
        } else newUser = null;
        return newUser;
    }

    public static User createUserFromParams(HttpServletRequest req) {
        String paramName = "";
        String paramValue = "";

        User user = new User();
        UserInfo userInfo = new UserInfo();

        user.setUserInfo(userInfo);

        List<Role> roles = new ArrayList<>();
        user.setRoles(roles);

        Enumeration params = req.getParameterNames();
        while (params.hasMoreElements()) {
            paramName = (String) params.nextElement();
            paramValue = req.getParameter(paramName);

            switch (paramName) {
                case ("email"):
                    user.setEmail(paramValue);
                    break;
                case ("password"):
                    user.setPassword(paramValue);
                    break;
                case ("firstName"):
                    userInfo.setFirstName(paramValue);
                    break;
                case ("lastName"):
                    userInfo.setLastName(paramValue);
                    break;
                case ("sex"):
                    userInfo.setSex(paramValue);
                    break;
                case ("address"):
                    userInfo.setAddress(paramValue);
                    break;
                case ("isSuper"):
                    Role userRole = new Role();
                    userRole.setName(SUPER_ROLE_NAME);
                    roles.add(userRole);
                    break;
            }
        }

        if (roles.size() == 0) {
            Role userRole = new Role();
            userRole.setName(DEFAULT_ROLE_NAME);
            roles.add(userRole);
        }

        logger.info("user " + user.getEmail() + " created");
        return user;
    }
}
