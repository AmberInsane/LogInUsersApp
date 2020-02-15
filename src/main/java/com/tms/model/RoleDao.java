package com.tms.model;

import com.tms.bean.Role;
import com.tms.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class RoleDao implements Dao<Role>{

    @Override
    public Optional<Role> get(String name, String password) {
        return Optional.empty();
    }

    @Override
    public List<Role> getAll() {
        return null;
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
