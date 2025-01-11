import filter from './filter.svg'
import logout from './logout.svg'

import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Switch, Route, Link, useNavigate } from 'react-router-dom';
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

function Menu() {

    const initialFormStateContract = {
        objects: '',
        customer: '',
        executor: '',
        responsible: '',
        responsible2: '',
        states: ''
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

    const handleChange = (str, i) => {
        let val = i;
        let name = str;
        setContract({ ...contract, [name]: val })
    }

    const handleChangeInput = (event) => {
        setContract( { ...contract, [event.target.name]: event.target.value } )
    }


    return(
        <div className="Menu">
            <div className="headText">
                <h1 className="D">Docks-</h1>
                <h1 className="T">Tools</h1>
            </div>

            <div className="ButtonList">
                <div className="butRow">
                    <img src={filter} alt="filterImg" className="ButImg" />
                    <a href="" className="ButText">Фильтры</a>
                </div>

                <div className="selectFilter">
                    <p>Объект</p>
                    <Input 
                        type="text"
                        name="objects"
                        onChange={handleChangeInput} />
                </div>

                <div className="selectFilter">
                    <p>Заказчик</p>
                    <Select
                        className='selectCustomer'
                        name="customer"
                        onChange={handleChange.bind(this, "customer")}> 
                        {customers.map(cus => (
                            <Select.Option value={cus.id}> {cus.name} </Select.Option>
                        ))}
                    </Select>
                    {/* <input className="searchCustomer" placeholder="Заказчик"></input> */}
                </div>

                <div className="selectFilter">
                    <p>Исполнитель</p>
                    <Input 
                        type="text"
                        name="executor"
                        onChange={handleChangeInput} />
                </div>

                <div className="selectFilter">
                    <p>Отдел</p>
                    <Select
                        className='selectCustomer'
                        name="customer"
                        onChange={handleChange.bind(this, "customer")}> 
                        {departments.map(dep => (
                            <Select.Option value={dep.id}> {dep.name} </Select.Option>
                        ))}
                    </Select>
                    {/* <input className="searchDepartment" placeholder="Отдел"></input> */}
                </div>

                <div className="selectFilter">
                    <p>Статус</p>
                    <Select
                        className='selectCustomer'
                        name="customer"
                        onChange={handleChange.bind(this, "customer")}> 
                        {statuses.map(cus => (
                            <Select.Option value={cus.id}> {cus.title} </Select.Option>
                        ))}
                    </Select>
                    {/* <input className="searchStatus" placeholder="Статус"></input> */}
                </div>
            </div>

            <div className="exit">
                <div className="butRow">
                    <img src={logout} alt="logIutImg" className="ButImg" />
                    <a href="" className="ButText">Выйти</a>
                </div>
            </div>
        </div>
    );
}

export default Menu;