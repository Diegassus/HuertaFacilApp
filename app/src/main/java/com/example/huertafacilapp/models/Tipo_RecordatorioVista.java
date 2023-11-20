package com.example.huertafacilapp.models;

public class Tipo_RecordatorioVista {
  private int id;
  private String titulo;

  public Tipo_RecordatorioVista() {
  }

  public Tipo_RecordatorioVista(int id, String titulo) {
    this.id = id;
    this.titulo = titulo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  @Override
  public String toString() {
    return titulo;
  }
}
