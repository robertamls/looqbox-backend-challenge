import logo from './logo.svg';
import './App.css';
import TextField from '@mui/material/TextField';
import React from 'react';
import charmander from './charmander.gif';
import axios from 'axios';
import Card from './Card';


class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      pokemonValue: '',
      listpokemon: []
    }
  }

  searchPokemon() {
    console.log("click", this.state.pokemonValue);
    if (this.state.pokemonValue !== "") {
      axios.get(`http://localhost:8080/pokemons/filter?q=${this.state.pokemonValue}`).then(
        (resposta) => {
          this.setState({
            listpokemon: resposta.data.result,
            pokemonValue: ""
          })
        }
      ).catch(
        function (e) {
          console.log(e);
        }
      )
    }
  }


  controlField(e) {
    this.setState({
      pokemonValue: e.target.value
    });
  }

  render() {
    let cards = this.state.listpokemon.map((pokemon) => {
      return <Card name={pokemon.name} img={pokemon.image} type={pokemon.types} />
    });

    return (
      <div className="App">
        <div className="container">
          <div className='header'>
            <div className="logo-header">
              <img src={logo} className="logo" alt="logo" />
            </div>
            <div className='text-header'>{"Qual Pokemon você procura? O Charmander te ajudará!"}</div>
          </div>
          <div className='filtros'>
            <div className="text-filtro">
              <TextField className="text-field" label="Realize a busca por nome completo ou as suas iniciais." color="secondary" focused
                value={this.state.pokemonValue} onChange={(e) => {
                  this.controlField(e);
                }} />
            </div>
            <div className='bt-filtro'>
              <img src={charmander} className="bt-charm" alt="bt-charm" onClick={() => {
                this.searchPokemon();
              }} />
            </div>
          </div>
          <div className='content-pokedex'>
            <ul className="pokedex">{cards}</ul>
          </div>
        </div>
      </div>
    );
  }

}

export default App;
