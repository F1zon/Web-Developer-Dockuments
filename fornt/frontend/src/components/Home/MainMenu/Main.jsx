import React, { useEffect, useState } from "react";
import axios from "axios";

function Main({ filters }) {
  const [docks, setDocks] = useState([]);
  const [filteredDocks, setFilteredDocks] = useState([]);
  const [selectedId, setSelId] = useState(null);

  // Получаем список документов
  useEffect(() => {
    fetch("http://localhost:8080/docks")
      .then((response) => response.json())
      .then((data) => {
        setDocks(data);
        setFilteredDocks(data); // по умолчанию все записи
      })
      .catch((error) => console.error("Error fetching documents: ", error));
  }, []);

  // Фильтруем данные при изменении filters
  useEffect(() => {
    console.log(filters.status);

    if (!filters || Object.keys(filters).length === 0) {
      setFilteredDocks(docks);
      return;
    }

    let result = [...docks];

    if (filters.objects) {
      result = result.filter((x) => x.objects?.includes(filters.objects));
    }

    if (filters.customer) {
      result = result.filter(
        (x) => String(x.customer) === String(filters.customer)
      );
    }

    if (filters.executor) {
      result = result.filter((x) =>
        x.executor?.toLowerCase().includes(filters.executor.toLowerCase())
      );
    }

    if (filters.department) {
      result = result.filter(
        (x) => String(x.department) === String(filters.department)
      );
    }

    if (filters.status) {
      result = result.filter(
        (x) => String(x.states) === String(filters.status)
      );
    }

    setFilteredDocks(result);
  }, [filters, docks]);

  const navigate = () => {
    window.location.href = "/editing";
  };

  const navigateReduct = () => {
    if (!selectedId) return alert("Выберите запись для редактирования");
    window.location.href = `/editing?id=${selectedId}`;
  };

  const deleteDocks = () => {
    if (!selectedId) return alert("Выберите запись для удаления");

    axios
      .delete(`http://localhost:8080/delete?id=${selectedId}`)
      .then(() => {
        setFilteredDocks((prev) =>
          prev.filter((d) => d.idContract !== selectedId)
        );
        setSelId(null);
      })
      .catch((err) => {
        console.error("Ошибка удаления", err);
        alert("Не удалось удалить запись");
      });
  };

  const setId = (event, value) => {
    setSelId(value);
  };

  function formatDate(dateString) {
    const date = new Date(dateString);

    const day = String(date.getDate()).padStart(2, "0");
    const month = String(date.getMonth() + 1).padStart(2, "0"); // Месяцы с 0
    const year = date.getFullYear();

    return `${day}.${month}.${year}`;
  }

  return (
    <div className="Main">
      <div className="RedactBar">
        <button className="crateDock" onClick={navigate}>
          Добавить
        </button>
        <button className="redactDock" onClick={navigateReduct}>
          Редактировать
        </button>
        <button className="deleteDock" onClick={deleteDocks}>
          Удалить
        </button>
      </div>

      <div className="DocksTable">
        <div className="InfoBar">
          <input
            type="checkbox"
            name="choseAll"
            id="getAll"
            className="custom-checkbox"
          />
          <p className="customer">Заказчик</p>
          <p className="object">Объект</p>
          <p className="executor">Исполнитель</p>
          <p className="pers">Ответственный</p>
          <p className="dep">Отдел</p>
          <p className="state">Статус</p>
          <p className="dst">Начало</p>
          <p className="dend">Срок текущего этапа</p>
        </div>

        <div className="docksTable">
          <table>
            <tbody>
              {filteredDocks.length > 0 ? (
                filteredDocks.map((dock) => (
                  <tr key={dock.idContract}>
                    <td>
                      <input
                        type="checkbox"
                        className="custom-checkbox"
                        onChange={(e) => setId(e, dock.idContract)}
                      />
                    </td>
                    <td className="customer">{dock.customer}</td>
                    <td className="object">{dock.objects}</td>
                    <td className="executor">{dock.executor}</td>
                    <td className="pers">{dock.responsible}</td>
                    <td className="dep">{dock.department}</td>
                    <td className="state">{dock.states}</td>
                    <td className="dst">{formatDate(dock.dateStart)}</td>
                    <td className="dend">{formatDate(dock.dateEnd)}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="7">Нет данных</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default Main;
