package com.example.BTL.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.BTL.DAO;


public class UserDAO extends DAO{

    public UserDAO() {
        super();
    }
    public boolean checkLogin(User user) {
            boolean result = false;
            String sql = "SELECT * FROM tblUser WHERE username = ? AND password = ?";
            try {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                            user.setId(rs.getInt("id"));
                            user.setName(rs.getString("name"));
                            user.setRole(rs.getString("role"));
                            result = true;
                    }
            }catch(Exception e) {
                    e.printStackTrace();
            }
            return result;
    }
    public String addUser(User user) {
            String sql = "INSERT INTO `qldt`.`tblUser` (`username`, `password`, `name`, `role`) VALUES (?, ?, ?, ?);";
            String checkSql = "SELECT * FROM tblUser where username = '"+user.getUsername()+"';";
            int result = 0;
            try {
                    PreparedStatement ps = con.prepareStatement(checkSql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                    ResultSet resultSet = ps.executeQuery();
                    if(resultSet.absolute(1)) {
                            return "username đã được sử dụng";
                    }
                    else {
                            ps = con.prepareStatement(sql);
                            ps.setString(1, user.getUsername());
                            ps.setString(2, user.getPassword());
                            ps.setString(3, user.getName());
                            ps.setString(4, "user");
                            result = ps.executeUpdate();
                            return "Đăng ký thành công";
                    }

            }catch (Exception e) {
                    e.printStackTrace();
            }
            return "Đăng ký không thành công";
    }

    public User selectUser(int id) {
            User user = new User();
            try {
                    PreparedStatement ps = con.prepareStatement("select * from tblUser where id = ?");
                    ps.setInt(1, id);
                    ResultSet result = ps.executeQuery();
                    while(result.next()) {
                            user.setId(result.getInt("id"));
                            user.setUsername(result.getString("username"));
                            user.setPassword(result.getString("password"));
                            user.setName(result.getString("name"));
                            user.setRole(result.getString("role"));
                    }
            } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
            }
            return user;
    }
    
}
