import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import LoginUsuario from './pages/LoginUsuario';
import CadastroUsuario from './pages/CadastroUsuario';

function App() {
    return (
        <Router>
            <Switch>
                <Route path="/" exact component={LoginUsuario} />
                <Route path="/cadastro-usuario" component={CadastroUsuario} />
            </Switch>
        </Router>
    );
}

export default App;
