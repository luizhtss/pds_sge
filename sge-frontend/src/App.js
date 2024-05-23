import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginUsuario from './pages/LoginUsuario';
import CadastroUsuario from './pages/CadastroUsuario';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginUsuario />} />
                <Route path="/cadastro-usuario" element={<CadastroUsuario />} />
            </Routes>
        </Router>
    );
}

export default App;
