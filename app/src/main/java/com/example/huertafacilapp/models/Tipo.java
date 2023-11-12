package com.example.huertafacilapp.models;

public class Tipo {
  public int id ;
  public String nombre ;

  public Tipo(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  public Tipo() {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
