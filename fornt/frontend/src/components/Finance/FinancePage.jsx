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

      <div className="contractForm">
        <Form method="post" className="inpForm" onSubmit={handleSubmit}>
          <label className="mainDateStart">
            Дата добавления:
            <DatePicker
              style={{ height: 50 }}
              // disabled={true}
              //   value={date}
              name="dateStart"
              //   onChange={(date) => setDate(date)}
            />
          </label>

          <label className="numberContract">
            Номер:
            <Input
              // value={object}
              type="text"
              name="objects"
              // onChange={(event) => setObject(event.target.value)}
            />
          </label>

          <label className="Item">
            Предмет:
            <Input
              // value={object}
              type="text"
              name="objects"
              // onChange={(event) => setObject(event.target.value)}
            />
          </label>

          <label className="res">
            Ответсвенный:
            <Cascader
              // value={responsibleOne}
              name="responsible"
              style={{ width: "100%", color: "black", height: 50 }}
              // options={optionsDep}
              // onChange={(value) => setResponsibleOne(value)}
            />
          </label>

          <label className="sum">
            Сумма:
            <Input
              // value={object}
              type="text"
              name="objects"
              // onChange={(event) => setObject(event.target.value)}
            />
          </label>

          <div className="xD"></div>

          <div className="stageFormNew">
            {initialBlocks.map((block, index) => (
              <div key={index} className="stagesNew">
                <label className="formBlock">
                  Название:
                  <Input
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

                <label className="formBlock">
                  Дата начала этапа:
                  <DatePicker
                    // disabled={true}
                    // value={date}
                    name="dateStartStage"
                    value={dayjs(block.dateStartStage)}
                    onChange={(date) =>
                      handleChangeBlocks(index, "dateStartStage", date)
                    }
                  />
                </label>

                <label className="formBlock">
                  Дата окончания этапа:
                  <DatePicker
                    // disabled={true}
                    // value={date}
                    name="dateEndStage"
                    value={dayjs(block.dateEndStage)}
                    onChange={(date) =>
                      handleChangeBlocks(index, "dateEndStage", date)
                    }
                  />
                </label>

                <label className="formBlock">
                  Сумма:
                  <Input
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

                <label className="formBlock">
                  № Счёта:
                  <Input
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

                <label className="formBlock">
                  Дата счёта:
                  <DatePicker
                    
                    // disabled={true}
                    // value={date}
                    name="dateEndStage"
                    value={dayjs(block.dateEndStage)}
                    onChange={(date) =>
                      handleChangeBlocks(index, "dateEndStage", date)
                    }
                  />
                </label>

                <label className="formBlock">
                  Планируемая дата оплаты:
                  <DatePicker
                    
                    // disabled={true}
                    // value={date}
                    name="dateEndStage"
                    value={dayjs(block.dateEndStage)}
                    onChange={(date) =>
                      handleChangeBlocks(index, "dateEndStage", date)
                    }
                  />
                </label>

                <label className="formBlock">
                  Фактическая дата оплаты:
                  <DatePicker
                    
                    // disabled={true}
                    // value={date}
                    name="dateEndStage"
                    value={dayjs(block.dateEndStage)}
                    onChange={(date) =>
                      handleChangeBlocks(index, "dateEndStage", date)
                    }
                  />
                </label>

                <label className="formBlock">
                  № Акта:
                  <Input
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

                <label className="formBlock">
                  Дата акта:
                  <DatePicker
                    
                    // disabled={true}
                    // value={date}
                    name="dateEndStage"
                    value={dayjs(block.dateEndStage)}
                    onChange={(date) =>
                      handleChangeBlocks(index, "dateEndStage", date)
                    }
                  />
                </label>

                <label className="formBlock">
                  Сумма акта:
                  <Input
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

                <Button
                  type="dashed"
                  icon={<MinusCircleOutlined />}
                  onClick={() => removeBlock(index)}
                  className="removeStageNew"
                >
                  Удалить этап
                </Button>
              </div>
            ))}

            <Button
              type="dashed"
              icon={<PlusOutlined />}
              onClick={addBlock}
              className="addStageNew"
            >
              Добавить новый этап
            </Button>
          </div>
        </Form>
      </div>
    </div>
  );
};

export default FinancePage;
