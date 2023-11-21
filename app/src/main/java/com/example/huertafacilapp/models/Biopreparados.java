package com.example.huertafacilapp.models;

public class Biopreparados {
  private int id;
  private String foto;
  private String descripcion;
  private String nombre;
  private String ingredientes;

  public Biopreparados() {
  }

  public Biopreparados(int id, String foto, String descripcion, String nombre, String ingredientes) {
    this.id = id;
    this.foto = foto;
    this.descripcion = descripcion;
    this.nombre = nombre;
    this.ingredientes = ingredientes;
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

  public String getIngredientes() {
    return ingredientes;
  }

  public void setIngredientes(String ingredientes) {
    this.ingredientes = ingredientes;
  }
}
