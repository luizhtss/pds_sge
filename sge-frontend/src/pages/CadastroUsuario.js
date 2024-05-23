import React, { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';
import { RadioButton } from 'primereact/radiobutton';
import { Toast } from 'primereact/toast';
import '../assets/css/CadastroUsuario.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const CadastroUsuario = () => {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [role, setRole] = useState('');
  const toast = useRef(null);
  const navigate = useNavigate();

  const showToast = (severity, summary, detail) => {
    toast.current.show({ severity, summary, detail });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const userData = {
      nome,
      email,
      senha,
      role
    };

    try {
      const response = await fetch('api/usuarios/criar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
      });

      if (response.ok) {
        showToast('success', 'Enviado', 'Dados enviados com sucesso!');
        setNome('');
        setEmail('');
        setSenha('');
        setRole('');
      } else {
        const errorData = await response.json();
        showToast('error', 'Erro', errorData.message);
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
            <label htmlFor="nome">Nome</label>
            <InputText id="nome" value={nome} onChange={(e) => setNome(e.target.value)} />
          </div>
          <div className="p-field">
            <label htmlFor="email">Email</label>
            <InputText id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
          </div>
          <div className="p-field">
            <label htmlFor="senha">Senha</label>
            <Password id="senha" value={senha} onChange={(e) => setSenha(e.target.value)} toggleMask />
          </div>
          <div className="p-field">
            <label>Você é:</label>
            <div className="p-formgrid p-grid">
              <div className="p-col-6">
                <RadioButton inputId="docente" name="role" value="docente" onChange={(e) => setRole(e.value)} checked={role === 'docente'} />
                <label htmlFor="docente">Docente</label>
              </div>
              <div className="p-col-6">
                <RadioButton inputId="discente" name="role" value="discente" onChange={(e) => setRole(e.value)} checked={role === 'discente'} />
                <label htmlFor="discente">Discente</label>
              </div>
            </div>
          </div>
          <div className="p-field">
            <Button type="submit" label="Submit" className="p-button-primary" />
          </div>
        </form>
        <div className="p-field">
          <Button label="Voltar para o Login" className="p-button-secondary" onClick={() => navigate('/')} />
        </div>
      </div>
  );
};

export default CadastroUsuario;
