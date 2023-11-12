package com.example.huertafacilapp.models;

public class Luz {
  public int id ;
  public String nombre ;

  public Luz() {

  }

  public Luz(int id, String nombre) {
    this.id = id;
    this.nombre = nombre;
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