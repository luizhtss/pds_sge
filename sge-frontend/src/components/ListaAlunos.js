import React, { useEffect, useState } from 'react';

const ListaAlunos = () => {
    const [alunos, setAlunos] = useState([]);

    useEffect(() => {
        const fetchAlunos = async () => {
            const response = await fetch('http://localhost:8080/api/alunos');
            const data = await response.json();
            setAlunos(data);
        };

        fetchAlunos();
    }, []);

    return (
        <div>
            <h1>Lista de Alunos</h1>
            <ul>
                {alunos.map(aluno => (
                    <li key={aluno.id}>{aluno.nome} - Nota: {aluno.nota}</li>
                ))}
            </ul>
        </div>
    );
};

export default ListaAlunos;

