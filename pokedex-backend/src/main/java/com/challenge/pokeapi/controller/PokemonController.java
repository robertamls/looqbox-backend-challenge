package com.challenge.pokeapi.controller;

import com.challenge.pokeapi.services.PokemonService;
import com.challenge.pokeapi.services.PokedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PokemonController {

    @Autowired
    private PokedexService servicePokedex;

    @Autowired
    private PokemonService servicePokemon;
    
    public void consumerAPI() {
        try {
            servicePokemon.persistPokemons(servicePokedex.getPokemons());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
