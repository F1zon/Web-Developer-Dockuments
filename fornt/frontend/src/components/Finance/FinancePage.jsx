import React, { useEffect, useState, useRef } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useNavigate,
  useParams,
} from "react-router-dom";
import { useLocation } from "react-router-dom";
import dayjs from "dayjs";
import { PlusOutlined, MinusCircleOutlined } from "@ant-design/icons";
import { DownloadOutlined } from "@ant-design/icons";
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
import axios from "axios";

import "../Create/InputFild.css";
import "./Finance.css";
import Header from "../Home/Headers/Headers";

const FinancePage = () => {
  const isCalledRef = React.useRef(false);
  const params = useLocation().search;
  const contract_id = new URLSearchParams(params).get("id");
  const navigate = useNavigate();

  const [initialBlocks, setBlocks] = useState([
    { dateStartStage: dayjs(), descriptionStage: "", dateEndStage: dayjs() },
  ]);

  const handleSubmit = (async) => {
    updateFincace();
    alert("Информация сохранёна");
    navigate("/editing?id=" + contract_id);
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

  const updateFincace = async () => {};

  // Состояние для хранения данных таблицы
  const [data, setData] = useState([]);

  // Состояние для управления видимостью модального окна редактирования
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);

  // Состояние для управления видимостью модального окна добавления
  const [isAddModalOpen, setIsAddModalOpen] = useState(false);

  // Состояние для хранения данных выбранной строки (для редактирования)
  const [editingRow, setEditingRow] = useState(null);

  // Состояние для хранения новых данных (для добавления)
  const [newRow, setNewRow] = useState({
    date_formation: "",
    number: "",
    item: "",
    responsible_department: "",
    amount: "",
  });

  // Имитация загрузки данных с сервера
  useEffect(() => {
    const fetchData = async () => {
      const serverData = [
        {
          id: 1,
          date_formation: "2023-10-01",
          number: "12345",
          item: "Товар A",
          responsible_department: "Отдел 1",
          amount: "1000",
        },
        {
          id: 2,
          date_formation: "2023-10-02",
          number: "67890",
          item: "Товар B",
          responsible_department: "Отдел 2",
          amount: "2000",
        },
      ];
      setData(serverData);
    };
    fetchData();
  }, []);

  // Обработчик двойного клика на строку
  const handleRowDoubleClick = (row) => {
    setEditingRow(row); // Сохраняем данные строки для редактирования
    setIsEditModalOpen(true); // Открываем модальное окно редактирования
  };

  // Обработчик изменения полей формы добавления
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewRow((prev) => ({ ...prev, [name]: value }));
  };

  // Обработчик отправки формы добавления
  const handleAddSubmit = async (e) => {
    e.preventDefault();

    // Генерируем уникальный ID для новой строки
    const newId =
      data.length > 0 ? Math.max(...data.map((item) => item.id)) + 1 : 1;

    // Создаем новую строку
    const newRowWithId = { id: newId, ...newRow };

    try {
      // Имитация отправки данных на сервер
      await fetch("https://example.com/api/data", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newRowWithId),
      });

      // Обновляем состояние таблицы
      setData((prev) => [...prev, newRowWithId]);

      // Очищаем форму добавления
      setNewRow({
        date_formation: "",
        number: "",
        item: "",
        responsible_department: "",
        amount: "",
      });

      // Закрываем модальное окно
      setIsAddModalOpen(false);
    } catch (error) {
      console.error("Ошибка при добавлении данных:", error);
    }
  };

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

      <div className="table-container">
        {/* Кнопка для добавления новых данных */}
        <button onClick={() => setIsAddModalOpen(true)} className="add-button">
          Добавить данные
        </button>

        {/* Таблица */}
        <table>
          <thead>
            <tr>
              <th className="topTable">Дата формирования</th>
              <th className="topTable">Номер</th>
              <th className="topTable">Предмет</th>
              <th className="topTable">Ответственный отдел</th>
              <th className="topTable">Сумма</th>
            </tr>
          </thead>
          <tbody>
            {data.map((row) => (
              <tr key={row.id} onDoubleClick={() => handleRowDoubleClick(row)}>
                <td>{row.date_formation}</td>
                <td>{row.number}</td>
                <td>{row.item}</td>
                <td>{row.responsible_department}</td>
                <td>{row.amount}</td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Модальное окно добавления */}
        {isAddModalOpen && (
          <div className="modal">
            <div className="modal-content">
              <h3>Добавление новых данных</h3>
              <form onSubmit={handleAddSubmit}>
                <label htmlFor="date_formation">Дата формирования:</label>
                <input
                  type="text"
                  id="date_formation"
                  name="date_formation"
                  value={newRow.date_formation}
                  onChange={handleInputChange}
                  required
                />

                <label htmlFor="number">Номер:</label>
                <input
                  type="text"
                  id="number"
                  name="number"
                  value={newRow.number}
                  onChange={handleInputChange}
                  required
                />

                <label htmlFor="item">Предмет:</label>
                <input
                  type="text"
                  id="item"
                  name="item"
                  value={newRow.item}
                  onChange={handleInputChange}
                  required
                />

                <label htmlFor="responsible_department">
                  Ответственный отдел:
                </label>
                <input
                  type="text"
                  id="responsible_department"
                  name="responsible_department"
                  value={newRow.responsible_department}
                  onChange={handleInputChange}
                  required
                />

                <label htmlFor="amount">Сумма:</label>
                <input
                  type="text"
                  id="amount"
                  name="amount"
                  value={newRow.amount}
                  onChange={handleInputChange}
                  required
                />

                <button type="submit">Добавить</button>
                <button type="button" onClick={() => setIsAddModalOpen(false)}>
                  Отмена
                </button>
              </form>
            </div>
          </div>
        )}

        {/* Модальное окно редактирования */}
        {isEditModalOpen && (
          <div className="modal">
            <div className="modal-content">
              <h3>Редактирование данных</h3>
              {/* Здесь оставляем логику редактирования, как в предыдущем примере */}
              <form onSubmit={handleSubmit}>
                <label htmlFor="date_formation">Дата формирования:</label>
                <input
                  type="text"
                  id="date_formation"
                  name="date_formation"
                  value={editingRow?.date_formation || ""}
                  onChange={handleInputChange}
                  required
                />

                <label htmlFor="number">Номер:</label>
                <input
                  type="text"
                  id="number"
                  name="number"
                  value={editingRow?.number || ""}
                  onChange={handleInputChange}
                  required
                />

                <label htmlFor="item">Предмет:</label>
                <input
                  type="text"
                  id="item"
                  name="item"
                  value={editingRow?.item || ""}
                  onChange={handleInputChange}
                  required
                />

                <label htmlFor="responsible_department">
                  Ответственный отдел:
                </label>
                <input
                  type="text"
                  id="responsible_department"
                  name="responsible_department"
                  value={editingRow?.responsible_department || ""}
                  onChange={handleInputChange}
                  required
                />

                <label htmlFor="amount">Сумма:</label>
                <input
                  type="text"
                  id="amount"
                  name="amount"
                  value={editingRow?.amount || ""}
                  onChange={handleInputChange}
                  required
                />

                <button type="submit">Сохранить</button>
                <button type="button" onClick={() => setIsEditModalOpen(false)}>
                  Отмена
                </button>
              </form>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default FinancePage;
