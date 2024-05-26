import React, { useState, useRef, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import '../assets/css/ListaAlunos.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const ListAlunosTurma = () => {
    const [alunos, setAlunos] = useState([]);
    const { id } = useParams();
    const toast = useRef(null);
    const navigate = useNavigate();

    const domain = 'http://localhost';
    const port = 8080;

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    useEffect(() => {
        const fetchAlunos = async () => {
            try {
                const response = await fetch(`${domain}:${port}/api/discente-materia/materia/${id}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                setAlunos(data);
            } catch (error) {
                showToast('error', 'Error', 'Não foi possível listar os alunos.');
            }
        };
        fetchAlunos();
    }, [id]);

    return (
        <div className="list-alunos-container">
            <Toast ref={toast} />
            <h1>Lista de Alunos</h1>
            <table className="table">
                <thead>
                <tr>
                    <th>ALUNO</th>
                    <th>UNIDADE 1</th>
                    <th>UNIDADE 2</th>
                    <th>UNIDADE 3</th>
                    <th>FREQUÊNCIA</th>
                </tr>
                </thead>
                <tbody>
                {alunos.map(dis_mat => (
                    <tr key={dis_mat.id}>
                        <td>{dis_mat.discente.discente.dadosPessoais.nome}</td>
                        <td>{dis_mat.unidade1}</td>
                        <td>{dis_mat.unidade2}</td>
                        <td>{dis_mat.unidade3}</td>
                        <td>{(dis_mat.presenca)/50*100}%</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListAlunosTurma;
