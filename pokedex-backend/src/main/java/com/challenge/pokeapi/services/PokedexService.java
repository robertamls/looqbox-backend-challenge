package com.challenge.pokeapi.services;

import com.challenge.pokeapi.entities.Pokemon;
import com.challenge.pokeapi.entities.Pokedex;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokedexService {

    /**
     * Método que busca todos os nomes e suas urls com os dados de um pokemon.
     * @return retorna uma lista com todos os pokemons inseridos na pokeapi.
     */
    public List<Pokemon> getPokemons() {

        RestTemplate template = new RestTemplate();

        List<Pokemon> auxList = new ArrayList<>();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("pokeapi.co")
                .path("/api/v2/pokemon")
                .queryParam("limit", "1000")
                .build();

        Pokedex pokedex = template.getForObject(uri.toUriString(), Pokedex.class);;

        if (pokedex != null) {
            auxList.addAll(pokedex.getResults());
            while (pokedex.getNext() != null) {
                pokedex = template.getForObject(pokedex.getNext(), Pokedex.class);
                if (pokedex != null) {
                    auxList.addAll(pokedex.getResults());
                }
            }
        }
        return auxList;
    }
}
