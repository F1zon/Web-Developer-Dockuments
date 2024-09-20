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
import axios from "axios";

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
    const [customers, setCust] = useState([]);
    const [persons, setPers] = useState([]);
    const [statuses, setStat] = useState([]);
    const [departments, setDep] = useState([]);

    const [contract, setContract] = useState(initialFormStateContract);
    const [fileData, setFilesData] = useState(initialFormStateFiles);
    const [dateData, setDateData] = useState(initialFormStateDates);
    const [messageApi, contextHolder] = message.useMessage();

    // ################################################################################# GET
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

    // ################################################################################# GET

    // TODO: Fix input value in Contract
    const handleChange = (str, i) => {
        let val = i;
        let name = str;
        setContract({ ...contract, [name]: val })
    }

    const handleChangeInput = (event) => {
        setContract( { ...contract, [event.target.name]: event.target.value } )
    }

    const handleChangeSelector = (str, i) => {
        let val = i[i.length - 1];
        let name = str;
        setContract({...contract, [name]: val});
    }

    const handleChangeDate = (str, i) => {
        let val = i;
        let name = str;
        setDateData({ ...dateData, [name]: val });
    }

    const handleChangeDateInput = (event) => {
        setDateData({...dateData, [event.target.name]: event.target.value});
    }

    const handleChangeFiles = (str, i) => {
        let val = i;
        let name = str;
        setFilesData({ ...fileData, [name]: val });
    }

    const handleChangeDep = (event) => {
        setDep({ ...departments, [event.target.name]: event.target.value })
    }

    const navigate = useNavigate();

    // ################################################################################# POST

    const handleSubmit = async => {
        // postDate();
        // postFile();
        postContract();

        alert("Контракт добвлен!!")

        navigate("/");
    }

    const postContract = () => {
        const article = {
            objects: contract.objects,
            customer: contract.customer,
            executor: contract.executor,
            responsible: contract.responsible,
            responsible2: contract.responsible2,
            states: contract.states
        };
        axios.post('http://localhost:8080/create/contract', article)
            .then(response => setContract(initialFormStateContract))
    };

    const postDate = () => {
        const article = {
            dateStart: dateData.dateStart,
            description: dateData.description
        };
        axios.post('http://localhost:8080/create/date', article)
            .then(response => setDateData(initialFormStateDates));
    };

    const postFile = () => {
        const article = { fileName: fileData.fileName };
        axios.post('http://localhost:8080/create/fileWay', article)
            .then(response => setFilesData(initialFormStateFiles));
    };
    // ################################################################################# POST

    const optionsDep = departments.map(dep => {
        const items = {
            value: dep.id,
            label: dep.name,
        };
        items.children = persons.filter(per => per.departmentId === dep.id).map(per => ({
            value: per.id,
            
            label: per.title,
        }));

        return items;
    })

    const normFile = (e) => {
        if (Array.isArray(e)) {
          return e;
        }
        return e?.fileList;
    };

    console.log(contract);
    console.log(dateData);
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
                        onChange={handleChangeInput} />
                </label>

                <label>
                    Заказчик:
                    <Select 
                        style={ {height: 50} }
                        name="customer"
                        onChange={handleChange.bind(this, "customer")}> 
                        {customers.map(cus => (
                            <Select.Option value={cus.id}> {cus.name} </Select.Option>
                        ))}
                    </Select>
                </label>

                <label>
                    Исполнитель:
                    <Input 
                        type="text"
                        name="executor"
                        onChange={handleChangeInput} />
                </label>

                <label>
                    Описание работ:
                    <Input 
                        type="text"
                        name="description"
                        onChange={handleChangeDateInput} />
                </label>

                <label>
                    Ответсвенный:
                    <Cascader
                        name="responsible"
                        style={ { width: "100%", color: "black", height: 50 } }
                        options={optionsDep}
                        onChange={handleChangeSelector.bind(this, "responsible")}
                    />
                </label>

                <label>
                    Ответсвенный-2:
                    <Cascader
                        name="responsible2"
                        style={ { width: "100%", color: "black", height: 50 } }
                        options={optionsDep}
                        onChange={handleChangeSelector.bind(this, "responsible2")}
                    />
                </label>

                <label>
                    Дата добавления:
                    <DatePicker style={ {height: 50} } name="dateStart" onChange={handleChangeDate.bind(this, "dateStart")}/>
                </label>

                <label>
                    Статус договора:
                    <Select onChange={handleChange.bind(this, "states")} name='states' style={ {height: 50} }>
                        {statuses.map(stat => (
                            <Select.Option value={stat.id}>{stat.title}</Select.Option>
                        ))}
                    </Select>
                </label>


                <Form.Item label="" valuePropName="fileList" getValueFromEvent={normFile}>
                    Файлы:
                    <Upload action="/upload.do" listType="picture-card">
                        <button style={{ border: 0, background: 'none' }} type="button">
                        <PlusOutlined style={{ color: "white" }} />
                        <div style={{ marginTop: 8, color: "white" }}>Загрузить</div>
                        </button>
                    </Upload>
                </Form.Item>

                <Button className="sub" onClick={handleSubmit}>Добавить</Button>
            </Form>
        </div>
    );
}

export default InputMain;