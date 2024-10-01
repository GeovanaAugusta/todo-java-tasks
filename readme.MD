# Tarefas Microservice

Este projeto é um microserviço para gerenciamento de tarefas, desenvolvido usando Spring Boot. Ele permite a criação, atualização, listagem e exclusão de tarefas, bem como o upload de arquivos para o Amazon S3.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **JPA/Hibernate**
- **MySQL**
- **Amazon S3 (SDK v2)**
- **Maven**

## Funcionalidades

- Criação de tarefas.
- Atualização de tarefas.
- Exclusão de tarefas.
- Listagem de todas as tarefas ou tarefas por usuário.
- Upload de arquivos para o Amazon S3 associados a uma tarefa.

## Pré-requisitos

Antes de iniciar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- **Java 17+**
- **Maven 3.6+**
- **MySQL**
- **Amazon AWS Account** com um bucket S3 configurado.

## Configuração do Projeto

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-repositorio/tarefas-microservice.git
   cd tarefas-microservice
   ```
2. **Configuração do application.properties:**

No diretório src/main/resources, configure o arquivo application.properties com as credenciais do banco de dados MySQL e da AWS:

```properties

spring.datasource.url=jdbc:mysql://localhost:3306/tarefas_db
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

aws.accessKeyId=SEU_ACCESS_KEY
aws.secretAccessKey=SUA_SECRET_KEY
aws.s3.bucket=nome-do-seu-bucket
aws.region=us-east-2
```

3. **Build do Projeto:**

Para compilar o projeto, rode o seguinte comando Maven:

```bash
mvn clean install
```

4. **Executando o Projeto:**

Para rodar o microserviço, use o seguinte comando:

```bash
    mvn spring-boot:run
```

5. **Endpoints da API**

- **Listar todas as tarefas**

  URL: /api/tasks
  Método: GET
  Descrição: Retorna uma lista de todas as tarefas.
- **Buscar tarefa por ID**

  URL: /api/tasks/{id}
  Método: GET
  Descrição: Retorna os detalhes de uma tarefa específica.
- **Buscar tarefas por usuário**

  URL: /api/tasks/user/{userId}
  Método: GET
  Descrição: Retorna todas as tarefas atribuídas a um usuário específico.
- **Criar uma nova tarefa**

  URL: /api/tasks

  Método: POST
  Descrição: Cria uma nova tarefa.
  Corpo do Pedido:

  ```json
  {
    "nome": "Nova Tarefa",
    "descricao": "Descrição da tarefa",
    "usuarioId": 1
  }
  ```
- **Atualizar uma tarefa**

  URL: /api/tasks/{id}
  Método: PUT
  Descrição: Atualiza os dados de uma tarefa específica.
  Corpo do Pedido: Veja o formato do corpo de criação de tarefa.
- **Deletar uma tarefa**

  URL: /api/tasks/{id}
  Método: DELETE
  Descrição: Remove uma tarefa do sistema.
- **Upload de Arquivo**

  URL: /api/tasks/upload
  Método: POST
  Descrição: Realiza o upload de um arquivo para o Amazon S3.
  Parâmetro: file (MultipartFile)

6. **Testes**

   Para rodar os testes unitários:

```bash
mvn test
```
