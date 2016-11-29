/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domain.Kajak;
import domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iwan
 */
public class BookingMapper {
    
    public void opretBooking(int brugerid, int kajakid, String booking)throws SQLException{
        
        String sql = "INSERT INTO booking(userid, kajakid, bookingdate) VALUES(?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, brugerid);
        pstmt.setInt(2, kajakid);
        pstmt.setString(3, booking);
        
        pstmt.executeUpdate();
        con.close();
        
    }
    
    public boolean isBooked(Date date, Kajak kajak) throws SQLException {
    
        int kajakid = kajak.getKajakId();
        
        String sql = "SELECT kajakid, bookingdate FROM booking WHERE kajakid = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, kajakid);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            Date bookingdate = rs.getDate("bookingdate");
            if(bookingdate.equals(date)){
                return true;
            }        
            else{
                return false;
            }
        }
        return false;
    }
    
   public List<Kajak> getAvailableKajaks(Date date) throws SQLException{   
       
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
                
                if(!isBooked(date, kajak)){
                    kajaks.add(kajak);
                }
            }
       return kajaks;
   }
    
}
