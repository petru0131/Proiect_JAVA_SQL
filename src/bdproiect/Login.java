/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bdproiect;

/**
 *
 * @author Petru
 */
public class Login {
    private int id;
    private String user;
    private String pass;
    private String nume;
    private String rol;

    public Login() {
    }

    public Login(int id, String user, String pass, String nume, String rol) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.nume = nume;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Login{" + "id=" + id + ", user=" + user + ", pass=" + pass + ", nume=" + nume + ", rol=" + rol + '}';
    }
    
    
}
