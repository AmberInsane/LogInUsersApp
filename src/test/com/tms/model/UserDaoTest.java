package com.tms.model;

import com.tms.bean.User;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserDaoTest {
    private static UserDao userDao;
    private static User tempUser;
    private static String email = "test@mail.com";
    private static String password = "1111";

    @AfterClass
    public static void afterClass() {
        userDao.delete(tempUser);
    }

    @Before
    public void initTest() {
        userDao = new UserDao();
        tempUser = new User();
        tempUser.setEmail(email);
        tempUser.setPassword(password);
        userDao.save(tempUser);
    }

    @Test
    public void deleteAndSave() {
        userDao.delete(tempUser);
        assertEquals(0, userDao.getAll().size());
        userDao.save(tempUser);
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void get() {
        Optional<User> userOptional = userDao.get(email, password);
        if (!userOptional.isPresent()) {
           fail();
        } else {
            User foundUser = userOptional.get();
            assertEquals(foundUser, tempUser);
        }

        userOptional = userDao.get("", "");
        if (userOptional.isPresent()) {
            fail();
        }
    }

    @Test
    public void getRecordById() {
        Optional<User> userOptional = userDao.getRecordById(tempUser.getId());
        if (!userOptional.isPresent()) {
            fail();
        } else {
            User foundUser = userOptional.get();
            assertEquals(foundUser, tempUser);
        }

        userOptional = userDao.getRecordById(0);
        if (!userOptional.isPresent()) {
            fail();
        }
    }
}