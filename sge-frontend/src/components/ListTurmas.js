import React, { useState, useRef, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import '../assets/css/ListaAlunos.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const ListMaterias = () => {
    const [materias, setMaterias] = useState([]);
    const { id } = useParams();
    const toast = useRef(null);

    const domain = 'http://localhost';
    const port = 8080;

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`${domain}:${port}/api/discente-materia/discente/${id}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                console.log(data);

                setMaterias(data);
            } catch (error) {
                showToast('error', 'Error', 'Não foi possível listar os alunos.');
            }
        };
        fetchData();
    }, [id]);

    return (
        <div className="list-alunos-container">
            <Toast ref={toast} />
            <h1>Lista de Materias</h1>
            <DataTable value={materias}>
                <Column field="materia.nome" header="MATÉRIA" />
                <Column field="materia.descricao" header="DESCRIÇÃO" />
            </DataTable>
        </div>
    );
};

export default ListMaterias;
