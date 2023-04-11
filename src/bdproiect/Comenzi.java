/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproiect;

import java.sql.Date;

/**
 *
 * @author Petru
 */
public class Comenzi extends Login {
    private int idC;
    private String denC;
    private Date dataC;
    private int idU;

    public Comenzi() {
    }

    public Comenzi(int idC, String denC, Date dataC, int idU) {
        this.idC = idC;
        this.denC = denC;
        this.dataC = dataC;
        this.idU = idU;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getDenC() {
        return denC;
    }

    public void setDenC(String denC) {
        this.denC = denC;
    }

    public Date getDataC() {
        return dataC;
    }

    public void setDataC(Date dataC) {
        this.dataC = dataC;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    

    @Override
    public String toString() {
        return "Comenzi{" + "idC=" + idC + ", denC=" + denC + ", dataC=" + dataC + ", idU=" + idU + '}';
    }

   
    
    
}
