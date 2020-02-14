package com.tms.controller;

import com.tms.bean.User;
import com.tms.model.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class UserAction {
    private static UserDao userDao = new UserDao();

    public static void logIn(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
       req.getSession().setAttribute("user", user);

        List<User> userList;
        if (user.getIsAdmin()) {
            userList = userDao.getAll();
        } else {
            userList = Collections.singletonList(user);
        }

        req.setAttribute("userList", userList);
        req.getSession().setAttribute("userList", userList);

        servletContext.getRequestDispatcher("/userInfo.jsp").forward(req, resp);
    }

    public static void logOut(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("userList", null);
        req.getSession().setAttribute("user", null);
        resp.sendRedirect("loginForm.jsp");
    }

    public static void editUser(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // req.getSession().setAttribute("userList", null);
       // req.getSession().setAttribute("user", null);
       // resp.sendRedirect("loginForm.jsp");
        System.out.println("edit");
    }

    public static void deleteUser(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        // req.getSession().setAttribute("userList", null);
        // req.getSession().setAttribute("user", null);
        // resp.sendRedirect("loginForm.jsp");
        System.out.println("delete");
    }
}
