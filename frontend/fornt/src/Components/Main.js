import React, { Component } from "react";
import "./Css/mainMenu.css"

export default class Main extends Component {
    render() {
        return(
            <div class="Main">
                <div class="RedactBar">
                    <button class="crateDock">Добавить</button>
                    <button class="redactDock">редактировать</button>
                </div>
                <div class="DocksTable">
                    <div class="InfoBar">
                        <input type="checkbox" name="choseAll" id="getAll" class="custom-checkbox"/>
                        <p class="object">Объект</p>
                        <p class="customer">Заказчик</p>
                        <p class="executor">Исполнитель</p>
                        <p class="pers">Ответсвенный</p>
                        <p class="state">Статус</p>
                    </div>
                    <div class="docksTable"></div>
                    <div class="bottomNavBar">
                        <a href="" class="colNavLink">1</a>
                        <a href="" class="colNavLink">2</a>
                        <a href="" class="colNavLink">3</a>
                        <p class="emptys">...</p>
                        <a href="" class="colNavLink">n</a>
                    </div>
                </div>
            </div>
        );
    }
}