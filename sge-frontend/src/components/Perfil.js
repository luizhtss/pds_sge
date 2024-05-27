import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Panel } from 'primereact/panel';
import '../assets/css/Perfil.css';
import logo from '../assets/images/logo.svg';

const Perfil = () => {
    const [userData, setUserData] = useState(null);
    const [matricula, setMatricula] = useState('');
    const [loading, setLoading] = useState(true);
    const { id } = useParams();
    const domain = 'http://localhost';
    const port = 8080;

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await fetch(`${domain}:${port}/api/pessoas/${id}`);
                if (!response.ok) {
                    throw new Error('Failed to fetch user data');
                }
                const userData = await response.json();
                setUserData(userData);

                const matriculaResponse = await fetch(`${domain}:${port}/api/matricula/${id}`);
                if (!matriculaResponse.ok) {
                    throw new Error('Failed to fetch matricula data');
                }
                const matriculaData = await matriculaResponse.json();
                setMatricula(matriculaData.matricula); // assuming the response has a field 'matricula'
            } catch (error) {
                console.error('Error fetching data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchUserData();
    }, [id]);

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div className="profile-page">
            <Panel className="profile-panel" header="Docente" style={{ padding: '20px' }}>
                <div style={{ display: 'flex', alignItems: 'center', marginBottom: '20px' }}>
                    <img src={logo} alt="Logo" style={{ width: '100px', height: 'auto', marginRight: '20px' }} />
                    <div>
                        <p className="profile-label">Nome: {userData.nome}</p>
                        <p className="profile-label">Email: {userData.email}</p>
                        <p className="profile-label">Matricula: {matricula}</p>
                    </div>
                </div>
            </Panel>
        </div>
    );
};

export default Perfil;
