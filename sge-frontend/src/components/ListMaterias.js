import React, { useState, useRef, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import '../assets/css/ListaAlunos.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const ListMaterias = () => {
    const [materias, setMaterias] = useState([]);
    const [matricula, setMatricula] = useState('');
    const [loading, setLoading] = useState(true);
    const { id } = useParams();
    const [timers, setTimers] = useState({});
    const [activeVideo, setActiveVideo] = useState(null);
    const [videoDuration, setVideoDuration] = useState(0);
    const toast = useRef(null);
    const domain = 'http://localhost';
    const port = 8080;
    const videoRef = useRef(null);

    const showToast = (severity, summary, detail) => {
        toast.current.show({ severity, summary, detail });
    };

    const loadYouTubeAPI = () => {
        const script = document.createElement('script');
        script.src = "https://www.youtube.com/iframe_api";
        script.async = true;
        script.onload = () => {
            console.log("YouTube API script loaded");
        };
        document.body.appendChild(script);
    };

    const fetchMatricula = async () => {
        try {
            console.log(id);
            const matriculaResponse = await fetch(`${domain}:${port}/api/matricula/${id}`);
            if (!matriculaResponse.ok) {
                throw new Error('Failed to fetch matricula data');
            }
            const matriculaData = await matriculaResponse.json();
            setMatricula(matriculaData.matricula);
        } catch (error) {
            console.error('Error fetching data:', error);
            showToast('error', 'Error', 'Não foi possível obter os dados do usuário.');
        } finally {
            setLoading(false);
        }
    };

    const handleWatchClick = (materiaId, videoLink) => {
        if (timers[materiaId]?.active) {
            const elapsedTime = Math.floor((Date.now() - timers[materiaId].startTime) / 1000);
            const percentageWatched = (elapsedTime / videoDuration) * 100;
            setTimers((prevTimers) => ({
                ...prevTimers,
                [materiaId]: { startTime: Date.now(), active: false, percentageWatched }
            }));
            setActiveVideo(null);
            registrarFrenquencia(materiaId, elapsedTime, percentageWatched);

        } else {
            setTimers((prevTimers) => ({
                ...prevTimers,
                [materiaId]: { startTime: Date.now(), active: true }
            }));
            setActiveVideo(videoLink);
        }
    };

    const registrarFrenquencia = (materiaId, elapsedTime, percentageWatched) => {
        const data = {
            presenca: percentageWatched >= 100, // Assuming 50% is the threshold for considering presence
            timestamp: elapsedTime,
            percentageWatched
        };
        fetch(`${domain}:${port}/api/discente-materia/frequencia/${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao registrar tempo de aula.');
            }
            showToast('success', 'Sucesso', 'Frequência registrada com sucesso.');
        })
        .catch(error => {
            showToast('error', 'Erro', 'Não foi possível registrar a frequência.');
        });
    };

    const fetchPresencas = async (alunoId) => {
        try {
            const response = await fetch(`${domain}:${port}/api/discente-materia/frequencia/${alunoId}`);
            if (!response.ok) {
                throw new Error('Failed to fetch presenca data');
            }
            const presencas = await response.json();
            
            const algumComPresenca = presencas.some(objeto => objeto.presenca === true);
            if (algumComPresenca) {
                return 'ASSISTIDO';
            }else{
                return 'PENDENTE';
            }
            
            // Calcular a soma de todos os timestamps de presença
            /*const totalTimestamps = presencas.reduce((acc, curr) => acc + curr.timestamp, 0);
            console.log(totalTimestamps);
    
            // faz comparação com o tamanho do video e retorna em porcentagem
            console.log('Duration:', videoDuration);
            const presencaPercentage = Math.min((totalTimestamps / videoDuration) * 100, 100);
            return `${presencaPercentage.toFixed(2)}%`;*/
        } catch (error) {
            console.error("Error fetching presenca dafta:", error);
            showToast('error', 'Error', `Não foi possível obter a presença para o aluno com ID ${alunoId}.`);
            return 'N/A';
        }
    };
    

    useEffect(() => {
        const fetchData = async () => {
            await fetchMatricula();
            try {
                if (!matricula) {
                    return;
                }
                const response = await fetch(`${domain}:${port}/api/discente-materia/discente/${matricula}`);
                if (!response.ok) {
                    throw new Error('Network response was not ok response status: ' + response.status);
                }
                const data = await response.json();
                const materiasPresenca = await Promise.all(data.map(async (materia) => {
                    const presencaPercentage = await fetchPresencas(materia.id);
                    return { ...materia, presenca: presencaPercentage };
                }));
                console.log(materiasPresenca);
                const materiasFormatted = materiasPresenca.map((materia) => ({
                    ...materia,
                    unidade1: materia.unidade1 || '--',
                    unidade2: materia.unidade2 || '--',
                    unidade3: materia.unidade3 || '--',
                }));
                setMaterias(materiasFormatted);
            } catch (error) {
                console.error('Error fetching data:', error);
                showToast('error', 'Error', 'Não foi possível listar as matérias gararar.');
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, [id, matricula]);

    useEffect(() => {
        const interval = setInterval(() => {
            setTimers((prevTimers) => {
                const newTimers = { ...prevTimers };
                Object.keys(newTimers).forEach((key) => {
                    if (newTimers[key].active) {
                        const elapsedTime = Math.floor((Date.now() - newTimers[key].startTime) / 1000);
                        newTimers[key].time = elapsedTime;
                    }
                });
                return newTimers;
            });
        }, 1000);
        return () => clearInterval(interval);
    }, []);

    useEffect(() => {
        loadYouTubeAPI();
    }, []);

    const onVideoReady = (event) => {
        setVideoDuration(event.target.getDuration());
    };

    const formatTime = (seconds) => {
        const h = Math.floor(seconds / 3600);
        const m = Math.floor((seconds % 3600) / 60);
        const s = seconds % 60;

        const formattedTime = `${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}:${s.toString().padStart(2, '0')}`;

        return formattedTime;
    };

    const renderWatchButton = (rowData) => {
        const timer = timers[rowData.id];
        const btnstatus = timer?.active ? "danger" : "primary";
        const buttonText = timer?.active ? 'Assistindo Aula' : 'Assistir Aula';
        const timerText = timer?.active ? ` (${formatTime(timer.time)})` : '';
        const icon = timer?.active ? 'pi pi-hourglass pi-spin' : 'pi pi-play';

        return (
            <Button
                label={`${buttonText}${timerText}`}
                severity={`${btnstatus}`}
                onClick={() => handleWatchClick(rowData.id, 'https://www.youtube.com/embed/dQw4w9WgXcQ')}
                icon={icon}
            />
        );
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div className="list-alunos-container">
            <Toast ref={toast} />
            <h1>Lista de Materias</h1>
            <DataTable value={materias}>
                <Column field="materia.nome" header="MATÉRIA" />
                <Column field="materia.descricao" header="DESCRIÇÃO" />
                <Column field="unidade1" header="UNIDADE 1" />
                <Column field="unidade2" header="UNIDADE 2" />
                <Column field="unidade3" header="UNIDADE 3" />
                <Column field="provaFinal" header="FINAL" />
                <Column field="status" header="STATUS" />
                <Column field="presenca" header="FREQUÊNCIA" />
                <Column body={renderWatchButton} header="AÇÕES" />
            </DataTable>
            {activeVideo && (
                <div className="video-container">
                    <iframe
                        ref={videoRef}
                        width="560"
                        height="315"
                        src={`${activeVideo}?enablejsapi=1&autoplay=1`}
                        frameBorder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                        allowFullScreen
                        title="Video Aula"
                        onLoad={() => {
                            const player = new window.YT.Player(videoRef.current, {
                                events: {
                                    'onReady': onVideoReady
                                }
                            });
                        }}
                    ></iframe>
                </div>
            )}
        </div>
    );
};

export default ListMaterias;
