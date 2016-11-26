import React from 'react';
import Widget from './Widget.jsx';

var Input = require("react-input-mask");

var Widgets = [
    {
        id: 1,
        name: 'Widget 1',
        count: 2,
        color: 'rgb(255,000,000)',
        select: false
    }, {
        id: 2,
        name: 'Widget 2',
        count: 5,
        color: 'rgb(255,150,000)',
        select: false
    }, {
        id: 3,
        name: 'Widget 3',
        count: 3,
        color: 'rgb(0,150,000)',
        select: false
    }, {
        id: 4,
        name: 'Widget 4',
        count: 7,
        color: 'rgb(150,250,000)',
        select: false
    }
]

export default class HandlerWidgets extends React.Component {

    state = {
        widgets: Widgets,
    };

    constructor(props) {
        super(props);
    }

    /*
     * Функция отвечает за то, что будет делать кнопка Add
     */
    addWidget = () => {
        // Снимаем метку у всех виджетов
        for(let el of Widgets) {
            el.select = false;
            // Убираем особый внешний вид для выбранного виджета
            document.getElementById(el.id).classList.remove('widget-check');
        }

        let name = document.getElementById('name').value;
        let count = document.getElementById('count').value;
        let color = document.getElementById('color').value;

        if (name != '' && count != '' && color != '') {
            // Создаем объект нового виджета
            let widgetTemp = {
                id: this.state.widgets.length + 1,
                name: name,
                count: count,
                color: color,
                select: false
            };

            // Добавляем новый виджет в массив
            Widgets.push(widgetTemp);

            // Выводим добавленный виджет в консоль
            console.log(widgetTemp);

            document.getElementById('color').style.background = document.getElementById('color').value;

            // Обновляем состояние
            this.setState({widgets: Widgets}, this.setColor);

            // Снимаем метку у всех виджетов и блокируем кнопку Save
            this.clearMarker();
            this.disabledOn();
        }
    }

    /*
     * Функция отвечает за то, что будет делать кнопка Save
     */
    saveWidget = () => {
        // Находим выбранный виджет и сохраняем новые данные
        for(let el of Widgets) {
            if (el.select) {
                // Убираем особый внешний вид для выбранного виджета
                document.getElementById(el.id).classList.remove('widget-check');

                el.name = document.getElementById('name').value;
                el.count = document.getElementById('count').value;
                el.color = document.getElementById('color').value;

                document.getElementById('color').style.background = document.getElementById('color').value;

                // Обновляем состояние
                this.setState({widgets: Widgets}, this.setColor);

                this.disabledOn();
            }
        }
    }

    /*
     * Устанавливаем все функции при выборе виджета
     *
     * @param el - получаем объект выбранного виджета
     */
    static handlerSelect(el) {

        // Снимаем метку у всех виджетов
        for(let el of Widgets) {
            el.select = false;
            // Убираем особый внешний вид для выбранного виджета
            document.getElementById(el.id).classList.remove('widget-check');
        }

        var element = Widgets[el.id -1];

        // Ставим метку на виджет, какой выбрал пользователь
        element.select = true;

        // Меняем внешний вид выбранного виджета
        document.getElementById(el.id).classList.add('widget-check');

        // Устанавливаем данные в поля ввода
        document.getElementById('name').value = el.name;
        document.getElementById('count').value = el.count;
        document.getElementById('color').value = el.color;

        // Снимаем блокировку кнопки Save
        document.getElementById('save').disabled = false;
        // Устанавливаем соответствующий цвет в поле ввода цвета
        document.getElementById('color').style.background = document.getElementById('color').value;

        // Выводим в консоль данные выбранного виджета
        console.log('Selected Widget #' + el.id + ': ' +
                                          el.name + ', ' +
                                          el.count + ', ' +
                                          el.color + ', ' +
                                          el.select + '.');
    }

    /*
     * Функция вызывается перед самым первым запуском.
     * Все начальные действия можно вносить сюда.
     */
    componentDidMount() {
        // Устанавливаем цвет каждого виджета при загрузке сайта
        this.setColor();
        // Устанавливаем красным цветом поле ввода цвета
        document.getElementById('color').style.background = document.getElementById('color').value;
    }

    /*
     * Для очистки выбранного виджета
     */
    clearMarker() {
        for(let el of Widgets) {
            el.select = false;
        }
    }

    /*
     * Для установки цвета для всех виджетов
     */
    setColor() {
        for (let el of Widgets) {
            document.getElementById(el.id).style.background = el.color;
        }
    }

    /*
     * Устанавливает цвет заднего фона, какой был введен в поле ввода цвета
     */
    setColorEdit() {
        document.getElementById('color').style.background = document.getElementById('color').value;
    }

    /*
     * Для снятия блокировки кнопки Save
     */
    disabledOff() {
        document.getElementById('save').disabled = false;
    }

    /*
     * Для блокировки кнопки Save
     */
    disabledOn() {
        document.getElementById('save').disabled = true;

        document.getElementById('name').value = '';
        document.getElementById('count').value = '';
        document.getElementById('color').value = '';
    }

    render() {
        return (
            <div>
                <div className="container2">
                    <div className="settings">
                        <form method="post">
                            <input type="text" id="name" className="form-control input-control" required placeholder="Input name widget"/>
                            <br />
                            <input type="number" id="count" className="form-control input-control" min="0" required placeholder="Input count widget"/>
                            <br />
                            <Input id="color" onChange={this.setColorEdit}
                                className="form-control input-control" pattern="\D [^_]" defaultValue="rgb(255,000,000)"
                                placeholder="Input color widget in format RGB" mask="rgb(999,999,999)" maskChar="_" />
                            <br />
                            <input type="button" id="add" value="Add" onClick={this.addWidget} className="btn btn-primary input-control" />
                            <input type="button" id="save" value="Save" disabled onClick={this.saveWidget} className="btn btn-success input-control" />
                        </form>
                    </div>
                </div>

                <div className="dashboard">
                    {
                        this.state.widgets.map(function(el) {
                            return (
                                <Widget key={el.id}
                                    id={el.id}
                                    name={el.name}
                                    count={el.count}
                                    color={el.color}
                                    select={el.select} />
                            );
                        })
                    }
                </div>
            </div>
        );
    }
}
