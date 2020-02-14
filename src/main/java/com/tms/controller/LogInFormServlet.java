package com.tms.controller;

import com.tms.bean.User;
import com.tms.model.Dao;
import com.tms.model.UserDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import org.apache.log4j.Logger;

@WebServlet("/login")
public class LogInFormServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LogInFormServlet.class);
    private Dao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("log in");
        req.setCharacterEncoding("UTF-8");

        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        Optional optionalUser = userDao.get(user.getEmail(), user.getPassword());
        if (optionalUser.isPresent()) {
            User tempUser = (User) optionalUser.get();
            req.getSession().setAttribute("user", tempUser);
            UserAction.logIn(getServletContext(), req, resp, tempUser);
        } else {
            logger.error("error with login: " + user.getEmail());
            req.setAttribute("error", "Указан неверный e-mail или пароль");
            getServletContext().getRequestDispatcher("/loginForm.jsp").forward(req, resp);
        }
    }

    @Override
    public void init() {
        userDao = new UserDao();
    }
}
