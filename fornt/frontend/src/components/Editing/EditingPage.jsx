import React, { useEffect, useState, useRef } from "react";
import { BrowserRouter as Router, Switch, Route, Link, useNavigate } from 'react-router-dom';
import { useParams } from "react-router-dom";
import '../Create/InputFild.css';
import { PlusOutlined } from '@ant-design/icons';
import Header from "../Home/Headers/Headers";
import axios from "axios";
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
import { useLocation  } from "react-router-dom";
import dayjs from 'dayjs';

const EditedPage = () => {

    const isCalledRef = React.useRef(false);
    const params = useLocation().search;
    const contract_id = new URLSearchParams(params).get("id");
    console.log(contract_id);

// ################################################################################ Формы для данных

const initialFormStateContract = {
    objects: '',
    customer: '',
    executor: '',
    responsible: '',
    responsible2: '',
    states: ''
};

const initialFormStateFiles = {
    fileName: new Array()
};

const initialFormStateDates = {
    dateStart: '',
    description: ''
};

// ################################################################################ Инициализация состояний для форм

//  Состояния для всех данных с бд

    // TODO: Разобрать useState для внесения изменений
    const [customers, setCust] = useState([]);
    const [persons, setPers] = useState([]);
    const [statuses, setStat] = useState([]);
    const [departments, setDep] = useState([]);

    const [currentContract, setCurrentContract] = useState([]);
    const [customer, setCustomer] = useState([]);
    const [personal, setPersonal] = useState([]);
    const [status, setStatus] = useState([]);
    const [department, setDepartment] = useState([]);
    const [datas, setDatas] = useState([]);

    const [contract, setContract] = useState(initialFormStateContract);
    const [fileData, setFilesData] = useState(initialFormStateFiles);
    const [dateData, setDateData] = useState(initialFormStateDates);
    const [messageApi, contextHolder] = message.useMessage();

// ################################################################################ Добаление данных с сервера в состояние форм

useEffect(() => {
    if (isCalledRef.current){
        return;
    }
    fetch(`http://localhost:8080/edited/customer?id=${contract_id}`)
        .then(response => response.clone().json())
        .then(data => setCustomer(data))
        .catch(error => console.log('Error fetching customer: ', error));
    
    fetch(`http://localhost:8080/edited/personal?id=${contract_id}`)
        .then(response => response.json())
        .then(data => setPersonal(data))
        .catch(error => console.log('Error fetching personal: ', error));

    fetch(`http://localhost:8080/edited/status?id=${contract_id}`)
        .then(response => response.clone().json())
        .then(data => setStatus(data))
        .catch(error => console.log('Error fetching status: ', error));
    
    fetch(`http://localhost:8080/info/dep?id=${contract_id}`)
        .then(response => response.clone().json())
        .then(data => setDepartment(data))
        .catch(error => console.log('Error fetcheng departments: ', error));

    fetch(`http://localhost:8080/edited/dates?id=${contract_id}`)
        .then(response => response.clone().json())
        .then(data => setDatas(data))
        .catch(error => console.log('Error fetcheng dates: ', error));
    
    fetch(`http://localhost:8080/edited/contract?id=${contract_id}`)
        .then(response => response.clone().json())
        .then(data => setCurrentContract(data))
        .catch(error => console.log('Error fetcheng currentContract: ', error));

    fetch('http://localhost:8080/info/customers')
        .then(response => response.clone().json())
        .then(data => setCust(data))
        .catch(error => console.log('Error fetching customers: ', error));
    
    fetch('http://localhost:8080/info/personal')
        .then(response => response.clone().json())
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

    isCalledRef.current = true
}, []);

// ################################################################################ Заполнение данных с клиента

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

const handleChangeFiles = ({fileList: filus}) => {
    // console.log(typeof(filus));
    // var tmpFiles = fileData.fileName;
    // tmpFiles.push(new File(filus));

    // console.log(Object.values(tmpFiles));

    // console.log(filus);
    setFilesData({ fileName: filus });
}

const handleSubmit = async => {
    updateContract();
    // postDate();
    // postFile();

    alert("Контракт сохранён")

    navigate("/");
}

// ################################################################################ Вспомогательные функции

const navigate = useNavigate();
const formatedDate = dayjs(datas.dateStart);

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

const delay = ms => new Promise(
    resolve => setTimeout(resolve, ms)
);

const updateContract = async () => {
    const articleContract = {
        id: contract.id,
        objects: contract.objects,
        customer: contract.customer,
        executor: contract.executor,
        responsible: contract.responsible,
        responsible2: contract.responsible2,
        states: contract.states
    };

    const articleDate = {
        dateStart: dateData.dateStart,
        description: dateData.description
    };

    const articleFile = {
        fileUrl: Object.values(fileData.fileName)
    }

    let fd = new FormData();
    fd.append("files", fileData.fileName);

    axios.all([
        axios.post('http://localhost:8080/update/contract', articleContract)
        .then(response => setContract(initialFormStateContract)),

        await delay(1000),

        // axios.post('http://localhost:8080/create/date', articleDate)
        // .then(response => setDateData(initialFormStateDates)),

        // axios.post('http://localhost:8080/create/fileWay', articleFile)
        // .then(response => setFilesData(initialFormStateFiles))
    ]);


};

// ################################################################################ Отображение страницы

 console.log("dates: ", datas.dateStart);
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
                        value={customer.name}
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
                        value={currentContract.executor}
                        type="text"
                        name="executor"
                        onChange={handleChangeInput} />
                </label>

                <label>
                    Описание работ:
                    <Input 
                        value={datas.description}
                        type="text"
                        name="description"
                        onChange={handleChangeDateInput} />
                </label>

                <label>
                    Ответсвенный:
                    <Cascader
                        // value={personal.at(0).title}
                        name="responsible"
                        style={ { width: "100%", color: "black", height: 50 } }
                        options={optionsDep}
                        onChange={handleChangeSelector.bind(this, "responsible")}
                    />
                </label>

                <label>
                    Ответсвенный-2:
                    <Cascader
                        // value={personal.at(1).title}
                        name="responsible2"
                        style={ { width: "100%", color: "black", height: 50 } }
                        options={optionsDep}
                        onChange={handleChangeSelector.bind(this, "responsible2")}
                    />
                </label>

                <label>
                    Дата добавления:
                    <DatePicker style={ {height: 50} } value={formatedDate} name="dateStart" onChange={handleChangeDate.bind(this, "dateStart")}/>
                </label>

                <label>
                    Статус договора:
                    <Select onChange={handleChange.bind(this, "states")} name='states' style={ {height: 50} } value={status.title}>
                        {statuses.map(stat => (
                            <Select.Option value={stat.id}>{stat.title}</Select.Option>
                        ))}
                    </Select>
                </label>


                <label className="upload">
                    Файлы:
                    <Form.Item label="" className="uploadFiles">
                        
                        <Upload
                        beforeUpload={() => false} 
                        onChange={handleChangeFiles}
                        // action={handleChangeFiles}
                        listType="picture-card"
                        multiple
                        >
                            <button style={{ border: 0, background: 'none' }} type="button">
                            <PlusOutlined style={{ color: "white" }} />
                            <div style={{ marginTop: 8, color: "white" }}>Загрузить</div>
                            </button>
                        </Upload>
                    </Form.Item>
                </label>

                <Button className="sub" onClick={handleSubmit}>Сохранить</Button>
            </Form>
        </div>
    );
}


export default EditedPage;
