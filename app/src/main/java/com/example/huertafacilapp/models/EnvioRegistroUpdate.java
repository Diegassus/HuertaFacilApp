package com.example.huertafacilapp.models;

public class EnvioRegistroUpdate {
  private int id;
  private int planta;
  private int recordatorio;
  private String evento;

  public EnvioRegistroUpdate() {
  }

  public EnvioRegistroUpdate(int id, int planta, int recordatorio, String evento) {
    this.id = id;
    this.planta = planta;
    this.recordatorio = recordatorio;
    this.evento = evento;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPlanta() {
    return planta;
  }

  public void setPlanta(int planta) {
    this.planta = planta;
  }

  public int getRecordatorio() {
    return recordatorio;
  }

  public void setRecordatorio(int recordatorio) {
    this.recordatorio = recordatorio;
  }

  public String getEvento() {
    return evento;
  }

  public void setEvento(String evento) {
    this.evento = evento;
  }
}
