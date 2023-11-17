package com.example.huertafacilapp.models;

import java.io.Serializable;

public class ObjetoDocument implements Serializable {
  private byte[] foto;
  private String titulo;
  private String cuerpo;

  public ObjetoDocument(byte[] foto, String titulo, String cuerpo) {
    this.foto = foto;
    this.titulo = titulo;
    this.cuerpo = cuerpo;
  }

  public ObjetoDocument(String titulo, String cuerpo) {
    this.titulo = titulo;
    this.cuerpo = cuerpo;
  }

  public ObjetoDocument() {
  }

  public byte[] getFoto() {
    return foto;
  }

  public void setFoto(byte[] foto) {
    this.foto = foto;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getCuerpo() {
    return cuerpo;
  }

  public void setCuerpo(String cuerpo) {
    this.cuerpo = cuerpo;
  }
}
