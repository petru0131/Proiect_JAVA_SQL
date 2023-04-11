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
public class Beneficiar {
    private int codBe;
    private String nume;
    private String pren;
    private int suma;
    private String valSchimb;
    private String valPrimita;
    private int comBen;
    private Date data;
    private int idc;

    public Beneficiar() {
    }

    public Beneficiar(int codBe, String nume, String pren, int suma, String valSchimb, String valPrimita, int comBen, Date data, int idc) {
        this.codBe = codBe;
        this.nume = nume;
        this.pren = pren;
        this.suma = suma;
        this.valSchimb = valSchimb;
        this.valPrimita = valPrimita;
        this.comBen = comBen;
        this.data = data;
        this.idc = idc;
    }

    public int getCodBe() {
        return codBe;
    }

    public void setCodBe(int codBe) {
        this.codBe = codBe;
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPren() {
        return pren;
    }

    public void setPren(String pren) {
        this.pren = pren;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }

    public String getValSchimb() {
        return valSchimb;
    }

    public void setValSchimb(String valSchimb) {
        this.valSchimb = valSchimb;
    }

    public String getValPrimita() {
        return valPrimita;
    }

    public void setValPrimita(String valPrimita) {
        this.valPrimita = valPrimita;
    }

    public int getComBen() {
        return comBen;
    }

    public void setComBen(int comBen) {
        this.comBen = comBen;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    @Override
    public String toString() {
        return "Beneficiar{" + "codBe=" + codBe + ", nume=" + nume + ", pren=" + pren + ", suma=" + suma + ", valSchimb=" + valSchimb + ", valPrimita=" + valPrimita + ", comBen=" + comBen + ", data=" + data + ", idc=" + idc + '}';
    }

    
    
    
}
