package com.tms.model;

import com.tms.bean.UserInfo;
import com.tms.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserInfoDao implements Dao<UserInfo> {
    private Session session = null;
    private Transaction transaction = null;
    final static Logger logger = Logger.getLogger(UserInfoDao.class);

    @Override
    public Optional<UserInfo> get(String name, String password) {
        return Optional.empty();
    }

    @Override
    public List<UserInfo> getAll() {
        return null;
    }

    @Override
    public boolean save(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean update(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean delete(UserInfo userInfo) {
        return false;
    }
}
