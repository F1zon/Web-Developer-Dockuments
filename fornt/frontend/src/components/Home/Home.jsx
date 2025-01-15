import React, { useState } from "react";
import Menu from "./Menu/Menu";
import Header from "./Headers/Headers";
import Main from "./MainMenu/Main";
import "./Home.css";

function Home() {
  const [object, setObject] = useState();

  return (
    <div className="container">
      <Menu object={object} setObject={setObject} />
      <Header />
      <Main object={object} />
    </div>
  );
}

export default Home;
