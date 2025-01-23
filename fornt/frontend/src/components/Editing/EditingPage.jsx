import React, { useEffect, useState, useRef } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useNavigate,
} from "react-router-dom";
import { useParams } from "react-router-dom";
import "../Create/InputFild.css";
import { PlusOutlined, MinusCircleOutlined } from "@ant-design/icons";
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
  Space,
} from "antd";
import { useLocation } from "react-router-dom";
import dayjs from "dayjs";

const EditedPage = () => {
  const isCalledRef = React.useRef(false);
  const params = useLocation().search;
  const contract_id = new URLSearchParams(params).get("id");
  console.log(contract_id);

  const isNew = contract_id == null;

  // ################################################################################ Формы для данных

  const initialFormStateContract = {
    objects: "",
    customer: "",
    executor: "",
    responsible: "",
    responsible2: "",
    states: "",
  };

  const initialFormStateFiles = {
    fileName: new Array(),
  };

  const initialFormStateDates = {
    dateStart: "",
    description: "",
  };

  // ################################################################################ Инициализация состояний для форм

  //  Состояния для всех данных с бд

  // TODO: Разобрать useState для внесения изменений
  const [customers, setCust] = useState([]);
  const [persons, setPers] = useState([]);
  const [statuses, setStat] = useState([]);
  const [departments, setDep] = useState([]);

  const [currentContract, setCurrentContract] = useState({});

  const [personal, setPersonal] = useState([]);
  //   const [status, setStatus] = useState([]);
  const [department, setDepartment] = useState([]);
  const [datas, setDatas] = useState([]);

  //

  const [object, setObject] = useState();
  const [executor, setExecutor] = useState();
  const [responsibleOne, setResponsibleOne] = useState();
  const [date, setDate] = useState(dayjs());
  const [customer, setCustomer] = useState();
  const [description, setDescription] = useState();
  const [responsibleTwo, setResponsibleTwo] = useState();
  const [status, setStatus] = useState();

  //

  const [contract, setContract] = useState(initialFormStateContract);
  const [fileData, setFilesData] = useState(initialFormStateFiles);
  const [dateData, setDateData] = useState(initialFormStateDates);
  const [messageApi, contextHolder] = message.useMessage();

  // ################################################################################ Добаление данных с сервера в состояние форм

  useEffect(() => {
    if (isCalledRef.current) {
      return;
    }
    fetch(`http://localhost:8080/edited/customer?id=${contract_id}`)
      .then((response) => response.clone().json())
      .then((data) => setCustomer(data))
      .catch((error) => console.log("Error fetching customer: ", error));

    fetch(`http://localhost:8080/edited/personal?id=${contract_id}`)
      .then((response) => response.json())
      .then((data) => setPersonal(data))
      .catch((error) => console.log("Error fetching personal: ", error));

    fetch(`http://localhost:8080/edited/status?id=${contract_id}`)
      .then((response) => response.clone().json())
      .then((data) => setStatus(data))
      .catch((error) => console.log("Error fetching status: ", error));

    fetch(`http://localhost:8080/info/dep`)
      .then((response) => response.clone().json())
      .then((data) => setDepartment(data))
      .catch((error) => console.log("Error fetcheng departments: ", error));

    fetch(`http://localhost:8080/edited/dates?id=${contract_id}`)
      .then((response) => response.clone().json())
      .then((data) => setDatas(data))
      .catch((error) => console.log("Error fetcheng dates: ", error));

    if (!isNew) {
      fetch(`http://localhost:8080/edited/contract?id=${contract_id}`)
        .then((response) => response.clone().json())
        .then((data) => {
          setCustomer(data.customer);
          setObject(data.object);
          setDescription(data.description);
          setResponsibleOne([data.departmentOne, data.responsibleOne]);
          setResponsibleTwo([data.departmentTwo, data.responsibleTwo]);
          setExecutor(data.executor);
          setStatus(data.status);
          setDate(dayjs(data.date.format("DD-MM-YYYY")));
        })
        .catch((error) =>
          console.log("Error fetcheng currentContract: ", error)
        );
      //  {
      //     "id": 7,
      //     "customerId": 1,
      //     "responsibleId": 4,
      //     "departmentIdOne": 4, TODO: Добавить на бэк в ответ
      //     "responsible2Id": 3,
      //     "departmentIdTwo": 3, TODO: Добавить на бэк в ответ
      //     "statesTitle": 6,
      //     "objectTitle": "Тестовый объект 2",
      //     "executor": "СИБМАРК Проект"
      //     "date": test TODO: Добавить в ответ на бэк
      // }
    }

    fetch("http://localhost:8080/info/customers")
      .then((response) => response.clone().json())
      .then((data) => setCust(data))
      .catch((error) => console.log("Error fetching customers: ", error));

    fetch("http://localhost:8080/info/personal")
      .then((response) => response.clone().json())
      .then((data) => setPers(data))
      .catch((error) => console.log("Error fetching personal: ", error));

    fetch("http://localhost:8080/info/statuses")
      .then((response) => response.clone().json())
      .then((data) => setStat(data))
      .catch((error) => console.log("Error fetching statuses: ", error));

    fetch("http://localhost:8080/info/dep")
      .then((response) => response.clone().json())
      .then((data) => setDep(data))
      .catch((error) => console.log("Error fetcheng departments: ", error));

    isCalledRef.current = true;
  }, []);

  // ################################################################################ Заполнение данных с клиента

  const handleChange = (str, i) => {
    let val = i;
    let name = str;
    setContract({ ...contract, [name]: val });
  };

  const handleChangeInput = (event) => {
    setContract({ ...contract, [event.target.name]: event.target.value });
  };

  const handleChangeSelector = (str, i) => {
    let val = i[i.length - 1];
    let name = str;
    setContract({ ...contract, [name]: val });
  };

  const handleChangeDate = (str, i) => {
    let val = i;
    let name = str;
    setDateData({ ...dateData, [name]: val });
  };

  const handleChangeDateInput = (event) => {
    setDateData({ ...dateData, [event.target.name]: event.target.value });
  };

  const handleChangeFiles = ({ fileList: filus }) => {
    // console.log(typeof(filus));
    // var tmpFiles = fileData.fileName;
    // tmpFiles.push(new File(filus));

    // console.log(Object.values(tmpFiles));

    // console.log(filus);
    setFilesData({ fileName: filus });
  };

  const handleSubmit = (async) => {
    updateContract();
    // postDate();
    // postFile();

    alert("Контракт сохранён");

    navigate("/");
  };

  // Раболта с блоками
  const [blocks, setBlocks] = useState([
    { startDate: null, description: "", endDate: null },
  ]);

  const addBlock = () => {
    setBlocks([...blocks, { startDate: null, description: "", endDate: null }]);
  };

  const removeBlock = (index) => {
    const newBlocks = blocks.filter((_, i) => i !== index);
    setBlocks(newBlocks);
  };

  const handleChangeBlocks = (index, field, value) => {
    const newBlocks = blocks.map((block, i) => {
      if (i === index) {
        return { ...block, [field]: value };
      }
      return block;
    });
    setBlocks(newBlocks);
  };

  const navigate = useNavigate();
  // const formatedDate = dayjs(datas.dateStart);

  const optionsDep = departments.map((dep) => {
    const items = {
      value: dep.id,
      label: dep.name,
    };
    items.children = persons
      .filter((per) => per.departmentId === dep.id)
      .map((per) => ({
        value: per.id,
        label: per.title,
      }));

    return items;
  });

  // const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

  const updateContract = async () => {
    const articleContract = {
      id: contract_id,
      object: object,
      customer: customer,
      executor: executor,
      responsibleOne: responsibleOne.at(1),
      departmentOne: responsibleOne.at(0),
      responsibleTwo: responsibleTwo.at(1),
      departmentTwo: responsibleTwo.at(0),
      status: status,
      date: date.format("DD-MM-YYYY"),
      description: description,
    };

    console.log(articleContract);

    const articleFile = {
      fileUrl: Object.values(fileData.fileName),
    };

    if (!isNew) {
      // Update
      axios.all([
        axios
          .post("http://localhost:8080/update/contract", articleContract)
          .then((response) => setContract(initialFormStateContract)),
      ]);
    } else {
      // Create
      axios.all([
        axios
          .post("http://localhost:8080/create/contract", articleContract)
          .then((response) => setContract(initialFormStateContract)),
      ]);
    }
  };

  // ################################################################################ Отображение страницы

  console.log("Blocks info: ", blocks);
  return (
    <div className="container-input">
      <Header className="impHeader" />

      <div className="massage">
        <div className="line"></div>
        <h2 className="title">Подсказка</h2>
        <p className="text1">
          Для добавления нового договора заполните все обязательные поля и
          нажмите кнопку <b>“добавить”</b>.
        </p>
        <p className="text2">
          Если нужно отменить все изменения и вернуться обратно на основную
          страницу, нажмите кнопку <b>“Удалить”</b>.
        </p>
      </div>

      <Form method="post" className="inpForm" onSubmit={handleSubmit}>
        <label>
          Объект:
          <Input
            value={object}
            type="text"
            name="objects"
            onChange={(event) => setObject(event.target.value)}
          />
        </label>

        <label>
          Заказчик:
          <Select
            style={{ height: 50 }}
            value={customer}
            name="customer"
            onChange={(value) => setCustomer(value)}
          >
            {customers.map((cus) => (
              <Select.Option value={cus.id}> {cus.name} </Select.Option>
            ))}
          </Select>
        </label>

        <label>
          Компания исполнитель:
          <Input
            value={executor}
            type="text"
            name="executor"
            onChange={(event) => setExecutor(event.target.value)}
          />
        </label>

        <label>
          Предмет договора :
          <Input
            value={description}
            type="text"
            name="description"
            onChange={(event) => setDescription(event.target.value)}
          />
        </label>

        <label>
          Ответсвенный:
          <Cascader
            value={responsibleOne}
            name="responsible"
            style={{ width: "100%", color: "black", height: 50 }}
            options={optionsDep}
            onChange={(value) => setResponsibleOne(value)}
          />
        </label>

        <label>
          Ответсвенный-2:
          <Cascader
            value={responsibleTwo}
            name="responsible2"
            style={{ width: "100%", color: "black", height: 50 }}
            options={optionsDep}
            onChange={(value) => setResponsibleTwo(value)}
          />
        </label>

        <label>
          Дата добавления:
          <DatePicker
            style={{ height: 50 }}
            // disabled={true}
            value={date}
            name="dateStart"
            onChange={(date) => setDate(date)}
          />
        </label>

        <label>
          Статус договора:
          <Select
            name="states"
            style={{ height: 50 }}
            value={status}
            onChange={(value) => setStatus(value)}
          >
            {statuses.map((stat) => (
              <Select.Option value={stat.id}>{stat.title}</Select.Option>
            ))}
          </Select>
        </label>

        <div className="stageForm">
          {blocks.map((block, index) => (
            <div key={index} className="stages">
              <label className="dateStartStage">
                Дата начала этапа:
                <DatePicker
                  style={{ height: 50, width: 200 }}
                  // disabled={true}
                  // value={date}
                  name="dateStartStage"
                  value={block.startDate}
                  onChange={(date) =>
                    handleChangeBlocks(index, "startDate", date)
                  }
                />
              </label>

              <label className="descriptionStage">
                Описание этапа:
                <Input
                  style={{ height: 50, width: 400 }}
                  // value={description}
                  type="text"
                  name="descriptionStage"
                  value={block.description}
                  onChange={(e) =>
                    handleChangeBlocks(index, "description", e.target.value)
                  }
                />
              </label>

              <label className="dateEndStage">
                Дата сдачи этапа:
                <DatePicker
                  style={{ height: 50, width: 200 }}
                  // disabled={true}
                  // value={date}
                  name="dateEndStage"
                  value={block.endDate}
                  onChange={(date) =>
                    handleChangeBlocks(index, "endDate", date)
                  }
                />
              </label>

              <Button
                type="dashed"
                icon={<MinusCircleOutlined />}
                onClick={() => removeBlock(index)}
                className="removeStage"
              >
                Удалить этап
              </Button>
            </div>
          ))}

          <Button
            type="dashed"
            icon={<PlusOutlined />}
            onClick={addBlock}
            className="addStage"
          >
            Добавить новый этап
          </Button>
        </div>

        <div className="fildFiles">
          <label className="upload">
            Файлы договора:
            <Form.Item label="" className="uploadFiles">
              <Upload
                beforeUpload={() => false}
                onChange={handleChangeFiles}
                // action={handleChangeFiles}
                listType="picture-card"
                multiple
              >
                <button style={{ border: 0, background: "none" }} type="button">
                  <PlusOutlined style={{ color: "white" }} />
                  <div style={{ marginTop: 8, color: "white" }}>Загрузить</div>
                </button>
              </Upload>
            </Form.Item>
          </label>

          <label className="upload">
            Акты и счета:
            <Form.Item label="" className="uploadFiles">
              <Upload
                beforeUpload={() => false}
                onChange={handleChangeFiles}
                // action={handleChangeFiles}
                listType="picture-card"
                multiple
              >
                <button style={{ border: 0, background: "none" }} type="button">
                  <PlusOutlined style={{ color: "white" }} />
                  <div style={{ marginTop: 8, color: "white" }}>Загрузить</div>
                </button>
              </Upload>
            </Form.Item>
          </label>
        </div>

        <Button className="sub" onClick={handleSubmit}>
          Сохранить
        </Button>
      </Form>
    </div>
  );
};

export default EditedPage;
