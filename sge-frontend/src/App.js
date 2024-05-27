import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginUsuario from './components/LoginUsuario';
import CadastroUsuario from './components/CadastroUsuario';
import CadastroDadosPessoais from "./components/CadastroDadosPessoais";
import ListAlunosTurma from "./components/ListAlunosTurma";
import ListMaterias from "./components/ListMaterias";
import ListTurmas from "./components/ListTurmas";
import HomeDocente from "./pages/HomeDocente";
import HomeDiscente from "./pages/HomeDiscente";
import Perfil from "./components/Perfil";
import Relatorio from "./pages/Relatorio";
import ObservacoesHandler from "./components/ObservacoesHandler";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginUsuario />} />
                <Route path="/cadastro-usuario" element={<CadastroUsuario />} />
                <Route path="/cadastro-pessoa" element={<CadastroDadosPessoais />} />
                <Route path="/lista-alunos-turma/:id" element={<ListAlunosTurma />} />
                <Route path="/lista-materias/:matricula" element={<ListMaterias/>} />
                <Route path="/lista-turmas/:id" element={<ListTurmas />} />
                <Route path="/home-docente/:id" element={<HomeDocente />} />
                <Route path="/home-discente/:id" element={<HomeDiscente />} />
                <Route path="/perfil/:id" element={<Perfil />} />
                <Route path="/relatorio/:id" element={<Relatorio />} />
                <Route path="/observacoes/:id/:matricula_discente" element={<ObservacoesHandler />} />
            </Routes>
        </Router>
    );
}

export default App;
