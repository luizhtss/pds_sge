import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import '../assets/css/CadastroUsuario.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const LoginUsuario = () => {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const toast = useRef(null);
    const navigate = useNavigate();

    const domain = 'http://localhost';
    const port = 8080;


    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const loginData = {
            login,
            password
        };

        try {
            const response = await fetch(`${domain}:${port}/api/auth/login` , {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData)
            });

            if (response.ok) {
                showToast('success', 'Success', 'Logado com sucesso!');
            } else {
                const errorData = await response.json();
                showToast('error', 'Error', errorData.message);
            }
        } catch (error) {
            showToast('error', 'Error', 'Ocorreu um erro ao fazer seu login.');
            console.error('Error logging in:', error);
        }
    };

    return (
        <div className="form-container">
            <Toast ref={toast} />
            <h1>Login Form</h1>
            <form onSubmit={handleSubmit}>
                <div className="p-field">
                    <label htmlFor="login">Usuário</label>
                    <InputText id="login" value={login} onChange={(e) => setLogin(e.target.value)} />
                </div>
                <div className="p-field">
                    <label htmlFor="password">Senha</label>
                    <InputText id="password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <div className="p-field">
                    <Button type="submit" label="Login" className="p-button-primary" />
                </div>
            </form>
            <div className="p-field">
                <Button label="Ir para Cadastro" className="p-button-secondary" onClick={() => navigate('/cadastro-usuario')} />
            </div>
        </div>
    );
};

export default LoginUsuario;
