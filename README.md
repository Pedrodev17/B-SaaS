# CRM 

Um sistema de CRM (Customer Relationship Management) básico, desenvolvido como um projeto de aprendizado para solidificar conceitos de desenvolvimento back-end com Java e Spring Boot, e front-end com Angular. O objetivo é criar uma plataforma SaaS (Software as a Service) para gerenciamento de contatos e clientes.

## 🚀 Sobre o Projeto

O **B-SaaS** é uma aplicação web projetada para simplificar o gerenciamento de relacionamento com o cliente. Ele permite que usuários cadastrem, visualizem, editem e removam contatos de forma organizada e eficiente. A arquitetura do projeto é dividida em um back-end robusto construído com Spring Boot, que serve uma API RESTful, e um front-end interativo que consome essa API.

## ✨ Funcionalidades

  - [x] **Autenticação de Usuários:** Sistema de login e segurança para acesso à plataforma.
  - [x] **Gerenciamento de Contatos:**
      - Criar, Ler, Atualizar e Deletar (CRUD) contatos.
      - Listagem e busca de contatos.
  - [ ] **Dashboard Principal:** Visualização rápida de métricas e atividades recentes.
  - [ ] **Gerenciamento de Empresas:** Associação de contatos a empresas.

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias:

#### **Back-end**

  - **Java 17**
  - **Spring Boot 3**
  - **Spring Security:** Para gerenciamento de autenticação e autorização.
  - **Spring Data JPA:** Para persistência de dados.
  - **MySQL:** Como banco de dados relacional.
  - **Gradle:** Como gerenciador de dependências e build.
  - **Lombok:** Para reduzir código boilerplate.

#### **Front-end (previsto)**

  - **Angular 17**
  - **TypeScript**
  - **HTML5 / CSS3**

## 🏁 Como Começar

Para executar o back-end do projeto localmente, siga os passos abaixo.

### **Pré-requisitos**

  - **Java Development Kit (JDK) 17** ou superior.
  - **Gradle** instalado.
  - Um servidor de banco de dados **MySQL** ativo.
  - Um cliente de API, como o [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/), para testar os endpoints.

### **Instalação**

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/Pedrodev17/B-SaaS.git
    cd B-SaaS
    ```

2.  **Configure o banco de dados:**

      - Crie um banco de dados no seu servidor MySQL.
      - Renomeie o arquivo `application.properties.example` para `application.properties` (se houver).
      - Abra o arquivo `src/main/resources/application.properties` e atualize as seguintes propriedades com as suas credenciais do MySQL:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco_de_dados
        spring.datasource.username=seu_usuario
        spring.datasource.password=sua_senha
        ```

3.  **Execute a aplicação:**
    Utilize o wrapper do Gradle para compilar e iniciar o servidor Spring Boot:

    ```bash
    ./gradlew bootRun
    ```

4.  **Acesse a API:**
    O servidor estará rodando em `http://localhost:8080`. Você pode usar um cliente de API para começar a testar os endpoints disponíveis.

## 👨‍💻 Autor

| [\<img src="https://avatars.githubusercontent.com/u/104190366?v=4" width="100px;"/\>\<br /\>\<sub\>\<b\>Pedro Henrique\</b\>\</sub\>](https://github.com/Pedrodev17) |
| :---: |
