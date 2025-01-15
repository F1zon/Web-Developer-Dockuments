import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route, Link, useNavigate } from 'react-router-dom';
import Created from '../../Create/Create'
import axios from "axios";

function Main({object}) {
    const [docks, setDocks] = useState([]);
    const [selectedId, setSelId] = useState();


    useEffect(() => {
        fetch('http://localhost:8080/docks')
            .then(response => response.json())
            .then(data => setDocks(data))
            .catch(error => console.error('Error fetching dockuments: ', error));
    });

    const navigate = useNavigate();

    const navigateCreate = () => {
        navigate('/editing');
    }

    const navigateReduct = () => {
        navigate('/editing?id=' + selectedId);
    }

    const deleteDocks = () => {
        // fetch('http://localhost:8080/delete?id=' + selectedId);
        const response = axios.delete(`http://localhost:8080/delete?id=` + selectedId);
    }

    const setId = (event, value) => {
        setSelId(value);
    }

    const filterDocks = object == undefined 
        ? docks
        : docks.filter(x => x.objects.includes(object));
    console.log(object === undefined);
    return(
        <div className="Main">
            <div className="RedactBar">
                <button className="crateDock" onClick={navigateCreate}>Добавить</button>
                <button className="redactDock" onClick={navigateReduct}>Редактировать</button>
                <button className="deleteDock" onClick={deleteDocks}>Удалить</button>
            </div>
            <div className="DocksTable">
                <div className="InfoBar">
                    <input type="checkbox" name="choseAll" id="getAll" className="custom-checkbox"/>
                    <p className="customer">Заказчик</p>
                    <p className="object">Объект</p>
                    <p className="executor">Исполнитель</p>
                    <p className="pers">Ответсвенный</p>
                    <p className="dep">Отдел</p>
                    <p className="state">Статус</p>
                </div>
                <div className="docksTable">
                    <table>
                        <tbody>
                            {filterDocks.map(dock => (
                                <tr key={dock.idContract}>
                                    <input type="checkbox" name="choseAll" id="getAll" className="custom-checkbox" onChange={(e) => setId(e, dock.idContract)}/>
                                    <td className="customer"> {dock.customer} </td>
                                    <td className="object"> {dock.objects} </td>
                                    <td className="executor"> {dock.executor} </td>
                                    <td className="pers"> {dock.responsible} </td>
                                    <td className="dep"> {dock.department} </td>
                                    <td className="state"> {dock.states} </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
                {/* <div className="bottomNavBar">
                    <a href="" className="colNavLink">1</a>
                    <a href="" className="colNavLink">2</a>
                    <a href="" className="colNavLink">3</a>
                    <p className="emptys">...</p>
                    <a href="" className="colNavLink">n</a>
                </div> */}
            </div>
        </div>
    );
}

export default Main;