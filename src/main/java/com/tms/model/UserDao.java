package com.tms.model;

import com.tms.bean.User;
import com.tms.util.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.management.Query;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    private Session session = null;
    private Transaction transaction = null;

    final static Logger logger = Logger.getLogger(UserDao.class);

    //private static List<User> users = UsersHolder.getList();

    public UserDao() {
    }

    @Override
    public Optional<User> get(String email, String password) {
        User user = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = session.createQuery("FROM User where email = :email and password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return Optional.empty();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        //HibernateUtil.shutdown();

        return Optional.ofNullable(user);

        // return users.stream()
        //         .filter(user -> user.getEmail().equals(name))
        //         .filter(user -> user.getPassword().equals(password))
        //         .findFirst();
    }

    public Optional<User> getRecordById(long id) {
        User user = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
        } catch (NoResultException nre) {
            return Optional.empty();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        //HibernateUtil.shutdown();

        return Optional.ofNullable(user);
        //return users.stream()
        //        .filter(user -> user.getId() == id)
        //        .findFirst();
    }

    public boolean hasUserEmail(String email) {
        Long userCount = 0L;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            userCount = (Long) session.createQuery("select count(*) FROM User where email = :cur_email").setParameter("cur_email", email).uniqueResult();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        //HibernateUtil.shutdown();

        return userCount > 0;
        //return users.stream()
        //.filter(user -> user.getId() == id)
        //       .findFirst();
    }

    @Override
    public List<User> getAll() {
        List allUsers = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            allUsers = session.createQuery("FROM User").list();
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
        //HibernateUtil.shutdown();

        return allUsers;
    }

    @Override
    public boolean save(User user) {
        logger.debug("save user " + user.getEmail());
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        //HibernateUtil.shutdown();

        return true;
    }

    @Override
    public boolean update(User user) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    public boolean delete(Long id) {
        Optional<User> user = getRecordById(id);
        if (user.isPresent())
            return delete(user.get());
        else
            return false;
    }

    @Override
    public boolean delete(User user) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }
}

/*class UsersHolder {
    private static List<User> list;

    static List<User> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}*/