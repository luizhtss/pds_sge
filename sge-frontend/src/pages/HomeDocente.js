import React from 'react';
import ListTurmas from "../components/ListTurmas";
import Perfil from "../components/Perfil";
import '../assets/css/HomeDocente.css';

const HomeDocente = () => {
    return (
        <div className="home-docente-container">
            <div className="turma-container">
                <ListTurmas />
            </div>
            <div className="perfil-container">
                <Perfil />
            </div>
        </div>
    );
};

export default HomeDocente;
