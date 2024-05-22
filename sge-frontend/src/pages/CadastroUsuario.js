import React, { useState, useRef } from 'react';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Dropdown } from 'primereact/dropdown';
import { Calendar } from 'primereact/calendar';
import { Checkbox } from 'primereact/checkbox';
import { Toast } from 'primereact/toast';
import '../assets/css/CadastroUsuario.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';


const CadastroUsuario = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [gender, setGender] = useState('');
  const [birthDate, setBirthDate] = useState(null);
  const [subscribe, setSubscribe] = useState(false);

  const toast = useRef(null);

  const showToast = (severity, summary, detail) => {
    toast.current.show({ severity, summary, detail });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    showToast('success', 'Enviado', 'Dados enviados com sucesso!');
  };

  const genderOptions = [
    { label: 'Male', value: 'male' },
    { label: 'Female', value: 'female' },
    { label: 'Other', value: 'other' }
  ];

  return (
    <div className="form-container">
      <Toast ref={toast} />
      <h1>Registration Form</h1>
      <form onSubmit={handleSubmit}>
        <div className="p-field">
          <label htmlFor="name">Name</label>
          <InputText id="name" value={name} onChange={(e) => setName(e.target.value)} />
        </div>
        <div className="p-field">
          <label htmlFor="email">Email</label>
          <InputText id="email" type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div className="p-field">
          <label>Gender</label>
          <Dropdown value={gender} options={genderOptions} onChange={(e) => setGender(e.value)} placeholder="Select a gender" />
        </div>
        <div className="p-field">
          <label htmlFor="birthdate">Birth Date</label>
          <Calendar id="birthdate" value={birthDate} onChange={(e) => setBirthDate(e.value)} />
        </div>
        <div className="p-field-checkbox">
          <Checkbox inputId="subscribe" checked={subscribe} onChange={(e) => setSubscribe(e.checked)} />
          <label htmlFor="subscribe">Subscribe to our newsletter</label>
        </div>
        <div className="p-field">
          <Button type="submit" label="Submit" />
        </div>
      </form>
    </div>
  );
};

export default CadastroUsuario;
