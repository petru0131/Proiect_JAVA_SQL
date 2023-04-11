/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproiect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petru
 */
public class LoginLoad {
    public static Login verif(String user,String pass){
        Login utilizator = new Login(0,"","","","");
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from util where user=? and pass=?");){
             stmt.setString(1,user);
             stmt.setString(2,pass);
             ResultSet rs = stmt.executeQuery();
             
             
             if(rs.next()){
                utilizator = new Login();
                utilizator.setId(rs.getInt("id"));
                utilizator.setRol(rs.getString("rol"));
             }
             
             
     }   
         catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
        return utilizator;
        
      
    }
    
    public List<Login> filtrare (String denumire) {
        
        List<Login> listalog = new ArrayList<Login>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select user,numeutil from util where id=?");){
            
                stmt.setString(1, denumire);
                
                ResultSet rs = stmt.executeQuery();
               
                Login log = null;
                    while (rs.next()){
                        log=new Login();
                        
                        log.setUser(rs.getString("user"));
                        log.setNume(rs.getString("numeutil"));
                        
                        
                        listalog.add(log);
                    }
                    ConnectionJDBC.closeResultSet(rs);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listalog;
        }
     
}
