package com.service;

import com.model.User;
import com.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Awerty
 */
public class UserService {
    Connection con;
    PreparedStatement ps;
    int status;
    public int createUser(User u){
        con = DBConnection.getConnection();
        try {
            ps = con.prepareStatement("INSERT INTO user_info (user_id, user_name, email, password, role, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, u.getId());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getRole());
            ps.setString(6, u.getStatus());
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public int updateUser(User u){
        con = DBConnection.getConnection();
        try {
            ps = con.prepareStatement("UPDATE user_info SET  user_name = ?, email = ?, password= ?, role = ?, status = ? WHERE user_id = ?)");
            
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getRole());
            ps.setString(5, u.getStatus());
            ps.setInt(6, u.getId());
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public int deleteUser(int id){
        con = DBConnection.getConnection();
        try {
            ps = con.prepareStatement("DELETE FROM user_info WHERE user_id = ?)");
            ps.setInt(1, id);
            status = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public ResultSet getResultSet(){
        con = DBConnection.getConnection();
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM user_info");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs; 
    }
    
    public User getById(int id) {
        con = DBConnection.getConnection();
        ResultSet rs = null;
        User u = new User();
        try {
            ps = con.prepareStatement("SELECT * FROM user_info WHERE user_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                u.setId(rs.getInt("user_id"));
                u.setUsername(rs.getString("user_name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setStatus(rs.getString("status"));
                
            }
 
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
}
