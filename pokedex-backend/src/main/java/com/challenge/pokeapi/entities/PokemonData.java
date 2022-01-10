package com.challenge.pokeapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class PokemonData {
    private String name;
    private List<String> types;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
