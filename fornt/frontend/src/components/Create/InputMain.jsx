import React, { useEffect, useState } from "react";
import axios from "axios";
import './InputFild.css'

import Header from "../Home/Headers/Headers";

function InputMain() {

    // GET запросы для получения заказчиков, персонала и статусов
    // ###################################################################################

    const [customers, setCust] = useState([]);
    const [persons, setPers] = useState([]);
    const [statuses, setStat] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/info/customers')
            .then(response => response.clone().json())
            .then(data => setCust(data))
            .catch(error => console.log('Error fetching customers: ', error));
        
        fetch('http://localhost:8080/info/personal')
            .then(response => response.json())
            .then(data => setPers(data))
            .catch(error => console.log('Error fetching personal: ', error));

        fetch('http://localhost:8080/info/statuses')
            .then(response => response.clone().json())
            .then(data => setStat(data))
            .catch(error => console.log('Error fetching statuses: ', error));
    });

    // ###################################################################################

    // Для POST запроса
    const [data, setData] = useState({ objects: "", customer: "", executor: "", 
        files: "", responsible: "", responsible2: "", datesStart: "", datesEnd: "", states: ""
     });
    const [fileData, setFilesData] = useState({file: ""});
    const [dateData, setDateData] = useState({datesStart: "", datesEnd: "", description: "", stage: ""});
    const [response, setResponse] = useState("");

    const handleChange = (event) => {
        setData({ ...data, [event.target.name]: event.target.value });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        axios
            .post("http://localhost:8080/created", data)
            .then((response) => {
                setResponse(response.data);
            })
            .catch((error) => {
                console.log(error);                
            });
    };

    return (
        <div className="container-input">
            <Header className="impHeader" />

            <div className="massage">
                <div className="line"></div>
                <h2 className="title">Подсказка</h2>
                <p className="text1">Для добавления нового договора заполните все обязательные поля и нажмите кнопку <b>“добавить”</b>.</p>
                <p className="text2">Если нужно отменить все изменения и вернуться обратно на основную страницу, нажмите кнопку <b>“Удалить”</b>.</p>
            </div>

            <form className="inpForm" onSubmit={handleSubmit}>
                <label>
                    Объект:
                    <input 
                        type="text"
                        name="objects"
                        value={data.objects}
                        onChange={handleChange} />
                </label>

                <label>
                    Заказчик:
                    {customers.map(cus => (
                        <select onChange={handleChange} key={cus.id}>
                            <option value={customers.id}>{cus.title}</option>
                        </select>
                    ))}
                </label>

                <label>
                    Описание
                    <input
                        type="text"
                        name="description"
                        value={dateData.description}
                        onChange={handleChange}></input>
                </label>

                <label>
                    Файлы:
                    <input 
                        type="file"
                        name="files"
                        value={data.files}
                        onChange={handleChange} />
                </label>
                {/* Выяснить процесс выборки в select */}
                <label>
                    Ответсвенный:
                    <select onChange={handleChange}>
                        {persons.map(per => (
                            <option value={per.id}>{per.title}</option>
                        ))}
                    </select>
                    
                </label>

                <label>
                    Ответсвенный-2:
                    <select onChange={handleChange}>
                        {persons.map(per => (
                            <option value={per.id}>{per.title}</option>
                        ))}
                    </select>
                </label>

                <label>
                    Дата начала:
                    <input 
                        type="date"
                        name="datesStart"
                        value={data.dates}
                        onChange={handleChange} />
                </label>

                <label>
                    Дата окончания:
                    <input 
                        type="date"
                        name="datesEnd"
                        value={data.dates}
                        onChange={handleChange} />
                </label>

                <label>
                    Статус:
                    <select onChange={handleChange}>
                        {statuses.map(stat => (
                            <option value={stat.id}>{stat.title}</option>
                        ))}
                    </select>
                </label>

                <button className="sub" onClick={handleSubmit}>Добавить</button>
            </form>
        </div>
    );
}

export default InputMain;