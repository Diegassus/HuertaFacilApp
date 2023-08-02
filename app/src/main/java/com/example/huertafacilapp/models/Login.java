package com.example.huertafacilapp.models;

public class Login {
    private String correo;
    private String Clave;

    public Login(String correo, String clave) {
        this.correo = correo;
        Clave = clave;
    }

    public Login(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }
}
