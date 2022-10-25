package com.line.dao;

import com.line.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker cm;

    public UserDao() {
        cm = new ConnectionMaker();

    }

    public void add(User user) {
        Map<String, String> env = System.getenv();
        try {
            Connection conn = cm.makeConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO USERS(id, name, password) VALUES(?,?,?);");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String id) {
        Map<String, String> env = System.getenv();
        Connection conn;
        try {
            conn = cm.makeConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from users where id=?"
            );
            ps.setString(1,id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User(rs.getString("id"),
                    rs.getString("name"), rs.getString("password"));
            rs.close();
            ps.close();
            conn.close();

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UserDao ud = new UserDao();
        User user = ud.findById("1");
        System.out.println(user.getName());
    }
}