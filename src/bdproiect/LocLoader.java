/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproiect;


import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Petru
 */
public class LocLoader {
    
    
    public List<Locatii> load () {
        
        List<Locatii> listaLoc = new ArrayList<Locatii>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from casadeschimb");
                ResultSet rs = stmt.executeQuery();){
                Locatii loc = null;
                    while (rs.next()){
                        loc=new Locatii();
                        loc.setCodc(rs.getInt("codc"));
                        loc.setNumeL(rs.getString("nume"));
                        loc.setStrada(rs.getString("strada"));
                        loc.setOras(rs.getString("oras"));
                        loc.setJudet(rs.getString("judet"));
                        
                        
                        listaLoc.add(loc);
                    }
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaLoc;
        }
    
    
    public static void insertLoc(Locatii v1){
         try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO casadeschimb(codc,nume,strada,oras,judet) VALUES(?,?,?,?,?)");)
         {
             stmt.setInt(1,v1.getCodc());
             stmt.setString(2, v1.getNumeL());
             stmt.setString(3, v1.getStrada());
             stmt.setString(4, v1.getOras());
             stmt.setString(5, v1.getJudet());
             
             stmt.executeUpdate();
             
         } 
         catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
     
     }
}
