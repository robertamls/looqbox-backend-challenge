package com.challenge.pokeapi.services;

import com.challenge.pokeapi.entities.Pokedex;
import com.challenge.pokeapi.entities.Pokemon;
import com.challenge.pokeapi.entities.PokemonData;
import com.challenge.pokeapi.entities.PokemonHighlight;
import com.challenge.pokeapi.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository repository;

    /**
     * Método que persiste as informações puxadas da POKEAPI no banco local.
     */
    public void persistPokemons(List<Pokemon> pokemons) {
        for (Pokemon pokemon : pokemons) {
            repository.save(pokemon);
        }
    }

    /**
     * Desafio 2: Método que busca no banco um pokemon com o valor recebido pelo parâmetro query.
     * @param query
     * @return Retorna uma lista de strings (nome dos pokemons) já em ordem alfabética.
     */
    public List<String> findByName(String query) {
        List<String> pokemonsList = repository.findByName(query);
        return alphabeticalFilter(pokemonsList);
    }

    /**
     * Método que busca a url de acordo com  nome do pokemon (parâmetro query) e recupera os dados do mesmo.
     * @param query
     * @return Retorna uma lista de PokemonData (informações recuperadas sobre um pokemon)
     */
    public List<PokemonData> findByNameUrl(String query) {

        List<String> urls = repository.findByNameUrl(query);
        List<PokemonData> datas = new ArrayList<>();
        for (String url : urls) {
            datas.add(getData(url));
        }
        return datas;
    }

    /**
     * Método que recupera as informações na pokeapi com base na url do banco.
     * @param url
     * @return Retorna um objeto preenchido com as informações recuperadas.
     */
    private PokemonData getData(String url) {

        RestTemplate template = new RestTemplate();

        Map<String, Object> map = template.getForObject(url, Map.class);

        PokemonData pokemonData = new PokemonData();

        Object auxName = map.get("name");
        if (auxName instanceof String) {
            pokemonData.setName((String) auxName);
        }

        String auxImage = (String) ((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) map.get("sprites")).get("other")).get("official-artwork")).get("front_default");
        pokemonData.setImage(auxImage);

        List<Map> auxTypes = (List<Map>) map.get("types");
        List<String> types = new ArrayList<>();

        for (Map m : auxTypes) {
            String auxType = (String) ((Map<String, Object>) m.get("type")).get("name");
            types.add(auxType);
        }

        pokemonData.setTypes(types);

        return pokemonData;
    }

    /**
     * Desafio 3 - Ordem alfabética: Classifica uma lista de strings (nomes dos pokemons) de acordo com suas iniciais,
     * retornando um valor inteiro para determinar qual string virá primeiro.
     *
     * @param list
     * @return Retorna uma lista de strings (nomes dos pokemons) filtrada em ordem alfabética.
     */
    public List<String> alphabeticalFilter(List<String> list) {
        String[] str = list.toArray(new String[0]);
        for (int i = 0; i < str.length - 1; i++) {
            for (int j = i + 1; j < str.length; j++) {
                if (str[i].compareTo(str[j]) > 0) {
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }
        return List.of(str);
    }

    /**
     * Desafio 3 - Tamanho do nome: Classifica uma lista de strings (nomes dos pokemons) de acordo com seus comprimentos.
     *
     * @param list
     * @return Retorna uma lista de strings (nomes dos pokemons) ordenada por ordem de comprimento das strings.
     */
    public List<String> namesLength(List<String> list) {
        String[] str = list.toArray(new String[0]);

        for (int i = 1; i < str.length; i++) {
            String temp = str[i];

            int j = i - 1;
            while (j >= 0 && temp.length() < str[j].length()) {
                str[j + 1] = str[j];
                j--;
            }
            str[j + 1] = temp;
        }

        return List.of(str);
    }

    /**
     * Desafio 4: Método que destaca a parte da string pesquisada no banco.
     * @param query
     * @return Retona uma lista de objetos (PokemonHighlight) preenchidos com as informações da pesquisa.
     */
    public List<PokemonHighlight> findByNameHighlight(String query) {
        List<String> pokemonsList = repository.findByName(query);
        List<PokemonHighlight> namesHighlight = new ArrayList<>();
        if(!pokemonsList.isEmpty()){
           for(String name : pokemonsList){
             namesHighlight.add(highlightString(name, query));
           }
        }
        return namesHighlight;
    }

    /**
     * Método que marca a parte do texto pesquisado.
     * @param name
     * @param query
     * @return Retorna ma lista de PokemonHighlight preenchidas com as informações.
     */
    private PokemonHighlight highlightString(String name, String query){
        int start = name.indexOf(query);
        int end = start + query.length() - 1;

        PokemonHighlight pokemonHighlight = new PokemonHighlight();
        pokemonHighlight.setName(name);
        pokemonHighlight.setStart(start);
        pokemonHighlight.setEnd(end);

        return pokemonHighlight;
    }
}


