package com.tms.controller;

import com.tms.bean.User;
import com.tms.model.UserDao;
import com.tms.util.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserAction {
    private static UserDao userDao = new UserDao();
    final static Logger logger = Logger.getLogger(UserAction.class);

    public static void logIn(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        User tempUser = null;
        
        Optional optionalUser = userDao.get(user.getEmail(), user.getPassword());
        if (optionalUser.isPresent()) {
            tempUser = (User) optionalUser.get();
            req.getSession().setAttribute("user", tempUser);
        } else {
            logger.error("error with login: " + user.getEmail());
            req.setAttribute("error", "Указан неверный e-mail или пароль");
            resp.sendRedirect("/loginForm.jsp");
        }
        
        req.getSession().setAttribute("user", tempUser);
        req.setAttribute("user", tempUser);

        List<User> userList;
        if (RoleUtil.isUserPlaysRole(tempUser,"show")) {
            userList = userDao.getAll();
            req.setAttribute("userList", userList);
            req.getSession().setAttribute("userList", userList);
        }

        servletContext.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
    }

    public static void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("userList", null);
        req.getSession().setAttribute("user", null);
        resp.sendRedirect("loginForm.jsp");
    }

    public static void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // req.getSession().setAttribute("userList", null);
       // req.getSession().setAttribute("user", null);
       // resp.sendRedirect("loginForm.jsp");
        System.out.println("edit");
        System.out.println(req.getAttribute("id"));
        resp.sendRedirect("loginForm.jsp");
    }

    public static void deleteUser(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        // req.getSession().setAttribute("userList", null);
        // req.getSession().setAttribute("user", null);
        // resp.sendRedirect("loginForm.jsp");
        System.out.println("delete");
    }
}
