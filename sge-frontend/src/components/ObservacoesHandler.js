import React, { useState, useEffect, useRef } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputTextarea } from 'primereact/inputtextarea';
import { Button } from 'primereact/button';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import '../assets/css/ObservacoesHander.css';

const ObservacoesHandler = () => {
    const [observacoes, setObservacoes] = useState([]);
    const [editingRows, setEditingRows] = useState({});
    const [DiscenteNome, setDiscenteNome] = useState('');
    const [novaObservacao, setNovaObservacao] = useState('');
    const toast = useRef(null);
    const navigate = useNavigate();
    const { id, matricula_discente } = useParams();
    const domain = 'http://localhost';
    const port = 8080;

    useEffect(() => {
        const fetchObservacoes = async () => {
            console.log("heyoo")
            try {
                const response = await fetch(`${domain}:${port}/api/observacoes/listar/${matricula_discente}`);
                console.log(response)
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                console.log(data)
                setObservacoes(data);
            } catch (error) {
                showToast('error', 'Error', 'Failed to fetch observacoes.');
                console.log('Error fetching observacoes:', error);
            }
        };

        const fetchDiscenteNome = async () => {
            try {
                const response = await fetch(`${domain}:${port}/api/discentes/matricula/${matricula_discente}`);
                if (response.ok) {
                    const discente = await response.json();
                    setDiscenteNome(discente.dadosPessoais.nome);
                }
            } catch (error) {
                showToast('error', 'Error', 'Failed to fetch student name.');
                console.log('Error fetching student name:', error);
            }
        };

        fetchObservacoes();
        fetchDiscenteNome();
    }, [matricula_discente]);


    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    const onEditorValueChange = (props, value) => {
        let updatedObservacoes = [...observacoes];
        updatedObservacoes[props.rowIndex][props.field] = value;
        setObservacoes(updatedObservacoes);
    };

    const onRowEditChange = (e) => {
        setEditingRows(e.data);
    };

    const onRowEditComplete = async (event) => {
        const { data: rowData } = event;

        try {
            const observacaoData = {
                observacao: rowData.observacao,
                docenteResponsavel: { id: id },
                matriculaDiscente: { id: matricula_discente }
            };

            const response = await fetch(`${domain}:${port}/api/observacoes/${rowData.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(observacaoData),
            });

            if (response.ok) {
                showToast('success', 'Success', 'Observação salva com sucesso!');
                console.log('Observação saved successfully.');
            } else {
                showToast('error', 'Erro', 'Ocorreu um erro ao salvar a observação.');
                console.log('Error response from server:', response);
            }
        } catch (error) {
            showToast('error', 'Erro', 'Ocorreu um erro ao enviar os dados.');
            console.log('Error submitting observacao:', error);
        }
    };

    const deleteObservacao = async (id) => {
        try {
            const response = await fetch(`${domain}:${port}/api/observacoes/${id}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                setObservacoes(observacoes.filter(obs => obs.id !== id));
                showToast('success', 'Success', 'Observação deletada com sucesso!');
                console.log('Observação deleted successfully.');
            } else {
                showToast('error', 'Erro', 'Ocorreu um erro ao deletar a observação.');
                console.log('Error response from server:', response);
            }
        } catch (error) {
            showToast('error', 'Erro', 'Ocorreu um erro ao enviar os dados.');
            console.log('Error deleting observation:', error);
        }
    };

    const inputEditor = (props) => {
        return (
            <InputTextarea
                value={props.rowData.observacao}
                onChange={(e) => onEditorValueChange(props, e.target.value)}
                rows={5}
                autoResize
            />
        );
    };

    const addNewRow = async () => {
        try {
            const response = await fetch(`${domain}:${port}/api/observacoes/criar`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    observacao: novaObservacao,
                    docenteResponsavel: { id: id },
                    matriculaDiscente: { id: matricula_discente }
                }),
            });

            if (response.ok) {
                const newObservacao = await response.json();
                setObservacoes([...observacoes, newObservacao]);
                showToast('success', 'Success', 'Nova observação adicionada com sucesso!');
                console.log('New observation added successfully.');
            } else {
                showToast('error', 'Erro', 'Ocorreu um erro ao adicionar a observação.');
                console.log('Error response from server:', response);
            }
        } catch (error) {
            showToast('error', 'Erro', 'Ocorreu um erro ao enviar os dados.');
            console.log('Error adding new observation:', error);
        }
    };

    return (
        <div className="form-container">
            <Toast ref={toast} />
            <h3>{DiscenteNome}</h3>
            <form>
                <DataTable value={observacoes} editMode="row" dataKey="id"
                           editingRows={editingRows} onRowEditChange={onRowEditChange}
                           onRowEditComplete={onRowEditComplete}>
                    <Column field="observacao" header="Observações" editor={inputEditor}/>
                    <Column rowEditor headerStyle={{width: '8rem'}} bodyStyle={{textAlign: 'center'}}/>
                    <Column
                        body={(rowData) => (
                            <Button
                                icon="pi pi-trash"
                                className="p-button-rounded p-button-danger"
                                onClick={() => deleteObservacao(rowData.id)}
                            />
                        )}
                        headerStyle={{width: '3rem'}}
                        bodyStyle={{textAlign: 'center'}}
                    />
                </DataTable>
                <div className="p-inputgroup">
                    <InputTextarea
                        value={novaObservacao}
                        onChange={(e) => setNovaObservacao(e.target.value)}
                        rows={5}
                        autoResize
                    />
                    <Button
                        type="button"
                        label="Adicionar Observação"
                        icon="pi pi-plus"
                        onClick={addNewRow}
                        className="p-button-success"
                        style={{ marginLeft: '10px' }}
                    />
                </div>
            </form>
            <div className="p-field button-container">
                <Button type="button" label="Voltar" className="p-button-secondary"
                        onClick={() => navigate(`/home-docente/${id}`)}/>
            </div>
        </div>
    );
};

export default ObservacoesHandler;
