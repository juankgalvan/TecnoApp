package com.jikansoftware.volleyejemploget.model;

public class Hero {
    String name, imageUrl, carrera;

    public Hero(String name, String imageUrl, String carrera) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.carrera = carrera;

    }

    public String getCarrera() {
        return carrera;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
