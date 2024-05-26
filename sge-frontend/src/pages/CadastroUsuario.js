import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import '../assets/css/CadastroUsuario.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const CadastroUsuario = () => {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [matricula, setMatricula] = useState('');
  const toast = useRef(null);
  const navigate = useNavigate();

  const domain = 'http://localhost';
  const port = 8080;

  const showToast = (severity, summary, detail) => {
    toast.current.show({ severity, summary, detail });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const cadastroData = {
      nome,
      email,
      password,
      matricula
    }

    try {
       const response = await fetch(`${domain}:${port}/api/auth/register`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(cadastroData)
      });
      if (response.ok) {
        showToast('success', 'Success', 'Usuário cadastrado com sucesso!');
      } else {
        showToast('error', 'Error', 'Ocorreu um erro ao enviar os dados.');
      }
    } catch (error) {
      showToast('error', 'Erro', 'Ocorreu um erro ao enviar os dados.');
      console.error('Error submitting form:', error);
    }
  };

  return (
      <div className="form-container">
        <Toast ref={toast} />
        <h1>Cadastro</h1>
        <form onSubmit={handleSubmit}>
          <div className="p-field">
            <label htmlFor="nome">Nome Completo</label>
            <InputText id="nome" value={nome} onChange={(e) => setNome(e.target.value)}/>
          </div>
          <div className="p-field">
            <label htmlFor="email">Matrícula</label>
            <InputText id="email" value={matricula} onChange={(e) => setMatricula(e.target.value)}/>
          </div>
          <div className="p-field">
            <label htmlFor="email">Email</label>
            <InputText id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
          </div>
          <div className="p-field">
            <label htmlFor="password">Senha</label>
            <Password id="password" value={password} onChange={(e) => setPassword(e.target.value)} toggleMask/>
          </div>
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

export default CadastroUsuario;
