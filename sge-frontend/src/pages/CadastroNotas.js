import React, { useState, useRef } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import '../assets/css/CadastroUsuario.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const CadastroNotas = () => {
    const [nota1, setNota1] = useState('');
    const [nota2, setNota2] = useState('');
    const [nota3, setNota3] = useState('');
    const { id } = useParams();
    const toast = useRef(null);
    const navigate = useNavigate();

    const domain = 'http://localhost';
    const port = 8080;

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const notasData = {
            nota1,
            nota2,
            nota3
        }

        try {
            const response2 = await fetch(`${domain}:${port}/api/notas/${id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(notasData)
            });
        } catch (error) {
            showToast('error', 'Erro', 'Ocorreu um erro ao enviar os dados.');
            console.error('Error submitting form:', error);
        }
    };

    return (
        <div className="form-container">
            <Toast ref={toast} />
            <h1>Cadastro de Notas</h1>
            <form onSubmit={handleSubmit}>
                <div className="p-field">
                    <label htmlFor="nota1">Unidade 1</label>
                    <InputText id="nota1" type="number" value={nota1} onChange={(e) => setNota1(e.target.value)}/>
                </div>
                <div className="p-field">
                    <label htmlFor="nota2">Unidade 2</label>
                    <InputText id="nota2" type="number" value={nota2} onChange={(e) => setNota2(e.target.value)}/>
                </div>
                <div className="p-field">
                    <label htmlFor="nota3">Unidade 3</label>
                    <InputText id="nota3" type="number" value={nota3} onChange={(e) => setNota3(e.target.value)}/>
                </div>
            </form>
            <div className="p-field">
                <Button label="Voltar para o Login" className="p-button-secondary" onClick={() => navigate('/')}/>
            </div>
        </div>
    );
};

export default CadastroNotas;
