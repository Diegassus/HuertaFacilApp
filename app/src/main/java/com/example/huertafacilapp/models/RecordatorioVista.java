package com.example.huertafacilapp.models;

public class RecordatorioVista {
  public int id;
  public String planta;
  public String recordatorio;
  public String evento;


  public RecordatorioVista() {
  }

  public RecordatorioVista(int id, String planta, String recordatorio, String evento) {
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

  public String getPlanta() {
    return planta;
  }

  public void setPlanta(String planta) {
    this.planta = planta;
  }

  public String getRecordatorio() {
    return recordatorio;
  }

  public void setRecordatorio(String recordatorio) {
    this.recordatorio = recordatorio;
  }

  public String getEvento() {
    return evento;
  }

  public void setEvento(String evento) {
    this.evento = evento;
  }
}
