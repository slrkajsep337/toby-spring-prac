package com.line.dao;



public class UserDaoFactory {

    private ConnectionMaker connectionMaker() {
        ConnectionA connA = new ConnectionA();

        return connA;
    }

    public UserDao userDao() {
//        ConnectionA connA = new ConnectionA();
        UserDao userDao = new UserDao(connectionMaker());

        return userDao;
    }
}
