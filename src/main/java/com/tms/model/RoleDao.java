package com.tms.model;

import com.tms.bean.Role;
import com.tms.bean.User;
import com.tms.util.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDao implements Dao<Role>{
    private Session session = null;
    private Transaction transaction = null;

    final static Logger logger = Logger.getLogger(UserDao.class);

    @Override
    public Optional<Role> get(String name, String password) {
        return Optional.empty();
    }

    public Optional<Role> getByName(String name) {
        Role role = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            role = session.createQuery("FROM Role where name = :name", Role.class)
                    .setParameter("name", name)
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

        return Optional.ofNullable(role);
    }

    @Override
    public List<Role> getAll() {
        List allRoles = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            allRoles = session.createQuery("FROM Role").list();
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

        return allRoles;
    }

    @Override
    public boolean save(Role role) {
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean delete(Role role) {
        return false;
    }
}
