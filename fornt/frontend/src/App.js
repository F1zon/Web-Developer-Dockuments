import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components/Home/Home";
import CreatePage from "./components/Create/Create";

function App() {
  return (
    <div className="App">
        <Routes>
          <Route path="/" element={ <Home /> } />
          <Route path="/create" element={ <CreatePage /> } />
        </Routes>
    </div>
  );
}

export default App;
