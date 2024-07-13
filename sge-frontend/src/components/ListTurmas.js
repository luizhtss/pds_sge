import React, { useState, useEffect, useRef } from 'react';
import { useParams, Link } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import '../assets/css/ListTurmas.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const ListTurmas = () => {
    const [turmaData, setTurmaData] = useState([]);
    const { id } = useParams();
    const [loading, setLoading] = useState(true);
    const toast = useRef(null);

    const domain = 'http://localhost';
    const port = 8080;

    const showToast = (severity, summary, detail) => {
        if (toast.current) {
            toast.current.show({ severity, summary, detail });
        }
    };

    useEffect(() => {
        const fetchTurmaData = async () => {
            try {
                const response = await fetch(`${domain}:${port}/api/turmas/docente/${id}`);
                if (!response.ok) {
                    throw new Error('Failed to fetch turma');
                }
                const dataTurma = await response.json(); // Fixed: Added await here
                console.log(dataTurma);
                setTurmaData(dataTurma);
            } catch (error) {
                console.error('Error fetching data:', error);
                showToast('error', 'Error', 'Não foi possível listar os dados.');
            } finally {
                setLoading(false);
            }
        };

        fetchTurmaData();
    }, [id]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (!turmaData.length) {
        return <div>No data found</div>;
    }

    const rows = turmaData.flatMap((turma) => {
        return turma.materias.map((materia) => ({
            id: `${turma.id}-${materia.id}`,
            turma: turma.nome,
            materia: materia.nome,
            url: `/lista-alunos-turma/${materia.id}` // Replace with actual URL
        }));
    });

    const turmaBodyTemplate = (rowData) => {
        return <span>{rowData.turma}</span>;
    };

    const materiaBodyTemplate = (rowData) => {
        return (
            <a href={rowData.url} target="_blank" rel="noopener noreferrer">
                {rowData.materia}
            </a>
        );
    };

    return (
        <div>
            <DataTable value={rows}>
                <Column field="turma" header="Turma" body={turmaBodyTemplate} />
                <Column field="materia" header="Matéria" body={materiaBodyTemplate} />
            </DataTable>
        </div>
    );
};

export default ListTurmas;
