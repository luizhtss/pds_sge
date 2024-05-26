import React from 'react';
import ListTurmas from "../components/ListTurmas";
import Perfil from "../components/Perfil";
import '../assets/css/HomeDocente.css';
import ListMaterias from "../components/ListMaterias";

const HomeDiscente = () => {
    return (
        <div className="home-docente-container">
            <div className="turma-container">
                <ListMaterias />
            </div>
            <div className="perfil-container">
                <Perfil />
            </div>
        </div>
    );
};

export default HomeDiscente;
