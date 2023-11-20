package com.example.huertafacilapp.models;

public class PlantaVista {
  public int id;
  public String planta;

  public PlantaVista(int id, String planta) {
    this.id = id;
    this.planta = planta;
  }

  public PlantaVista() {
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

  @Override
  public String toString() {
    return planta;
  }
}
