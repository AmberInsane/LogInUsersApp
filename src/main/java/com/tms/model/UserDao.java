package com.tms.model;

import com.tms.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private List<User> users = UsersHolder.getList();

    public UserDao() {
    }

    @Override
    public Optional<User> get(String name, String password) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
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