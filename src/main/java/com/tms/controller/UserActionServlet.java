package com.tms.controller;

import com.tms.bean.User;
import com.tms.model.Dao;
import com.tms.model.UserDao;
import com.tms.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/userAction")
public class UserActionServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(UserActionServlet.class);
    private Dao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        switch (action) {
            case ("logIn"):
                logger.info("log in");
                UserAction.logIn(getServletContext(), req, resp, user);
                break;
            case ("logOut"):
                logger.info("log out");
                UserAction.logOut(getServletContext(), req, resp);
                break;
            case ("save"):
                UserAction.saveUser(getServletContext(), req, resp);
                break;
            case ("delete"):
                UserAction.deleteUser(getServletContext(), req, resp);
                break;
            case ("cancel"):
                UserAction.showInfoPage(getServletContext(), req, resp);
                break;
        }
    }

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    public void destroy() {
        HibernateUtil.shutdown();
    }
}
