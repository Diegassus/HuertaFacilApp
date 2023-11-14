package com.example.huertafacilapp.models;

public class Documento {
  private int Id;
  private String Titulo;

  public Documento() {
  }

  public Documento(int id, String titulo) {
    Id = id;
    Titulo = titulo;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getTitulo() {
    return Titulo;
  }

  public void setTitulo(String titulo) {
    Titulo = titulo;
  }
}
