package com.example.huertafacilapp.models;

public class Registro {
    private String correo;
    private String Clave;
    private boolean Hemisferio;

    public Registro(String correo, String clave, boolean hemisferio) {
        this.correo = correo;
        Clave = clave;
        Hemisferio = hemisferio;
    }

    public Registro(String correo) {
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

    public boolean isHemisferio() {
        return Hemisferio;
    }

    public void setHemisferio(boolean hemisferio) {
        Hemisferio = hemisferio;
    }
}
