package com.challenge.pokeapi.repositories;

import com.challenge.pokeapi.entities.Pokemon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PokemonRepository extends CrudRepository<Pokemon, Long> {

    @Query(value = "SELECT name FROM pokemon WHERE name LIKE ?1%", nativeQuery = true)
    List<String> findByName(String query);

    @Query(value = "SELECT url FROM pokemon WHERE name LIKE ?1%", nativeQuery = true)
    List<String> findByNameUrl(String name);

}
