package com.challenge.pokeapi;

import com.challenge.pokeapi.controller.PokemonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PokeapiApplication {

    @Autowired
    private PokemonController controller;

    /**
     *
     */
   @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        controller.consumerAPI();
    }

    public static void main(String[] args) {
        SpringApplication.run(PokeapiApplication.class, args);
    }
}
