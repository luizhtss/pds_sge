import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginUsuario from './components/LoginUsuario';
import CadastroUsuario from './components/CadastroUsuario';
import CadastroNotas from "./components/CadastroNotas";
import CadastroDadosPessoais from "./components/CadastroDadosPessoais";
import CadastroFrequencia from "./components/CadastroFrequencia";
import ListAlunosTurma from "./components/ListAlunosTurma";
import ListMaterias from "./components/ListMaterias";
import ListTurmas from "./components/ListTurmas";
import HomeDocente from "./pages/HomeDocente";
import Perfil from "./components/Perfil";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginUsuario />} />
                <Route path="/cadastro-usuario" element={<CadastroUsuario />} />
                <Route path="/cadastro-pessoa" element={<CadastroDadosPessoais />} />
                <Route path="/cadastro-notas/:id" element={<CadastroNotas />} />
                <Route path="/cadastro-frequencia/:id" element={<CadastroFrequencia />} />
                <Route path="/lista-alunos-turma/:id" element={<ListAlunosTurma />} />
                <Route path="/lista-materias/:matricula" element={<ListMaterias/>} />
                <Route path="/lista-turmas/:id" element={<ListTurmas />} />
                <Route path="/home-docente/:id" element={<HomeDocente />} />
                <Route path="/perfil/:id" element={<Perfil />} />
            </Routes>
        </Router>
    );
}

export default App;
