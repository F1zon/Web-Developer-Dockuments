import React, { useEffect, useState } from "react";

function Main() {
    const [docks, setDocks] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/docks')
            .then(response => response.json())
            .then(data => setDocks(data))
            .catch(error => console.error('Error fetching dockuments: ', error));
    });

    return(
        <div className="Main">
            <div className="RedactBar">
                <button className="crateDock">Добавить</button>
                <button className="redactDock">редактировать</button>
            </div>
            <div className="DocksTable">
                <div className="InfoBar">
                    <input type="checkbox" name="choseAll" id="getAll" className="custom-checkbox" />
                    <p className="object">Объект</p>
                    <p className="customer">Заказчик</p>
                    <p className="executor">Исполнитель</p>
                    <p className="pers">Ответсвенный</p>
                    <p className="state">Статус</p>
                </div>
                <div className="docksTable">
                    <ul>
                        {docks.map(dock => (
                            <li>{dock}</li>
                        ))}
                    </ul>
                </div>
                <div className="bottomNavBar">
                    <a href="" className="colNavLink">1</a>
                    <a href="" className="colNavLink">2</a>
                    <a href="" className="colNavLink">3</a>
                    <p className="emptys">...</p>
                    <a href="" className="colNavLink">n</a>
                </div>
            </div>
        </div>
    );
}

export default Main;