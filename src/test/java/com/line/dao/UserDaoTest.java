package com.line.dao;

import com.google.protobuf.Empty;
import com.line.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
public class UserDaoTest {

//    @Test
//    void addAndSelect() {
        //분리전 test
//        UserDao ud = new UserDao();
//        String id = "1";
//        ud.add(new User(id, "Rara", "11447788"));
//        User user = ud.findById(id);
//        Assertions.assertEquals("Rara", user.getName());
//
//        //분리후 test
//        UserDao ud2 = new UserDao();
//        String id2 = "2";
//        ud.add(new User(id, "Raraa", "114477889"));
//        user = ud.findById(id);
//        Assertions.assertEquals("Raraa", user.getName());

//        //interface적용 후
//        UserDao ud = new UserDao(new ConnectionA());
//        String id = "3";
//        ud.add(new User(id, "Raro", "114788"));
//        User user = ud.findById(id);
//        Assertions.assertEquals("Raro", user.getName());

        //factory적용 후
//        UserDaoFactory udFactory = new UserDaoFactory();
//        UserDao ud = new UserDaoFactory().userDao();
//        String id = "4";
//        ud.add(new User(id, "daro", "11554788"));
//        User user = ud.findById(id);
//        Assertions.assertEquals("daro", user.getName());
//    }


    @Autowired
    ApplicationContext context;
    UserDao userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUP() throws SQLException {
        this.userDao = context.getBean("awsUserDao", UserDao.class);

        this.user1 = new User("1","a","1234");
        this.user2 = new User("2","b","1235");
        this.user3 = new User("3","c","1236");

    }

    @Test
    void addAndSelect() throws SQLException {
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        String id = "7";
        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        User user = userDao.findById(user1.getId());

        assertEquals(user1.getName(), user.getName());
        assertEquals(user1.getPassword(), user.getPassword());
    }

    @Test
    @DisplayName("User가 null인 경우 Exception")
    void userNULL() {
        assertThrows(EmptyResultDataAccessException.class, ()-> {
            userDao.deleteAll();
            userDao.findById("0");
        });
    }

}
