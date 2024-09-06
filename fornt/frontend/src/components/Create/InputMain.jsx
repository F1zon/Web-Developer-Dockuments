import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route, Link, useNavigate } from 'react-router-dom';
import './InputFild.css'
import { PlusOutlined } from '@ant-design/icons';
import {
  Button,
  Cascader,
  Checkbox,
  ColorPicker,
  DatePicker,
  Form,
  Input,
  InputNumber,
  message,
  Radio,
  Rate,
  Select,
  Slider,
  TreeSelect,
  Upload,
} from 'antd';

import Header from "../Home/Headers/Headers";
import { Color } from "antd/es/color-picker";

function InputMain() {
    const initialFormStateContract = {
        objects: '',
        customer: '',
        executor: '',
        responsible: '',
        responsible2: '',
        states: ''
    };

    const initialFormStateFiles = {
        fileName: ''
    };

    const initialFormStateDates = {
        dateStart: '',
        description: ''
    };


    // GET запросы для получения заказчиков, персонала и статусов
    // ###################################################################################
    const [customers, setCust] = useState([]);
    const [persons, setPers] = useState([]);
    const [statuses, setStat] = useState([]);
    const [departments, setDep] = useState([]);

    const [contract, setContract] = useState(initialFormStateContract);
    const [fileData, setFilesData] = useState(initialFormStateFiles);
    const [dateData, setDateData] = useState(initialFormStateDates);
    const [messageApi, contextHolder] = message.useMessage();

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
        
        fetch('http://localhost:8080/info/dep')
            .then(response => response.clone().json())
            .then(data => setDep(data))
            .catch(error => console.log('Error fetcheng departments: ', error));
    }, []);

    // ###################################################################################

    // Для POST запроса

    // const handleChange = (event) => {
    //     setContract({ ...contract, [event.target.name]: event.target.value });
    //     // setFilesData({ ...fileData, [event.target.name]: event.target.value });
    // };

    // TODO: Fix input value in Contract
    const handleChange = (event) => {
        const { name, value } = event.target
        setContract({ ...contract, [name]: value })
    }

    const handleChangeDate = (event) => {
        setDateData({ ...dateData, [event.target.name]: event.target.value });
    }

    const handleChangeFiles = (event) => {
        setFilesData({ ...fileData, [event.target.name]: event.target.value });
    }

    const handleChangeDep = (event) => {
        setDep({ ...departments, [event.target.name]: event.target.value })
    }

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        await fetch(`http://localhost:8080//create/contract`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(contract)
        });
        setContract(initialFormStateContract);

        navigate("/");
    };

    const options = [
        departments.map(dep => ({
            label: dep.name,
            value: dep.id,
            children: persons.map(pers => ({
              label: pers.name,
              value: pers.id,
            })),
        }))
    ]

    console.log(contract);
    return (
        <div className="container-input">
            <Header className="impHeader" />

            <div className="massage">
                <div className="line"></div>
                <h2 className="title">Подсказка</h2>
                <p className="text1">Для добавления нового договора заполните все обязательные поля и нажмите кнопку <b>“добавить”</b>.</p>
                <p className="text2">Если нужно отменить все изменения и вернуться обратно на основную страницу, нажмите кнопку <b>“Удалить”</b>.</p>
            </div>

            <Form method="post" className="inpForm" onSubmit={handleSubmit}>
                <label>
                    Объект:
                    <Input 
                        type="text"
                        name="objects"
                        value={contract.objects}
                        onChange={handleChange} />
                </label>

                {/* <label>
                    Заказчик:
                    <Select name='states' onChange={handleChange}>
                        <option value=""> </option>
                        {customers.map(cus => (
                            <option value={cus.id}>{cus.name}</option>
                        ))}
                    </Select>
                    
                </label> */}

                <label>
                    Заказчик:
                    <Select 
                        options={customers}
                    />
                </label>

                <label>
                    Описание работ:
                    <Input 
                        type="text"
                        name="description"
                        value={dateData.description}
                        onChange={handleChangeDate} />
                </label>

                {/* <label>
                    шаг
                    <input
                        type="text"
                        name="stage"
                        value={dateData.stage}
                        onChange={handleChangeDate} />
                </label> */}

                {/* <label>
                    Отдел:
                    <Select name="department" onChange={handleChangeDep} multiple>
                        <option value=""> </option>
                        {departments.map(dep => (
                            <option value={dep.id}>{dep.name}</option>
                        ))}
                    </Select>
                </label> */}

                <label>
                    Ответсвенный:
                    {/* <Select onChange={handleChange}>
                        <option value=""> </option>
                        {persons.map(per => (
                            <option value={per.title}>{per.title}</option>
                        ))}
                    </Select> */}
                    <Cascader
                        style={{
                        width: '100%',
                        }}
                        options={options}
                        onChange={handleChange}
                        multiple
                        maxTagCount="responsive"
                    />
                </label>

                <label>
                    Ответсвенный-2:
                    <Select onChange={handleChange}>
                        <option value=""> </option>
                        {persons.map(per => (
                            <option value={per.title}>{per.title}</option>
                        ))}
                    </Select>
                </label>

                <label>
                    Дата добавления:
                    {/* <Input 
                        type="text"
                        name="datesStart"
                        value={new Date().toLocaleDateString()}
                        onChange={handleChange} readOnly/> */}
                    <DatePicker />
                </label>

                {/* <label>
                    Дата окончания:
                    <input 
                        type="date"
                        name="datesEnd"
                        value={data.dates}
                        onChange={handleChange} />
                </label> */}

                <label>
                    Статус договора:
                    <Select onChange={handleChange}>
                        <option value=""> </option>
                        {statuses.map(stat => (
                            <option className="states" value={stat.id}>{stat.title}</option>
                        ))}
                    </Select>
                </label>


                <label>
                    Файлы:
                    <Upload action="/upload.do" listType="picture-card">
                        <button style={{ border: 0, background: 'none' }} type="button">
                        <PlusOutlined />
                        <div style={{ marginTop: 8 }}>Загрузить</div>
                        </button>
                    </Upload>
                </label>

                <button className="sub" onClick={handleSubmit}>Добавить</button>
            </Form>
        </div>
    );
}

export default InputMain;