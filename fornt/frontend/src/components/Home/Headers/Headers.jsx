import React from "react";
import notifications from './notifications.svg'
import defaultAvatar from './G1Oom95LzX0.jpg'

function Header() {
    return(
        <div className="Header">
            <div className="NavBar">
                <a href="/" className="docks">Договоры</a>
            </div>
            <a href="" className="notImg">
                <img src={notifications} alt="nitfImg" className="nitfImg" />
            </a>
            <img src={defaultAvatar} alt="avatar" className="avatar" />
            <p className="Name">Никита Королёв</p>
        </div>
    );
}

export default Header;