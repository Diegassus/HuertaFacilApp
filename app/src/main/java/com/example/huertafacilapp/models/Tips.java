package com.example.huertafacilapp.models;

public class Tips {
  private int Id;
  private String descripcion;
  private int plantaId;

  public Tips() {
  }

  public Tips(int id, String descripcion, int plantaId) {
    Id = id;
    this.descripcion = descripcion;
    this.plantaId = plantaId;
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
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
