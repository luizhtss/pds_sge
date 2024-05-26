import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import '../assets/css/CadastroUsuario.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import { FormControl, FormLabel, FormControlLabel,RadioGroup,Radio } from '@mui/material';

const CadastroDadosPessoais = () => {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [matricula, setMatricula] = useState('');
    const [role, setRole] = useState('');
    const toast = useRef(null);
    const navigate = useNavigate();

    let domain = 'http://localhost';
    let port = 8080;

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const pessoaData = {
            nome,
            email,
            matricula,
            role
        }

        try {
            const response = await fetch(`${domain}:${port}/api/pessoas/criar`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(pessoaData)
            });
            if (response.ok) {
                showToast('success', 'Success', 'Dados Pessoais cadastrados com sucesso!');
            } else {
                showToast('error', 'Erro', 'Ocorreu um erro ao enviar os dados.');
            }
        } catch (error) {
            showToast('error', 'Erro', 'Ocorreu um erro ao enviar os dados.');
            console.error('Error submitting form:', error);
        }
    };

    return (
        <div className="form-container">
            <Toast ref={toast} />
            <h1>Cadastro de Dados Pessoais</h1>
            <form onSubmit={handleSubmit}>
                <div className="p-field">
                    <label htmlFor="nome">Nome Completo</label>
                    <InputText id="nome" value={nome} onChange={(e) => setNome(e.target.value)}/>
                </div>
                <div className="p-field">
                    <label htmlFor="email">Matrícula</label>
                    <InputText id="email" type="email" value={matricula}
                               onChange={(e) => setMatricula(e.target.value)}/>
                </div>
                <div className="p-field">
                    <label htmlFor="email">Email</label>
                    <InputText id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                </div>
                <FormControl>
                    <FormLabel id="demo-controlled-radio-buttons-group">Cargo</FormLabel>
                    <RadioGroup
                        aria-labelledby="demo-controlled-radio-buttons-group"
                        name="controlled-radio-buttons-group"
                        value={role}
                        onChange={(e) => setRole(e.target.value)}
                    >
                        <FormControlLabel value="female" control={<Radio />} label="Docente" />
                        <FormControlLabel value="male" control={<Radio />} label="Discente" />
                    </RadioGroup>
                </FormControl>
                <div className="p-field">
                    <Button type="submit" label="Cadastrar" className="p-button-primary"/>
                </div>
            </form>
            <div className="p-field">
                <Button label="Voltar para o Login" className="p-button-secondary" onClick={() => navigate('/')}/>
            </div>
        </div>
    );
};

export default CadastroDadosPessoais;
