import React, { Component } from "react";
import "./Css/menu.css"
import filter from "./images/filter.svg"
import exit from "./images/logout.svg"

export default class Menu extends Component {
    render() {
        return(
            <div class="Menu">
                <div class="headText">
                    <h1 class="D">Docks-</h1>
                    <h1 class="T">Tools</h1>
                </div>

                <div class="ButtonList">
                    <div class="butRow" butRow>
                        <img src={ filter } alt="filterImg" class="ButImg"/>
                        <a href="" class="ButText">Фильтр</a>
                    </div>
                </div>

                <div class="exit">
                    <div class="butRow">
                        <img src={ exit } alt="logIutImg" class="ButImg"/>
                        <a href="" class="ButText">Выход</a>
                    </div>
                </div>
            </div>
        );
    }
}