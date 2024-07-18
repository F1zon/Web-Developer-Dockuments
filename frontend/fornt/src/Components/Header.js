import React, { Component } from "react";
import notifications from "./images/notifications.svg"
import avatar from "./images/G1Oom95LzX0.jpg"

export default class Header extends Component {
    render() {
        return(
            <div class="Header">
                <div class="NavBar">
                    <a href="" class="docks">Договоры</a>
                </div>

                <a href="" class="notImg"><img src={notifications} alt="nitfImg" class="nitfImg"/></a>
                <img src={ avatar } alt="avatar" class="avatar"/>
                <p class="Name">Никита Королёв</p>
            </div>
        );
    }
}