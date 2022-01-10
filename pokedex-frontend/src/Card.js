import React from 'react';
import './Card.css';


class Card extends React.Component {

    render() {
       let gradient = this.props.type.join(" ")
    
        gradient+=" card"

        return (
            <li className={gradient}>
                <img className="card-image" src={this.props.img} alt={this.props.name}></img>
                <h2 className="card-title">{this.props.name}</h2>
                <p className="card-subtitle">{this.props.type.join(' | ')}</p>
            </li>
        )
    }
}

export default Card;