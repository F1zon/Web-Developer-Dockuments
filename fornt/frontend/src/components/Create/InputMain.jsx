import React, { useEffect, useState } from "react";
import axios from "axios";
import './InputFild.css'

import Header from "../Home/Headers/Headers";

function InputMain() {

    // Для получения GET информации с бэка
    const [info, setInfo] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/docks')
            .then(response => response.json())
            .then(data => setInfo(data))
            .catch(error => console.error('Error fetching dockuments: ', error));
    });

    // Для POST запроса
    const [data, setData] = useState({ objects: "", customer: "", executor: "", 
        files: "", responsible: "", responsible2: "", datesStart: "", datesEnd: "", states: ""
     });
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
                    <select onChange={handleChange}>
                        {info.map(inf => (
                            <option value={inf.customer}>{inf.customer}</option>
                        ))}
                    </select>
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
                        {info.map(inf => (
                            <option value={data.responsible}>{inf.responsible}</option>
                        ))}
                    </select>
                </label>

                <label>
                    Ответсвенный-2:
                    <input 
                        type="text"
                        name="responsible2"
                        value={data.responsible2}
                        onChange={handleChange} />
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
                    <input 
                        type="text"
                        name="states"
                        value={data.states}
                        onChange={handleChange} />
                </label>
            </form>
        </div>
    );
}

export default InputMain;