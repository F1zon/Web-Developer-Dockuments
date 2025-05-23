import React, { useState } from "react";
import Menu from "./Menu/Menu";
import Header from "./Headers/Headers";
import Main from "./MainMenu/Main";
import "./Home.css";

function Home() {
  const [object, setObject] = useState();
  const [filters, setFilters] = useState({});

  const applyFilters = (filtersData) => {
    console.log("Фильтры применены", filtersData);
    setFilters(filtersData);
  };

  return (
    <div className="container">
      <Menu object={object} setObject={setObject} onApplyFilters={applyFilters} />
      <Header />
      <Main filters={filters} />
    </div>
  );
}

export default Home;
