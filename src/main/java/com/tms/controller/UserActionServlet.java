package com.tms.controller;

import com.tms.util.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userAction")
public class UserActionServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(UserActionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    public void doAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        switch (action) {
            case ("logIn"):
                logger.info("log in");
                UserAction.logIn(getServletContext(), req, resp, 0L);
                break;
            case ("logOut"):
                logger.info("log out");
                UserAction.logOut(getServletContext(), req, resp);
                break;
            case ("save"):
                UserAction.saveUser(getServletContext(), req, resp);
                break;
            case ("edit"):
                UserAction.editUser(getServletContext(), req, resp);
                break;
            case ("update"):
                UserAction.updateUser(getServletContext(), req, resp);
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
    public void destroy() {
        HibernateUtil.shutdown();
    }
}
