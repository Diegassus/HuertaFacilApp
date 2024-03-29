package com.example.huertafacilapp.models;

public class Usos {
  private int id;
  private String descripcion;
  private int plantaId;

  public Usos() {
  }

  public Usos(int id, String descripcion, int plantaId) {
    this.id = id;
    this.descripcion = descripcion;
    this.plantaId = plantaId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getPlantaId() {
    return plantaId;
  }

  public void setPlantaId(int plantaId) {
    this.plantaId = plantaId;
  }
}
