import Menu from "./components/Menu/Menu";
import Header from "./components/Headers/Headers";
import Main from "./components/MainMenu/Main";
import './App.css'

function App() {
  return (
    <div className="container">
      <Menu />
      <Header />
      <Main />
    </div>
  );
}

export default App;
