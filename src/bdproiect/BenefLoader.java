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
public class BenefLoader {
    
    
    public List<Beneficiar> load () {
        
        List<Beneficiar> listaBenef = new ArrayList<Beneficiar>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from beneficiar");
                ResultSet rs = stmt.executeQuery();){
                Beneficiar benef = null;
                    while (rs.next()){
                        benef=new Beneficiar();
                        benef.setCodBe(rs.getInt("codBe"));
                        benef.setNume(rs.getString("nume"));
                        benef.setPren(rs.getString("pren"));
                        benef.setSuma(rs.getInt("suma"));
                        benef.setValSchimb(rs.getString("valutaschimb"));
                        benef.setValPrimita(rs.getString("valutaprim"));
                        benef.setComBen(rs.getInt("comisB"));
                        benef.setData(rs.getDate("data"));
                        benef.setIdc(rs.getInt("codc"));
                        
                        listaBenef.add(benef);
                    }
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaBenef;
        }
    
    public List<Beneficiar> loadRaport() {
        
        List<Beneficiar> listaBenef = new ArrayList<Beneficiar>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select sum(suma),valutaschimb from beneficiar group by valutaschimb");
                ResultSet rs = stmt.executeQuery();){
                Beneficiar benef = null;
                    while (rs.next()){
                        benef=new Beneficiar();
                        
                        benef.setSuma(rs.getInt("sum(suma)"));
                        benef.setValSchimb(rs.getString("valutaschimb"));
                        
                        listaBenef.add(benef);
                    }
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaBenef;
        }
    
    
    
    public static void insertBenef(Beneficiar v1){
         try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO beneficiar(codBe,nume,pren,suma,valutaschimb,valutaprim,comisB,data,codc) VALUES(?,?,?,?,?,?,?,?,?)");)
         {
             stmt.setInt(1,v1.getCodBe());
             stmt.setString(2, v1.getNume());
             stmt.setString(3, v1.getPren());
             stmt.setInt(4, v1.getSuma());
             stmt.setString(5, v1.getValSchimb());
             stmt.setString(6, v1.getValPrimita());
             stmt.setInt(7,v1.getComBen());
             java.util.Date utildata= v1.getData();
             java.sql.Date sqldata=new java.sql.Date(utildata.getTime());
             stmt.setDate(8,sqldata);
             stmt.setInt(9,v1.getIdc());
             
             stmt.executeUpdate();
             
         } 
         catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
     
     }
    
    public static void deleteBenef(int idStersBen){
         try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM beneficiar where codBe=?");){
             stmt.setInt(1, idStersBen);
             stmt.executeUpdate();
     }   
         catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         
     }
    
    
    public static void updateBenef(Beneficiar v1,int id){
         try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE beneficiar set codBe=?,nume=?,pren=?,suma=?,valutaschimb=?,valutaprim=?,comisB=?,codc=? where codBe=?");){
            stmt.setInt(1, v1.getCodBe());
            stmt.setString(2, v1.getNume());
            stmt.setString(3, v1.getPren());
            stmt.setInt(4, v1.getSuma());
            stmt.setString(5, v1.getValSchimb());
            stmt.setString(6, v1.getValPrimita());
//            java.util.Date utildata= v1.getData();
//            java.sql.Date sqldata=new java.sql.Date(utildata.getTime());
//            stmt.setDate(7, sqldata);
            stmt.setInt(7, v1.getComBen());
            stmt.setInt(8, v1.getIdc());
            stmt.setInt(9,id);
            
            stmt.executeUpdate();
            
            
     }   catch (ClassNotFoundException ex) {   
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }   
     }
    
    public List<Beneficiar> loadModel (String ben) {
        
        List<Beneficiar> listaBenef = new ArrayList<Beneficiar>();
        
        try(Connection conn = ConnectionJDBC.getConnection();
                PreparedStatement stmt = conn.prepareStatement("select * from beneficiar where nume=? or valutaschimb=? ");){
                stmt.setString(1, ben);
                stmt.setString(2, ben);
                
                ResultSet rs = stmt.executeQuery();
                Beneficiar benef = null;
                    while (rs.next()){
                        benef=new Beneficiar();
                        benef.setCodBe(rs.getInt("codBe"));
                        benef.setNume(rs.getString("nume"));
                        benef.setPren(rs.getString("pren"));
                        benef.setSuma(rs.getInt("suma"));
                        benef.setValSchimb(rs.getString("valutaschimb"));
                        benef.setValPrimita(rs.getString("valutaprim"));
                        benef.setComBen(rs.getInt("comisB"));
                        benef.setData(rs.getDate("data"));
                        benef.setIdc(rs.getInt("codc"));
                        
                        listaBenef.add(benef);
                    }
                    ConnectionJDBC.closeResultSet(rs);
        } catch (ClassNotFoundException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(ValutaDispLoad.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listaBenef;
        }
}
