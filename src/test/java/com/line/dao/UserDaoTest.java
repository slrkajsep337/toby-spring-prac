package com.line.dao;

import com.line.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDaoTest {


    @Test
    void addAndSelect() {
        //분리전 test
        UserDao ud = new UserDao();
        String id = "1";
        ud.add(new User(id, "Rara", "11447788"));
        User user = ud.findById(id);
        Assertions.assertEquals("Rara", user.getName());

        //분리후 test
        UserDao ud2 = new UserDao();
        String id2 = "2";
        ud.add(new User(id, "Raraa", "114477889"));
        user = ud.findById(id);
        Assertions.assertEquals("Raraa", user.getName());
    }
}
