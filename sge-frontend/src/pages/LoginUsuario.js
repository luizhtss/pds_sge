import React, { useState } from 'react';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import '../assets/css/LoginUsuario.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const LoginUsuario = () => {
    const [user, setUser] = useState('');
    const [senha, setSenha] = useState('');
    const toast = useRef(null);

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Example code to send login data to the backend API
        const loginData = {
            user,
            senha
        };

        try {
            // Send the login data to the backend API
            const response = await fetch('api/usuarios/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginData)
            });

            // Check if the request was successful
            if (response.ok) {
                // Login successful, handle success
                showToast('success', 'Success', 'Logged in successfully!');
                // Optionally, perform any additional actions after successful login
            } else {
                // Login failed, handle error
                const errorData = await response.json();
                showToast('error', 'Error', errorData.message);
            }
        } catch (error) {
            // Handle unexpected errors
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
                    <Button type="submit" label="Login" />
                </div>
            </form>
            <div className="p-field">
                <Button label="Ir para Cadastro" onClick={() => history.push('/cadastro-usuario')} />
            </div>
        </div>
    );
};

export default LoginUsuario;