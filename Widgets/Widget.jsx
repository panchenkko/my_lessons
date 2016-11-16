import React from 'react';
import Widgets from './Widgets.jsx';

export default class Widget extends React.Component {

    state = {
        id: 0,
        name: this.props.name,
        count: this.props.count,
        color: this.props.color,
        select: false
    };

    constructor(props) {
        super(props);
    }

    /*
     * Для получения данных виджета при клике на него
     */
    handlerSelect() {
        Widgets.handlerSelect(this.props);
    }

    render() {
        return (
            <div className="widget" id={this.props.id} onClick={this.handlerSelect.bind(this)}>
                <strong>Name: {this.props.name}</strong>
                <br />
                <strong>Count: {this.props.count}</strong>
            </div>
        );
    }
}
