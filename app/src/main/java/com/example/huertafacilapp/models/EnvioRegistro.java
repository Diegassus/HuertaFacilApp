package com.example.huertafacilapp.models;

public class EnvioRegistro {
  private int planta;
  private int tipo;
  private String evento;

  public EnvioRegistro() {
  }

  public EnvioRegistro(int planta, int tipo, String evento) {
    this.planta = planta;
    this.tipo = tipo;
    this.evento = evento;
  }

  public int getPlanta() {
    return planta;
  }

  public void setPlanta(int planta) {
    this.planta = planta;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public String getEvento() {
    return evento;
  }

  public void setEvento(String evento) {
    this.evento = evento;
  }
}
