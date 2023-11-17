package com.example.huertafacilapp.models;

import androidx.annotation.NonNull;

public class DocumentoVista {

  private int id;
  private String titulo;

  public DocumentoVista() {
  }

  public DocumentoVista(int id, String titulo) {
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

  @NonNull
  @Override
  public String toString() {
    return "Documento{" + "Id=" + id + ", Titulo='" + titulo + '\'' + '}';
  }
}
