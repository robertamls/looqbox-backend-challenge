package com.challenge.pokeapi.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeEndpoint {
    @GetMapping("/")
    public String index(){
        return "Aplicação iniciada!";
    }
}
