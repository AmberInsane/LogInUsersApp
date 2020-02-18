package com.tms.util;

import com.tms.bean.Role;
import com.tms.bean.User;
import com.tms.bean.UserInfo;
import com.tms.model.Dao;
import com.tms.model.RoleDao;
import com.tms.model.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class UserUtil {
    private static final String SUPER_ROLE_NAME = RoleUtil.SUPER_ROLE_NAME;
    private static final String DEFAULT_ROLE_NAME = RoleUtil.DEFAULT_ROLE_NAME;

    private static UserDao userDao = new UserDao();
    private static RoleDao roleDao = new RoleDao();

    final static Logger logger = Logger.getLogger(UserUtil.class);

    public static User saveUser(HttpServletRequest req) {
        User newUser = createUserFromParams(req);
        if (!userDao.hasUserEmail(newUser.getEmail())) {
            userDao.save(newUser);
        } else newUser = null;
        return newUser;
    }

    public static Long getSessionUserId(HttpServletRequest req) {
        return (Long) req.getSession().getAttribute("userId");
    }

    public static Optional<User> getSessionUser(HttpServletRequest req) {
        Long id = getSessionUserId(req);
        return userDao.getRecordById(id);
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
                    roles.add(RoleUtil.getRole(SUPER_ROLE_NAME));
                    break;
                case ("isShow"):
                    roles.add(RoleUtil.getRole("show"));
                    break;
                case ("isEdit"):
                    roles.add(RoleUtil.getRole("edit"));
                    break;
                case ("isDelete"):
                    roles.add(RoleUtil.getRole("delete"));
                    break;
            }
        }

        if (roles.size() == 0) {
            roles.add(RoleUtil.getRole(DEFAULT_ROLE_NAME));
        }

        logger.info("user " + user.getEmail() + " created");
        return user;
    }

    public static void updateUser(HttpServletRequest req) {
        User user = createUserFromParams(req);

        long userId = Long.valueOf(req.getParameterValues("id")[0]);
        User oldUser = userDao.getRecordById(userId).get();
        userDao.update(mergeUsers(user, oldUser));
    }

    private static User mergeUsers(User user, User oldUser) {
        UserInfo newInfo = user.getUserInfo();
        UserInfo oldInfo = oldUser.getUserInfo();

        if (newInfo.getFirstName() != null) {
            oldInfo.setFirstName(newInfo.getFirstName());
        }

        if (newInfo.getLastName() != null) {
            oldInfo.setLastName(newInfo.getLastName());
        }

        if ((newInfo.getAddress() != null)) {
            oldInfo.setAddress(newInfo.getAddress());
        }
        oldUser.setRoles(user.getRoles());

        return oldUser;
    }
}
