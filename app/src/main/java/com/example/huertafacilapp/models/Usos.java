package com.example.huertafacilapp.models;

public class Usos {
  private int Id;
  private String Descripcion;
  private int PlantaId;

  public Usos(int id, String descripcion, int plantaId) {
    Id = id;
    Descripcion = descripcion;
    PlantaId = plantaId;
  }

  public Usos() {
  }

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getDescripcion() {
    return Descripcion;
  }

  public void setDescripcion(String descripcion) {
    Descripcion = descripcion;
  }

  public int getPlantaId() {
    return PlantaId;
  }

  public void setPlantaId(int plantaId) {
    PlantaId = plantaId;
  }
}
