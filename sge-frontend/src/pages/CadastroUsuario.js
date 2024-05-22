import React, { useState } from 'react';
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

      // Check if the request was successful
      if (response.ok) {
        // Data was successfully saved in the database
        showToast('success', 'Enviado', 'Dados enviados com sucesso!');
        // Optionally, reset the form fields
        setNome('');
        setEmail('');
        setSenha('');
        setRole('');
      } else {
        // Handle errors if the request was not successful
        const errorData = await response.json();
        showToast('error', 'Erro', errorData.message);
      }
    } catch (error) {
      // Handle any unexpected errors
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
            <Button type="submit" label="Submit" />
          </div>
        </form>
        <div className="p-field">
          <a href="/">Voltar para o Login</a>
        </div>
      </div>
  );
};

export default CadastroUsuario;
