/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Kajak;
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
public class KajakMapper {
    
    public void insertKajak(String name, String model, String description, String color, float length) {
    
        try {
            String sql = "INSERT INTO kajak(name, model, description, color, length) VALUES(?,?, ?, ?, ?)";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, model);
            pstmt.setString(3, description);
            pstmt.setString(4, color);
            pstmt.setFloat(5, length);
            pstmt.executeUpdate();
            con.close();
        }
            catch (SQLException ex) {
            ex.printStackTrace(); 
            }
        
    }
    
    public Kajak getKajak(int id) throws SQLException{
            
            String sql = "SELECT kajakid, name, model, description, color, length FROM kajak WHERE userid = ?";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            int kajakid = rs.getInt("kajakid");
            String name = rs.getString("name");
            String model = rs.getString("model");
            String description = rs.getString("description");
            String color = rs.getString("color");
            float length = rs.getFloat("length");
            Kajak kajak = new Kajak(kajakid, name); 
            
        return kajak;
    }
    
    public List<Kajak> getAllKajaks() throws SQLException{
        List<Kajak> kajaks = new ArrayList();
            
            String sql = "SELECT kajakid, name, model, description, color, length FROM kajak";
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("kajakid");
                String name = rs.getString("name");
                String model = rs.getString("model");
                String description = rs.getString("description");
                String color = rs.getString("color");
                Float length = rs.getFloat("length");
                Kajak kajak = new Kajak(id, name, model, description, color, length);
                kajaks.add(kajak);
            }
            
        return kajaks;
    }
    
    
    public static void main(String[] args) throws SQLException {
        KajakMapper km = new KajakMapper();        
        List<Kajak> kajaks = km.getAllKajaks();
        
        for (Kajak kajak : kajaks) {
            System.out.println(kajak.getName() + " " + kajak.getModel() + " " + kajak.getDescription() + " "
            + kajak.getColor() + " " +kajak.getLength());
        }
    }
    
}
