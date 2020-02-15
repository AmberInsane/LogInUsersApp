package com.tms.model;

import com.tms.bean.User;
import com.tms.util.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    private Session session = null;
    private Transaction transaction = null;

    final static Logger logger = Logger.getLogger(UserDao.class);

    private static List<User> users = UsersHolder.getList();

    public UserDao() {
    }

    @Override
    public Optional<User> get(String name, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(name))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    public Optional<User> getRecordById(long id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public boolean save(User user) {
        logger.debug("bef session");
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();

        return users.add(user);
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return users.remove(user);
    }
}

class UsersHolder {
    private static List<User> list;

    static List<User> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}