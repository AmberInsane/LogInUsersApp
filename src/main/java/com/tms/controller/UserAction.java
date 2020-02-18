package com.tms.controller;

import com.tms.bean.User;
import com.tms.model.UserDao;
import com.tms.util.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.*;

public class UserAction {
    private static UserDao userDao = new UserDao();
    final static Logger logger = Logger.getLogger(UserAction.class);

    public static void logIn(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp, User user) {
        User tempUser = null;

        Optional optionalUser = userDao.get(user.getEmail(), user.getPassword());
        if (optionalUser.isPresent()) {
            tempUser = (User) optionalUser.get();
            req.getSession().setAttribute("user", tempUser);
            showInfoPage(servletContext, req, resp);
        } else {
            emulateError(servletContext, req, resp, "Указан неверный e-mail или пароль");
        }
    }

    public static void showInfoPage(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        List<User> userList;
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", user);

        Map<String, Boolean> allUserRoles = RoleUtil.getAllUserRoles(user);
        allUserRoles.forEach((key, value) -> req.setAttribute(key, value));

        if (allUserRoles.get("show")) {
            userList = userDao.getAll();
            req.setAttribute("userList", userList);
            req.getSession().setAttribute("userList", userList);
        }

        try {
            servletContext.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("error with open page " + e.getMessage());
        }
    }

    public static void logOut(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", null);
        try {
            servletContext.getRequestDispatcher("/loginForm.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("error with open log in page " + e.getMessage());
        }
    }

    public static void editUser(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        long userId = Long.valueOf(req.getParameterValues("id")[0]);
        User user = userDao.getRecordById(userId).get();

        req.setAttribute("user", user);

        Map<String, Boolean> getAllUserRoles = RoleUtil.getAllUserRoles(user);
        getAllUserRoles.forEach((key, value) -> req.setAttribute(key, value));

        try {
            servletContext.getRequestDispatcher("/edit.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.error("error with open update page " + e.getMessage());
        }
    }

    public static void updateUser(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        UserUtil.updateUser(req);
        showInfoPage(servletContext, req, resp);
    }

    public static void deleteUser(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        long userId = Long.valueOf(req.getParameterValues("id")[0]);
        User user = userDao.getRecordById(userId).get();

        User curUser = (User) req.getSession().getAttribute("user");

        userDao.delete(userId);

        if (curUser.equals(user)) {
            emulateError(servletContext, req, resp, "Вы удалили себя");
        }
        showInfoPage(servletContext, req, resp);
    }

    public static void emulateError(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp, String message) {
        logger.error(message);
        req.setAttribute("error", message);
        logOut(servletContext, req, resp);
    }

    public static void saveUser(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) {
        User newUser = UserUtil.saveUser(req);
        if (newUser != null) {
            UserAction.logIn(servletContext, req, resp, newUser);
        } else {
            UserAction.emulateError(servletContext, req, resp, "Пользователь с таким e-mail уже существует");
        }
    }
}
