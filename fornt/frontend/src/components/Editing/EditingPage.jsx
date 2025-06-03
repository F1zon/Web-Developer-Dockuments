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
  Table,
} from "antd";
import { useLocation } from "react-router-dom";
import dayjs from "dayjs";
import { DownloadOutlined } from "@ant-design/icons";

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

  // const initialFormStateFiles = {
  //   fileArr: [],
  // };

  const initialFormStateDates = {
    dateStart: "",
    description: "",
  };

  // ################################################################################ Инициализация состояний для форм

  //  Состояния для всех данных с бд
  const [customers, setCust] = useState([]);
  const [persons, setPers] = useState([]);
  const [statuses, setStat] = useState([]);
  const [departments, setDep] = useState([]);

  const [object, setObject] = useState();
  const [executor, setExecutor] = useState();
  const [responsibleOne, setResponsibleOne] = useState();
  const [date, setDate] = useState(dayjs());
  const [customer, setCustomer] = useState();
  const [description, setDescription] = useState();
  const [responsibleTwo, setResponsibleTwo] = useState();
  const [status, setStatus] = useState();
  const [files, setFiles] = useState();

  const [contract, setContract] = useState(initialFormStateContract);
  const [fileData, setFilesData] = useState({ fileArr: [] });
  const [dateData, setDateData] = useState(initialFormStateDates);
  const [initialBlocks, setBlocks] = useState([
    { dateStartStage: dayjs(), descriptionStage: "", dateEndStage: dayjs() },
  ]);

  const [formData, setFormData] = useState(new FormData());
  const [fileList, setFileList] = useState([]); // Состояние для хранения fileList

  // ################################################################################ Добаление данных с сервера в состояние форм

  useEffect(() => {
    if (isCalledRef.current) {
      return;
    }
    fetch(`http://localhost:8080/edited/customer?id=${contract_id}`)
      .then((response) => response.clone().json())
      .then((data) => setCustomer(data))
      .catch((error) => console.log("Error fetching customer: ", error));

    fetch(`http://localhost:8080/edited/status?id=${contract_id}`)
      .then((response) => response.clone().json())
      .then((data) => setStatus(data))
      .catch((error) => console.log("Error fetching status: ", error));

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
          setDate(dayjs(data.date));
          setBlocks(data.stageDtoArr);
          setFiles(data.fileNames);
          const serverFiles = data.fileNames;

          // Создаем fileList только после получения данных
          const formattedFileList = serverFiles.map((fileName, index) => ({
            uid: index.toString(),
            name: fileName,
            status: "done",
          }));
          setFileList(formattedFileList);
        })
        .catch((error) =>
          console.log("Error fetcheng currentContract: ", error)
        );
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

  useEffect(() => {
    console.log("Обновилось состояние fileData:", fileData);
  }, [fileData]);

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

  const handleChangeFiles = (info) => {
    const { fileList } = info; // Получаем список файлов из события

    // Извлекаем реальные файлы из originFileObj
    const files = fileList
      .map((file) => file.originFileObj) // Получаем originFileObj
      .filter(Boolean); // Фильтруем undefined или null

    setFilesData({ fileArr: files }); // Сохраняем массив файлов в состоянии
    console.log("Массив файлов:", fileData);
  };

  const handleSubmit = (async) => {
    // Проверка: если статус === "В работе", то stage не может быть пустым
    if (status === 6 && initialBlocks.length === 0) {
      message.error("Нужно добавить хотя бы один этап");
      return;
    }

    updateContract();
    alert("Контракт сохранён");
    navigate("/");
  };

  const addBlock = () => {
    setBlocks([
      ...initialBlocks,
      { dateStartStage: dayjs(), descriptionStage: "", dateEndStage: dayjs() },
    ]);
  };

  const removeBlock = (index) => {
    const newBlocks = initialBlocks.filter((_, i) => i !== index);
    setBlocks(newBlocks);
  };

  const handleChangeBlocks = (index, field, value) => {
    const newBlocks = initialBlocks.map((block, i) => {
      if (i === index) {
        return { ...block, [field]: value };
      }
      return block;
    });
    setBlocks(newBlocks);
  };

  const navigate = useNavigate();

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
      stageDtoArr: initialBlocks,
    };

    const newFormData = new FormData();
    newFormData.append("model", JSON.stringify(articleContract));

    if (fileData.fileArr && fileData.fileArr.length > 0) {
      [...fileData.fileArr].forEach((file) => {
        if (file instanceof File) {
          newFormData.append("fileArr", file);
        } else {
          console.warn("Не файл:", file);
        }
      });
    } else {
      console.warn("Файлы отсутствуют");
    }

    for (let pair of newFormData.entries()) {
      console.log(pair[0], pair[1]);
    }

    if (!isNew) {
      // Update
      try {
        const response = await axios.post(
          "http://localhost:8080/update/contract",
          newFormData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          }
        );
        console.log(response.data);
      } catch (error) {
        console.error("Ошибка при отправке данных:", error);
      }
    } else {
      // Create
      axios.all([
        axios
          .post("http://localhost:8080/create/contract", newFormData)
          .then((response) => setContract(initialFormStateContract)),
      ]);
    }
  };

  // Обработчик скачивания файла
  const handleDownload = async (fileName) => {
    console.log("File name: ", fileName);

    try {
      // Выполняем GET-запрос с использованием fetch
      const response = await fetch(
        `http://localhost:8080/download?id=${contract_id}&fileName=${fileName}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json", // Указываем тип контента (если требуется)
          },
        }
      );

      // Проверяем, успешен ли запрос
      if (!response.ok) {
        throw new Error(`Ошибка при скачивании файла: ${response.statusText}`);
      }

      // Получаем бинарные данные файла
      const blob = await response.blob();

      // Создаем ссылку для скачивания
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", fileName); // Устанавливаем имя файла
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (error) {
      console.error("Ошибка при скачивании файла:", error);
      message.error("Не удалось скачать файл.");
    }
  };

  const handleDownloadFiles = async (filename) => {
    try {
      const response = await axios.get(
        `http://localhost:8080/download?id=${contract_id}&fileName=${filename}`,
        {
          responseType: "blob",
        }
      );

      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", filename);
      document.body.appendChild(link);
      link.click();
      link.remove();
    } catch (error) {
      console.error("Ошибка при скачивании файла:", error);
    }
  };

  const columns = [
    {
      title: "Имя файла",
      dataIndex: "filename",
      key: "uid",
    },
    {
      title: "Действия",
      key: "actions",
      render: (_, record) => (
        <Button
          type="link"
          onClick={() => handleDownloadFiles(record.filename)}
        >
          Скачать
        </Button>
      ),
    },
  ];

  // Преобразуем массив файлов в формат, подходящий для таблицы
  const dataSource = fileList.map((filename) => ({
    key: filename.uid, // Уникальный ключ для каждой строки
    filename: filename.name, // Имя файла
  }));

  // ################################################################################ Отображение страницы

  // console.log("fileData: ", fileData);
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
          {initialBlocks.map((block, index) => (
            <div key={index} className="stages">
              <label className="dateStartStage">
                Дата начала этапа:
                <DatePicker
                  style={{ height: 50, width: 200 }}
                  // disabled={true}
                  // value={date}
                  name="dateStartStage"
                  value={dayjs(block.dateStartStage)}
                  onChange={(date) =>
                    handleChangeBlocks(index, "dateStartStage", date)
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
                  value={block.descriptionStage}
                  onChange={(e) =>
                    handleChangeBlocks(
                      index,
                      "descriptionStage",
                      e.target.value
                    )
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
                  value={dayjs(block.dateEndStage)}
                  onChange={(date) =>
                    handleChangeBlocks(index, "dateEndStage", date)
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
            Файлы для скачивания:
            <Table dataSource={dataSource} columns={columns} rowKey="uid" />
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
