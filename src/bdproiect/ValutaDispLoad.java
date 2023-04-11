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

public class ValutaDispLoad {
    Locatii l=new Locatii();
     public List<ValutaDisp> load() {
        
        List<ValutaDisp> listaValute = new ArrayList<ValutaDisp>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from valuta");
                ResultSet rs = stmt.executeQuery();){
                ValutaDisp valuta = null;
                    while (rs.next()){
                        valuta=new ValutaDisp();
                        valuta.setCodV(rs.getInt("codv"));
                        valuta.setDenumire(rs.getString("denumire"));
                        valuta.setCursAnt(rs.getDouble("cursant"));
                        valuta.setCursCump(rs.getDouble("curscump"));
                        valuta.setCursVanz(rs.getDouble("cursvanz"));
                        valuta.setComision(rs.getInt("comision"));
                        valuta.setData(rs.getDate("ziua"));
                        valuta.setCodc(rs.getInt("codc"));
                        
                        listaValute.add(valuta);
                    }
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaValute;
        }
     
     public List<ValutaDisp> loadRaport() {
        
        List<ValutaDisp> listaValute = new ArrayList<ValutaDisp>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select codc,count(denumire) from valuta  GROUP BY codc");
                ResultSet rs = stmt.executeQuery();){
                ValutaDisp valuta = null;
                    while (rs.next()){
                        valuta=new ValutaDisp();
                        valuta.setCodc(rs.getInt("codc"));
                        valuta.setDenumire(rs.getString("count(denumire)"));
                        
                        listaValute.add(valuta);
                    }
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaValute;
        }
     
     public List<ValutaDisp> loadCombo() {
        
        List<ValutaDisp> listaValute = new ArrayList<ValutaDisp>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select distinct denumire from valuta");
                ResultSet rs = stmt.executeQuery();){
                ValutaDisp valuta = null;
                    while (rs.next()){
                        valuta=new ValutaDisp();
                        
                        valuta.setDenumire(rs.getString("denumire"));
                        
                        listaValute.add(valuta);
                    }
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaValute;
        }
     
     public static void loadComb(){
         
     }
     
     public static void insertValuta(ValutaDisp v1){
         try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO valuta(codv,denumire,cursant,curscump,cursvanz,comision,ziua,codc) VALUES(?,?,?,?,?,?,?,?)");)
         {
             stmt.setInt(1,v1.getCodV());
             stmt.setString(2, v1.getDenumire());
             stmt.setDouble(3, v1.getCursAnt());
             stmt.setDouble(4, v1.getCursCump());
             stmt.setDouble(5, v1.getCursVanz());
             stmt.setInt(6, v1.getComision());
             java.util.Date utildata= v1.getData();
             java.sql.Date sqldata=new java.sql.Date(utildata.getTime());
             stmt.setDate(7,sqldata);
             stmt.setInt(8,v1.getCodc());
             
             stmt.executeUpdate();
             
         } 
         catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
     
     }
     
     public static void deleteValuta(int idStersVal){
         try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM valuta where codv=?");){
             stmt.setInt(1, idStersVal);
             stmt.executeUpdate();
     }   
         catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         
     }
     
     
     public static void updateValuta(ValutaDisp v1,int id){
         try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE valuta set codv=?,denumire=?,cursant=?,curscump=?,cursvanz=?,comision=?,codc=? where codv=?");){
            stmt.setInt(1, v1.getCodV());
            stmt.setString(2, v1.getDenumire());
            stmt.setDouble(3, v1.getCursAnt());
            stmt.setDouble(4, v1.getCursCump());
            stmt.setDouble(5, v1.getCursVanz());
            stmt.setInt(6, v1.getComision());
//            java.util.Date utildata= v1.getData();
//            java.sql.Date sqldata=new java.sql.Date(utildata.getTime());
//            stmt.setDate(7, sqldata);
            stmt.setInt(7, v1.getCodc());
            stmt.setInt(8,id);
            
            stmt.executeUpdate();
            
            
     }   catch (ClassNotFoundException ex) {   
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }   
     }
     
     public List<ValutaDisp> filtrare (String denumire) {
        
        List<ValutaDisp> listaValute = new ArrayList<ValutaDisp>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from valuta where denumire=?");){
            
                stmt.setString(1, denumire);
                
                ResultSet rs = stmt.executeQuery();
               
                ValutaDisp valuta = null;
                    while (rs.next()){
                        valuta=new ValutaDisp();
                        valuta.setCodV(rs.getInt("codv"));
                        valuta.setDenumire(rs.getString("denumire"));
                        valuta.setCursAnt(rs.getDouble("cursant"));
                        valuta.setCursCump(rs.getDouble("curscump"));
                        valuta.setCursVanz(rs.getDouble("cursvanz"));
                        valuta.setComision(rs.getInt("comision"));
                        valuta.setData(rs.getDate("ziua"));
                        valuta.setCodc(rs.getInt("codc"));
                        
                        listaValute.add(valuta);
                    }
                    ConnectionJDBC.closeResultSet(rs);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaValute;
        }
     
     
}
     
