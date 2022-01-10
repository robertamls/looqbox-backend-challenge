package com.challenge.pokeapi.util;

import com.challenge.pokeapi.entities.Pokemon;
import com.challenge.pokeapi.entities.PokemonData;
import com.challenge.pokeapi.entities.PokemonHighlight;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {

    public static ResponseEntity<Object> generateStrings(HttpStatus status, List<String> pokemonViewList) {

        Map<String, Object> map = new HashMap<>();

        map.put("result", pokemonViewList);

        return new ResponseEntity<Object>(map,status);
    }

    public static ResponseEntity<Object> generatePokemons(HttpStatus status, List<PokemonData> pokemonList) {

        Map<String, Object> map = new HashMap<>();

        map.put("result", pokemonList);

        return new ResponseEntity<Object>(map,status);
    }
}
