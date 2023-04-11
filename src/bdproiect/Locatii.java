/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproiect;

/**
 *
 * @author Petru
 */
public class Locatii  {
    private int codc;
    private String numeL;
    private String strada;
    private String oras;
    private String judet;

    public Locatii() {
    }

    public Locatii(int codc, String numeL, String strada, String oras, String judet) {
        this.codc = codc;
        this.numeL = numeL;
        this.strada = strada;
        this.oras = oras;
        this.judet = judet;
    }

    public int getCodc() {
        return codc;
    }

    public void setCodc(int codc) {
        this.codc = codc;
    }

    public String getNumeL() {
        return numeL;
    }

    public void setNumeL(String numeL) {
        this.numeL = numeL;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    @Override
    public String toString() {
        return "Locatii{" + "codc=" + codc + ", numeL=" + numeL + ", strada=" + strada + ", oras=" + oras + ", judet=" + judet + '}';
    }
    
    
    
}
