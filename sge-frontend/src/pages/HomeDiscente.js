import React from 'react';
import Perfil from "../components/Perfil";
import ListMaterias from "../components/ListMaterias";
import '../assets/css/HomeDocente.css';

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
