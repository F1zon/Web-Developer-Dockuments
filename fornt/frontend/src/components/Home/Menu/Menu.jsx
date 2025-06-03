import filter from "./filter.svg";
import logout from "./logout.svg";

import React, { useEffect, useState } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useNavigate,
} from "react-router-dom";
import { PlusOutlined } from "@ant-design/icons";
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
  Slnameer,
  TreeSelect,
  Upload,
} from "antd";

function Menu({ object, setObject, onApplyFilters }) {
  const [contract, setContract] = useState({
    objects: object || "",
    customer: "",
    executor: "",
    department: "",
    status: "",
  });

  //   const initialFormStateDates = {
  //     dateStart: "",
  //     description: "",
  //   };

  const [customers, setCust] = useState([]);
  const [persons, setPers] = useState([]);
  const [statuses, setStat] = useState([]);
  const [departments, setDep] = useState([]);
  const [messageApi, contextHolder] = message.useMessage();
  //   const [contract, setContract] = useState(initialFormStateContract);
  //   const [dateData, setDateData] = useState(initialFormStateDates);

  // ################################################################################# GET
  useEffect(() => {
    fetch("http://localhost:8080/info/customers")
      .then((res) => res.json())
      .then((data) => setCust(data))
      .catch((err) => messageApi.error("Ошибка загрузки заказчиков"));

    fetch("http://localhost:8080/info/personal")
      .then((res) => res.json())
      .then((data) => setPers(data))
      .catch((err) => messageApi.error("Ошибка загрузки сотрудников"));

    fetch("http://localhost:8080/info/statuses")
      .then((res) => res.json())
      .then((data) => setStat(data))
      .catch((err) => messageApi.error("Ошибка загрузки статусов"));

    fetch("http://localhost:8080/info/dep")
      .then((res) => res.json())
      .then((data) => setDep(data))
      .catch((err) => messageApi.error("Ошибка загрузки отделов"));
  }, []);

  const handleChange = (name) => (value) => {
    setContract((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  //   const handleChange = (str, i) => {
  //     let val = i;
  //     let name = str;
  //     setContract({ ...contract, [name]: val });
  //   };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setContract((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = () => {
    onApplyFilters(contract); // передаём фильтры наверх
  };

//   const handleChangeInput = (event) => {
//     setContract({ ...contract, [event.target.name]: event.target.value });
//   };

//   const getContractDataFilters = () => {
//     return contract;
//   };

  return (
    <div className="Menu">
      <div className="headText">
        <h1 className="D">Docks-</h1>
        <h1 className="T">Tools</h1>
      </div>

      <div className="ButtonList">
        <div className="butRow">
          <img src={filter} alt="filterImg" className="ButImg" />
          <a href="" className="ButText">
            Фильтры
          </a>
        </div>

        <div className="selectFilter">
          <p>Объект</p>
          <Input
            value={contract.objects}
            type="text"
            name="objects"
            onChange={(e) => handleChange("objects")(e.target.value)}
          />
        </div>

        <div className="selectFilter">
          <p>Заказчик</p>
          <Select
            className="selectCustomer"
            name="customer"
            // onChange={handleChange.bind(this, "customer")}
            onChange={handleChange("customer")}
          >
            {customers.map((cus) => (
              <Select.Option value={cus.name}> {cus.name} </Select.Option>
            ))}
          </Select>
          {/* <input className="searchCustomer" placeholder="Заказчик"></input> */}
        </div>

        <div className="selectFilter">
          <p>Исполнитель</p>
          <Input
            type="text"
            name="executor"
            value={contract.executor}
            onChange={handleInputChange}
            // onChange={handleChangeInput}
          />
        </div>

        <div className="selectFilter">
          <p>Отдел</p>
          <Select
            className="selectCustomer"
            name="customer"
            // onChange={handleChange.bind(this, "customer")}
            onChange={handleChange("department")}
          >
            {departments.map((dep) => (
              <Select.Option value={dep.name}> {dep.name} </Select.Option>
            ))}
          </Select>
          {/* <input className="searchDepartment" placeholder="Отдел"></input> */}
        </div>

        <div className="selectFilter">
          <p>Статус</p>
          <Select
            className="selectCustomer"
            name="customer"
            // onChange={handleChange.bind(this, "customer")}
            onChange={handleChange("status")}
          >
            {statuses.map((stat) => (
              <Select.Option value={stat.title}> {stat.title} </Select.Option>
            ))}
          </Select>
          {/* <input className="searchStatus" placeholder="Статус"></input> */}
        </div>

        {/* Кнопка применения фильтров */}
        <Button type="primary" onClick={handleSubmit} block>
          Применить фильтры
        </Button>
      </div>

      <div className="exit">
        <div className="butRow">
          <img src={logout} alt="logIutImg" className="ButImg" />
          <a href="" className="ButText">
            Выйти
          </a>
        </div>
      </div>
    </div>
  );
}

export default Menu;
