package com.tms.controller;

import com.tms.bean.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/userAction")
public class UserActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (req.getParameter("logOut") != null) {
            UserAction.logOut(getServletContext(), req, resp);
        } else if (req.getParameter("edit") != null) {
            UserAction.editUser(getServletContext(), req, resp);
        } else if (req.getParameter("delete") != null) {
            UserAction.deleteUser(getServletContext(), req, resp, user);
        }
    }
}
