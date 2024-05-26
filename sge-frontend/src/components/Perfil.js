import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Panel } from 'primereact/panel';
import '../assets/css/Perfil.css';
import logo from '../assets/images/logo.svg';

const Perfil = () => {
    const [userData, setUserData] = useState(null);
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
            } catch (error) {
                console.error('Error fetching user data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchUserData();
    }, []);

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
                        <p className="profile-label">Matricula: 1{/*userData.matricula*/}</p>
                    </div>
                </div>
            </Panel>
        </div>
    );
};

export default Perfil;
