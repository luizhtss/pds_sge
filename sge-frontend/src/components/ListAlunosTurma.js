import React, { useState, useRef, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputNumber } from 'primereact/inputnumber';
import { Button } from 'primereact/button';
import '../assets/css/ListaAlunos.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const ListAlunosTurma = () => {
    const [alunos, setAlunos] = useState([]);
    const [editingRows, setEditingRows] = useState({});
    const { id } = useParams();
    const toast = useRef(null);

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

    const onRowEditChange = (e) => {
        setEditingRows(e.data);
    };

    const onRowEditComplete = async (e) => {
        const { newData, index } = e;
        try {
            const response = await fetch(`${domain}:${port}/api/discente-materia/notas/${newData.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    unidade1: newData.unidade1,
                    unidade2: newData.unidade2,
                    unidade3: newData.unidade3,
                }),
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const updatedAlunos = [...alunos];
            updatedAlunos[index] = newData;
            setAlunos(updatedAlunos);
            showToast('success', 'Success', 'Notas updated successfully.');
        } catch (error) {
            showToast('error', 'Error', 'Não foi possível atualizar as notas.');
        }
    };

    const inputNumberEditor = (options) => {
        return (
            <InputNumber
                value={options.value}
                onValueChange={(e) => options.editorCallback(e.value)}
                mode="decimal"
                minFractionDigits={1}
                maxFractionDigits={1}
                min={0}
                max={10}
            />
        );
    };

    const incrementPresenca = async (rowData) => {
        const updatedPresenca = rowData.presenca + 1;
        try {
            const response = await fetch(`${domain}:${port}/api/discente-materia/frequencia/${rowData.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    presenca: updatedPresenca,
                }),
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const updatedAlunos = alunos.map(aluno =>
                aluno.id === rowData.id ? { ...aluno, presenca: updatedPresenca } : aluno
            );
            setAlunos(updatedAlunos);
            showToast('success', 'Success', 'Presença updated successfully.');
        } catch (error) {
            showToast('error', 'Error', 'Não foi possível atualizar a presença.');
        }
    };

    const presencaTemplate = (rowData) => {
        return (
            <div>
                {`${(rowData.presenca / 50 * 100).toFixed(2)}%`}
                <Button icon="pi pi-plus" className="p-button-rounded p-button-success p-ml-2" style={{ marginLeft: '10px' }} onClick={() => incrementPresenca(rowData)} />
            </div>
        );
    };

    return (
        <div className="list-alunos-container">
            <Toast ref={toast} />
            <h1>Lista de Alunos</h1>
            <DataTable value={alunos} responsiveLayout="scroll" editMode="row" dataKey="id"
                       editingRows={editingRows} onRowEditChange={onRowEditChange} onRowEditComplete={onRowEditComplete}>
                <Column field="discente.discente.dadosPessoais.nome" header="ALUNO" />
                <Column field="unidade1" header="UNIDADE 1" editor={inputNumberEditor} />
                <Column field="unidade2" header="UNIDADE 2" editor={inputNumberEditor} />
                <Column field="unidade3" header="UNIDADE 3" editor={inputNumberEditor} />
                <Column body={presencaTemplate} header="FREQUÊNCIA" />
                <Column rowEditor headerStyle={{ width: '7rem' }} bodyStyle={{ textAlign: 'center' }} />
            </DataTable>
        </div>
    );
};

export default ListAlunosTurma;
