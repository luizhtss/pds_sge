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
    const [notas, setNotas] = useState({ unidade1: '', unidade2: '', unidade3: '' });
    const { id } = useParams();
    const toast = useRef(null);
    const navigate = useNavigate();

    const domain = 'http://localhost';
    const port = 8080;

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        // Allow only numbers
        if (!isNaN(value)) {
            setNotas((prevNotas) => ({
                ...prevNotas,
                [name]: value
            }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(`${domain}:${port}/api/discente-materia/notas/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(notas)
            });
            if (response.ok) {
                showToast('success', 'Success', 'Notas cadastradas com sucesso!');
            } else {
                showToast('error', 'Error', 'Ocorreu um erro ao cadastrar as notas');
            }
        } catch (error) {
            showToast('error', 'Erro', 'Ocorreu um erro ao cadastrar as notas.');
            console.error('Error submitting form:', error);
        }
    };


    return (
        <div className="form-container">
            <Toast ref={toast} />
            <h1>Cadastro de Notas</h1>
            <form onSubmit={handleSubmit}>
                <div className="p-field">
                    <label htmlFor="unidade1">Unidade 1</label>
                    <InputText
                        id="unidade1"
                        name="unidade1"
                        type="number"
                        value={notas.unidade1}
                        onChange={handleChange}
                    />
                </div>
                <div className="p-field">
                    <label htmlFor="unidade2">Unidade 2</label>
                    <InputText
                        id="unidade2"
                        name="unidade2"
                        type="number"
                        value={notas.unidade2}
                        onChange={handleChange}
                    />
                </div>
                <div className="p-field">
                    <label htmlFor="unidade3">Unidade 3</label>
                    <InputText
                        id="unidade3"
                        name="unidade3"
                        type="number"
                        value={notas.unidade3}
                        onChange={handleChange}
                    />
                </div>
                <div className="p-field">
                    <Button label="Enviar" type="submit" className="p-button-primary" />
                </div>
            </form>
            <div className="p-field">
                <Button label="Voltar para o Login" className="p-button-secondary" onClick={() => navigate('/')} />
            </div>
        </div>
    );
};

export default CadastroNotas;
