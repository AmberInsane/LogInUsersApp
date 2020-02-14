package com.tms.model;

import com.tms.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserDao implements Dao<User> {

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
        return users.add(user);
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