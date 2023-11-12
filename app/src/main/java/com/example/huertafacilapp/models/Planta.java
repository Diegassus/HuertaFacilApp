package com.example.huertafacilapp.models;

public class Planta {
  public int id ;
  public String logo ;
  public String portada ;
  public String nombre ;
  public int mes ;
  public int transplante ;
  public int riego ;
  public int cosecha ;
  public int semillado ;
  public int poda ;
  public int tipoId ;
  public Tipo tipo ;
  public int lLuzId ;
  public Luz iluminacion ;

  public Planta() {
  }

  public Planta(int id, String logo, String portada, String nombre, int mes, int transplante, int riego, int cosecha, int semillado, int poda, int tipoId, Tipo categoria, int lLuzId, Luz iluminacion) {
    this.id = id;
    this.logo = logo;
    this.portada = portada;
    this.nombre = nombre;
    this.mes = mes;
    this.transplante = transplante;
    this.riego = riego;
    this.cosecha = cosecha;
    this.semillado = semillado;
    this.poda = poda;
    this.tipoId = tipoId;
    this.tipo = categoria;
    this.lLuzId = lLuzId;
    this.iluminacion = iluminacion;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getPortada() {
    return portada;
  }

  public void setPortada(String portada) {
    this.portada = portada;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getMes() {
    return mes;
  }

  public void setMes(int mes) {
    this.mes = mes;
  }

  public int getTransplante() {
    return transplante;
  }

  public void setTransplante(int transplante) {
    this.transplante = transplante;
  }

  public int getRiego() {
    return riego;
  }

  public void setRiego(int riego) {
    this.riego = riego;
  }

  public int getCosecha() {
    return cosecha;
  }

  public void setCosecha(int cosecha) {
    this.cosecha = cosecha;
  }

  public int getSemillado() {
    return semillado;
  }

  public void setSemillado(int semillado) {
    this.semillado = semillado;
  }

  public int getPoda() {
    return poda;
  }

  public void setPoda(int toda) {
    this.poda = toda;
  }

  public int getTipoId() {
    return tipoId;
  }

  public void setTipoId(int tipoId) {
    this.tipoId = tipoId;
  }

  public Tipo getTipo() {
    return tipo;
  }

  public void setCTipo(Tipo categoria) {
    this.tipo = categoria;
  }

  public int getlLuzId() {
    return lLuzId;
  }

  public void setlLuzId(int lLuzId) {
    this.lLuzId = lLuzId;
  }

  public Luz getIluminacion() {
    return iluminacion;
  }

  public void setIluminacion(Luz iluminacion) {
    this.iluminacion = iluminacion;
  }
}
