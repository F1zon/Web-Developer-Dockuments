import DockList from './components/DockList';

import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <DockList />
      </header>
    </div>
  );
}

export default App;
