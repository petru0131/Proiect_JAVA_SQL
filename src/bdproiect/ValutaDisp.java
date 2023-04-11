/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproiect;

import java.util.Date;

/**
 *
 * @author Petru
 */
public class ValutaDisp {
    private int codV;
    private String denumire;
    private double cursAnt;
    private double cursCump;
    private double cursVanz;
    private int comision;
    private Date data;
    private int codc;
  
    public ValutaDisp() {
    }
    
    public ValutaDisp(int codV, String denumire, double cursAnt, double cursCump, double cursVanz, int comision, Date data,int codc) {
        this.codV = codV;
        this.denumire = denumire;
        this.cursAnt = cursAnt;
        this.cursCump = cursCump;
        this.cursVanz = cursVanz;
        this.comision = comision;
        this.data = data;
        this.codc=codc;
    }

    public int getCodV() {
        return codV;
    }

    public void setCodV(int codV) {
        this.codV = codV;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getCursAnt() {
        return cursAnt;
    }

    public void setCursAnt(double cursAnt) {
        this.cursAnt = cursAnt;
    }

    public double getCursCump() {
        return cursCump;
    }

    public void setCursCump(double cursCump) {
        this.cursCump = cursCump;
    }

    public double getCursVanz() {
        return cursVanz;
    }

    public void setCursVanz(double cursVanz) {
        this.cursVanz = cursVanz;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCodc() {
        return codc;
    }

    public void setCodc(int codc) {
        this.codc = codc;
    }

    @Override
    public String toString() {
        return "ValutaDisp{" + "codV=" + codV + ", denumire=" + denumire + ", cursAnt=" + cursAnt + ", cursCump=" + cursCump + ", cursVanz=" + cursVanz + ", comision=" + comision + ", data=" + data + ", codc=" + codc + '}';
    }

   
  
}
