package com.tms.model;

import com.tms.bean.UserInfo;

import java.util.List;
import java.util.Optional;

public class UserInfoDao implements Dao<UserInfo> {
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
