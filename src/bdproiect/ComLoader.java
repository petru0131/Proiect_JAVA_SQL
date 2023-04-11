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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petru
 */
public class ComLoader {
    public List<Comenzi> load() {
        
        List<Comenzi> listaCom = new ArrayList<Comenzi>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select idcom, user,numeutil,rol,comanda,dataCom,u.id from comenzi c,util u where c.id=u.id");
                ResultSet rs = stmt.executeQuery();){
                Comenzi com = null;
                    while (rs.next()){
                        com=new Comenzi();
                        com.setIdC(rs.getInt("idcom"));
                        com.setUser(rs.getString("user"));
                        com.setNume(rs.getString("numeutil"));
                        com.setRol(rs.getString("rol"));
                        com.setDenC(rs.getString("comanda"));
                        com.setDataC(rs.getDate("dataCom"));
                        com.setIdU(rs.getInt("id"));
                        
                       listaCom.add(com);
                    }
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaCom;
        }
    
    
    public static void adaugaCom(int id,String comanda) throws SQLException, ClassNotFoundException {
        
        List<Comenzi> listaCom = new ArrayList<Comenzi>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("insert into comenzi(comanda,dataCom,id) values(?,?,?)");)
                
                
                    {
                        
                        stmt.setString(1,comanda);
                        java.util.Date utildata= new Date();
                        java.sql.Date sqldata=new java.sql.Date(utildata.getTime());
                        stmt.setDate(2, sqldata);
                        stmt.setInt(3,id);
                       stmt.executeUpdate();
                    }
       
    catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    }
