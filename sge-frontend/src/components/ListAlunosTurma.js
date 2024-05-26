import React, { useState, useRef, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
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
            <DataTable value={alunos} responsiveLayout="scroll">
                <Column field="discente.discente.dadosPessoais.nome" header="ALUNO" />
                <Column field="unidade1" header="UNIDADE 1" />
                <Column field="unidade2" header="UNIDADE 2" />
                <Column field="unidade3" header="UNIDADE 3" />
                <Column body={(rowData) => `${(rowData.presenca / 50 * 100).toFixed(2)}%`} header="FREQUÊNCIA" />
            </DataTable>
        </div>
    );
};

export default ListAlunosTurma;
