import React, { useState, useRef } from 'react';
import { useParams } from 'react-router-dom';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import { Card } from 'primereact/card';
import { ProgressSpinner } from 'primereact/progressspinner';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import '../assets/css/Relatorio.css';

const Relatorio = () => {
    const { id } = useParams();
    const [relatorio, setRelatorio] = useState('');
    const [nome, setNome] = useState('');
    const [matricula, setMatricula] = useState('');
    const [loading, setLoading] = useState(false);
    const toast = useRef(null);

    const handleChange = (event) => {
        setRelatorio(event.target.value);
    };

    const handleSubmit = () => {
        setLoading(true);
        fetch(`http://localhost:8080/api/relatorio/academico/${id}`)
            .then((response) => response.json())
            .then((data) => {
                setRelatorio(data.texto);
                setNome(data.nomeEstudante);
                setMatricula(data.matricula);
                setLoading(false);
            })
            .catch((error) => {
                console.error('Error fetching relatorio data:', error);
                toast.current.show({
                    severity: 'error',
                    summary: 'Error',
                    detail: 'Failed to fetch relatorio data',
                    life: 3000,
                });
                setLoading(false);
            });
    };

    return (
        <div className="relatorio-container">
            <Card title={`Relatório ${nome ? `de ${nome}` : ''} ${matricula ? `| #${matricula}` : ''}`}>
                <div className="relatorio-input">
                    <label htmlFor="relatorio-textarea">Relatório:</label>
                    <textarea
                        id="relatorio-textarea"
                        value={relatorio}
                        onChange={handleChange}
                        maxLength={3000}
                        disabled={true}
                        rows={20}
                    />
                </div>
                <div className="button-container">
                    <Button label="Gerar Relatório" onClick={handleSubmit} disabled={loading} />
                </div>
                {loading && (
                    <div className="loading-message">
                        <ProgressSpinner />
                        <p>Carregando...</p>
                    </div>
                )}
            </Card>
            <Toast ref={toast} />
        </div>
    );
};

export default Relatorio;
