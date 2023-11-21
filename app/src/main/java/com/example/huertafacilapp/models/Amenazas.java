package com.example.huertafacilapp.models;

public class Amenazas {
  private int id;
  private String foto;
  private String descripcion;
  private String nombre;

  public Amenazas() {
  }

  public Amenazas(int id, String foto, String descripcion, String nombre) {
    this.id = id;
    this.foto = foto;
    this.descripcion = descripcion;
    this.nombre = nombre;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
