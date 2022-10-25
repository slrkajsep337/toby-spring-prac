package com.line.dao;

import com.line.domain.User;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker cm;

    public UserDao(ConnectionMaker cm) {
        this.cm = cm;
    }

    public void deleteAll() throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = cm.makeConnection();
            ps = conn.prepareStatement("delete from users");
            ps.executeUpdate();
        } catch(SQLException e) {
            throw e;
        } finally {
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }

            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public int getCount() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int cnt = 0;
        try {
            conn = cm.makeConnection();
            ps = conn.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }

        }
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
            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("id"),
                        rs.getString("name"), rs.getString("password"));
            }

            rs.close();
            ps.close();
            conn.close();

            if(user==null) throw new EmptyResultDataAccessException(1);

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }

}