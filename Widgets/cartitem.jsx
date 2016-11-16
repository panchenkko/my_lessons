import React from 'react';

export default class CartItem extends React.Component {

    static propTypes = {
        title: React.PropTypes.string.isRequired,
        price: React.PropTypes.number.isRequired,
        initialQty: React.PropTypes.number
    };

    static defaultProps = {
        title: 'Undefined Product',
        price: 100,
        initialQty: 0
    };

    state = {
        title: this.props.title,
        image: this.props.image,
        qty: this.props.initialQty,
        price: this.props.price,
        total: 0
    };

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.recalculateTotal();
    }

    increaseQty() {
        this.setState({qty: this.state.qty + 1}, this.recalculateTotal);
    }

    decreaseQty() {
        let newQty = this.state.qty > 0 ? this.state.qty - 1 : 0;
        this.setState({qty: newQty}, this.recalculateTotal);
    }

    recalculateTotal() {
        this.setState({total: this.state.qty * this.state.price});
    }

    handlePrice = (event) => {
        this.setState({price: event.target.value}, this.recalculateTotal);
    }

    render() {
        return (
            <article className="row large-5">
                <figure className="text-center">
                    <p>
                        <img src={this.state.image}/>
                    </p>
                    <figcaption>
                        <h2>{this.state.title}</h2>
                    </figcaption>
                </figure>
                <p className="large-4 column"><strong>Quantity: {this.state.qty}</strong></p>

                <p className="large-4 column">
                    <button onClick={this.increaseQty.bind(this)} className="button success">+</button>
                    <button onClick={this.decreaseQty.bind(this)} className="button alert">-</button>
                </p>

                <p className="large-4 column"><strong>Price per item:</strong> ${this.state.price}</p>
                <input type="number" min="0" className="large-4 column price-field" onChange={this.handlePrice} />

                <h3 className="large-12 column text-center">
                    Total: ${this.state.total}
                </h3>
                <p className="large-12 column">
                    <strong>Time elapsed for interval: </strong>
                    {this.props.seconds} seconds
                </p>
            </article>
        );
    }
}
