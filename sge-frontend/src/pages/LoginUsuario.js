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
    const [user, setUser] = useState('');
    const [senha, setSenha] = useState('');
    const toast = useRef(null);
    const navigate = useNavigate();

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const loginData = {
            user,
            senha
        };

        try {
            const response = await fetch('api/usuarios/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData)
            });

            if (response.ok) {
                showToast('success', 'Success', 'Logged in successfully!');
            } else {
                const errorData = await response.json();
                showToast('error', 'Error', errorData.message);
            }
        } catch (error) {
            showToast('error', 'Error', 'An error occurred while logging in.');
            console.error('Error logging in:', error);
        }
    };

    return (
        <div className="form-container">
            <Toast ref={toast} />
            <h1>Login Form</h1>
            <form onSubmit={handleSubmit}>
                <div className="p-field">
                    <label htmlFor="user">Usuário</label>
                    <InputText id="user" value={user} onChange={(e) => setUser(e.target.value)} />
                </div>
                <div className="p-field">
                    <label htmlFor="senha">Senha</label>
                    <InputText id="senha" type="password" value={senha} onChange={(e) => setSenha(e.target.value)} />
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
