import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginUsuario from './pages/LoginUsuario';
import CadastroUsuario from './pages/CadastroUsuario';
import CadastroNotas from "./pages/CadastroNotas";
import CadastroDadosPessoais from "./pages/CadastroDadosPessoais";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginUsuario />} />
                <Route path="/cadastro-usuario" element={<CadastroUsuario />} />
                <Route path="/cadastro-pessoa" element={<CadastroDadosPessoais />} />
                <Route path="/cadastro-notas" element={<CadastroNotas />} />
            </Routes>
        </Router>
    );
}

export default App;
