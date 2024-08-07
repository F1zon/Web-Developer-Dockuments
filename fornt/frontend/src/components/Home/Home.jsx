import Menu from './Menu/Menu'
import Header from './Headers/Headers'
import Main from './MainMenu/Main'
import './Home.css'

function Home() {
    return (
        <div className="container">
          <Menu />
          <Header />
          <Main />
        </div>
      );
}

export default Home;