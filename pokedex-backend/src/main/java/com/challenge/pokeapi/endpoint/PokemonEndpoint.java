package com.challenge.pokeapi.endpoint;

import com.challenge.pokeapi.entities.PokemonData;
import com.challenge.pokeapi.entities.PokemonHighlight;
import com.challenge.pokeapi.services.PokemonService;
import com.challenge.pokeapi.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
@CrossOrigin(origins = "http://localhost:3000")
public class PokemonEndpoint {

    @Autowired
    private PokemonService service;

    @GetMapping("")
    public ResponseEntity<Object> pokemons(@RequestParam("q") String query) {
        List<String> pokemonViewList = service.findByName(query);
        return ResponseUtil.generateStrings(HttpStatus.OK, pokemonViewList);
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> filterPokemons(@RequestParam("q") String query) {
        List<PokemonData> pokemonList = service.findByNameUrl(query);
        return ResponseUtil.generatePokemons(HttpStatus.OK, pokemonList);
    }

    @GetMapping("/highlight")
    public  List<PokemonHighlight> highlightName(@RequestParam("q") String query) {
        List<PokemonHighlight> pokemonList = service.findByNameHighlight(query);
        return pokemonList;
    }
}
