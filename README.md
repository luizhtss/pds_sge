
```markdown


## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:
- Docker
- Docker Compose

## Clonando o Projeto

Clone este repositório para sua máquina local usando o seguinte comando:

```bash
git clone (https://github.com/luizhtss/pds_sge/
```

## Executando o Projeto

1. Navegue até o diretório raiz do projeto clonado:

```bash
cd pds_sge
```

2. Execute o Docker Compose para criar e iniciar os contêineres Docker:

```bash
docker-compose up 
```

3. Aguarde até que todos os contêineres sejam construídos e iniciados. Isso pode levar alguns minutos na primeira execução.

4. Após a conclusão, você poderá acessar os seguintes serviços:

- Aplicativo Spring Boot: http://localhost:8080
- Aplicativo React: http://localhost:3000

5. Quando terminar, você pode parar os contêineres pressionando `Ctrl + C` no terminal onde o `docker-compose` está sendo executado.

## Configurações Adicionais (se necessário)

- Se precisar alterar as portas padrão dos serviços, você pode fazer isso no arquivo `docker-compose.yml`.
- Certifique-se de que seu aplicativo React esteja configurado para fazer chamadas à API REST fornecida pelo aplicativo Spring Boot em `http://localhost:8080`.
