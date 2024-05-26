import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginUsuario from './pages/LoginUsuario';
import CadastroUsuario from './pages/CadastroUsuario';
import CadastroNotas from "./pages/CadastroNotas";
import CadastroDadosPessoais from "./pages/CadastroDadosPessoais";
import CadastroFrequencia from "./pages/CadastroFrequencia";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginUsuario />} />
                <Route path="/cadastro-usuario" element={<CadastroUsuario />} />
                <Route path="/cadastro-pessoa" element={<CadastroDadosPessoais />} />
                <Route path="/cadastro-notas/:id" element={<CadastroNotas />} />
                <Route path="/cadastro-frequencia/:id" element={<CadastroFrequencia />} />
            </Routes>
        </Router>
    );
}

export default App;
