import React from "react";
import filter from './filter.svg'
import logout from './logout.svg'

function Menu() {
    return(
        <div className="Menu">
            <div className="headText">
                <h1 className="D">Docks-</h1>
                <h1 className="T">Tools</h1>
            </div>

            <div className="ButtonList">
                <div className="butRow" butRow>
                    <img src={filter} alt="filterImg" className="ButImg" />
                    <a href="" className="ButText">Фильтр</a>
                </div>
            </div>

            <div className="exit">
                <div className="butRow">
                    <img src={logout} alt="logIutImg" className="ButImg" />
                    <a href="" className="ButText">Выход</a>
                </div>
            </div>
        </div>
    );
}

export default Menu;