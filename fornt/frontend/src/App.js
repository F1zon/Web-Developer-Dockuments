import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components/Home/Home";
import CreatePage from "./components/Create/Create";
import EditingPage from "./components/Editing/EditingPage"
import { DatePicker } from 'antd';

function App() {
  return (
    <div className="App">
        <Routes>
          <Route path="/" element={ <Home /> } />
          <Route path="/create" element={ <CreatePage /> } />
          <Route path="/editing" element={ <EditingPage /> }/>
        </Routes>
    </div>
  );
}

export default App;
