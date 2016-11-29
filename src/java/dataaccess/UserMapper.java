/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iwan
 */
public class UserMapper {
    
    public void createAndInsertNewUser(String username, String password){
        
        try {
            String sql = "INSERT INTO user(username, password) VALUES(?,?)";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            con.close();
        }
            catch (SQLException ex) {
            ex.printStackTrace(); 
            }
    }
    
    public User getUser(int id) throws SQLException{
            
            String sql = "SELECT userid, username FROM user WHERE userid = ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            int userid = rs.getInt("userid");
            String username = rs.getString("username");
            User user = new User(userid, username); 
            
        return user;
    }
    
    public int getUserIDByName(String username) throws SQLException{
       int userid = 0;     
            String sql = "SELECT userid, username FROM user WHERE username = ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("username");
                if(name.equals(username)){
                    userid = rs.getInt("userid"); 
                }
            }
        return userid;
    }
    
    public List<User> getAllUsers() throws SQLException{
        List<User> users = new ArrayList();
            
            String sql = "SELECT userid, username FROM user";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("userid");
                String username = rs.getString("username");
                User user = new User(id, username);
                users.add(user);
            }
            
        return users;
    }
    
    public boolean authenticateUser(String username, String password){
        try {
            String sql = "SELECT username, password FROM user WHERE username = ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String passwordFromDB = rs.getString("password");
                if(passwordFromDB.equals(password)){
                    return true;
                }
            }else{
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
    
    public boolean userAllreadyExists(String username){
        try {
            String sql = "SELECT username, password FROM user";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String usernameFromDB = rs.getString("username");
                if(usernameFromDB.equals(username)){
                    return true;
                }
                else{
                return false;
            }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
    
    public static void main(String[] args) throws SQLException {
        UserMapper um = new UserMapper();
       boolean isAuthenticated = um.authenticateUser("Bruger1", "kode1");
       if(isAuthenticated)
           System.out.println("YEASS we are in");
        else
            System.out.println("Oh no no access");
        List<User> users = um.getAllUsers();
        for (User user : users) {
            String name = user.getUsername();
            System.out.println(name);
            System.out.println(um.getUserIDByName(name));
        }
    }
    
}
