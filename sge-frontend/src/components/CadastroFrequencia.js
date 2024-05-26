import React, { useState, useRef } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import '../assets/css/CadastroUsuario.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const CadastroFrequencia = () => {
    const [presenca, setFrequencia] = useState('');
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

        const Teste = {presenca};
        try {
            const response = await fetch(`${domain}:${port}/api/discente-materia/frequencia/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(Teste)
            });
            if (response.ok) {
                showToast('success', 'Success', 'Frequencia cadastrada com sucesso!');
            } else {
                showToast('error', 'Erro', 'Ocorreu um erro ao cadastrar a frequencia.');
            }
        } catch (error) {
            showToast('error', 'Erro', 'Ocorreu um erro ao cadastrar a frequencia.');
            console.error('Error submitting form:', error);
        }
    };


    return (
        <div className="form-container">
            <Toast ref={toast} />
            <h1>Cadastro de Notas</h1>
            <form onSubmit={handleSubmit}>
                <div className="p-field">
                    <label htmlFor="presenca">Frequencia</label>
                    <InputText
                        id="presenca"
                        name="presenca"
                        type="number"
                        value={presenca}
                        onChange={(e) => {if (!isNaN(e.target.value)) { setFrequencia(e.target.value)}}}
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

export default CadastroFrequencia;
