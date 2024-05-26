import React, { useState, useRef, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import '../assets/css/ListaAlunos.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const ListMaterias = () => {
    const [materias, setMaterias] = useState([]);
    const { matricula } = useParams();
    const toast = useRef(null);
    const navigate = useNavigate();

    const domain = 'http://localhost';
    const port = 8080;

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`${domain}:${port}/api/discente-materia/discente/${matricula}`);


                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                setMaterias(data);
            } catch (error) {
                showToast('error', 'Error', 'Não foi possível listar os alunos.');
            }
        };
        fetchData();
    }, [matricula]);

    return (
        <div className="list-alunos-container">
            <Toast ref={toast} />
            <h1>Lista de Materias</h1>
            <table className="table">
                <thead>
                <tr>
                    <th>MATÉRIA</th>
                    <th>DESCRIÇÃO</th>
                </tr>
                </thead>
                <tbody>
                {materias.map(dis_mat => (
                    <tr key={dis_mat.id}>
                        <td>{dis_mat.materia.nome}</td>
                        <td>{dis_mat.materia.descricao}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListMaterias;
