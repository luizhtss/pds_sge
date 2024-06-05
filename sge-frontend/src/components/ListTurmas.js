import React, { useState, useEffect, useRef } from 'react';
import { useParams, Link } from 'react-router-dom'; // Import Link
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
                const responseTurma = await fetch(`${domain}:${port}/api/turmas/${id}`);
                console.log('Fetching turmas:', responseTurma);
                if (!responseTurma.ok) {
                    throw new Error('Network response was not ok');
                }
                const dataTurma = await responseTurma.json();
                console.log('Turma data:', dataTurma);

                const responseMateria = await fetch(`${domain}:${port}/api/materias/docente/${dataTurma.id}`);
                console.log('Fetching materias for turma:', dataTurma.id, responseMateria);
                if (!responseMateria.ok) {
                    throw new Error('Network response was not ok');
                }
                const dataMateria = await responseMateria.json();
                console.log('Materia data for turma:', dataTurma.id, dataMateria);

                setTurmaData([{ ...dataTurma, materias: [dataMateria] }]);
            } catch (error) {
                console.error('Error fetching turmas:', error);
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

    return (
        <div className="list-turma-container">
            <Toast ref={toast}/>
            <h1>Lista de Turmas</h1>
            <DataTable value={turmaData}>
                <Column
                    body={(rowData) => (
                        <Link to={`/lista-alunos-turma/${rowData.materias[0].id}`}>{`${rowData.nome} - ${rowData.materias.map(materia => materia.nome).join(', ')}`}</Link>
                    )}
                    style={{ fontSize: 'larger' }}
                />
            </DataTable>
        </div>
    );
};

export default ListTurmas;
