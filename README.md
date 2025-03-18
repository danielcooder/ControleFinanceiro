# 💰 Controle Financeiro API 🏦  
Este projeto é uma API de controle financeiro desenvolvida com Spring Boot, utilizando JPA para persistência de dados e Lombok para redução de código boilerplate. A API permite gerenciar pessoas e suas transações financeiras, com suporte para cálculo de saldos, receitas e despesas  

## 🚀 Tecnologias  
- **Spring Boot** → Estrutura principal da API.  
- **JPA** → Persistência de dados.  
- **Lombok** → Redução de código repetitivo.  
- **Swagger** → Documentação da API.  
- **Flyway** → Migração do banco de dados.  

## 📂 Estrutura  

A API possui três entidades principais:  

- **Pessoa** → Representa um usuário, contendo `id`, `nome`, `email`, `idade` e uma lista de transações.  
- **Transacao** → Representa uma transação financeira, com `id`, `descricao`, `valor`, `tipo (RECEITA/DESPESA)`, `data` e associação a uma pessoa.  
- **TipoTransacao (ENUM)** → Define os tipos `RECEITA` ou `DESPESA`.  

### 🗄️ Repositórios  
- **PessoaRepository** → Interface CRUD para pessoas.  
- **TransacaoRepository** → Interface CRUD para transações, incluindo consulta para somar valores por pessoa e tipo.  

### ⚙️ Serviços  
- **PessoaService** → Regras de negócio para pessoas, incluindo cálculos de saldo, receitas e despesas.  
- **TransacaoService** → Regras de negócio para transações, garantindo a vinculação correta entre pessoa e transação.  

### 🎮 Controladores  
  A API expõe endpoints REST para operações de pessoas e transações.  

- **PessoaController** → Controla operações de listagem, busca por ID, criação, atualização e remoção de pessoas.  
- **TransacaoController** → Gerencia transações, permitindo listagem, busca por ID, criação e atualização.  

### ⚠️ Tratamento de Exceções  
- **EntidadeNaoEncontradaException** → Retorna erro `404` caso uma entidade não seja encontrada.  
- **GlobalExceptionHandler** → Captura exceções e retorna respostas padronizadas para erros.  

## 🚀 Como Executar  

1. Clone o repositório.  
2. Configure o banco de dados no `application.properties` ou `application.yml`.  
3. Execute o projeto com o comando:  
   ```sh
   mvn spring-boot:run

Acesse a documentação da API via Swagger em:
bash
Copiar
Editar
http://localhost:8080/swagger-ui.html
🔗 Endpoints
A API fornece endpoints para gerenciar pessoas e transações.

## 🔗 Endpoints Principais:
### Pessoas:

- GET /pessoas → Lista todas as pessoas.
- GET /pessoas/{id} → Retorna uma pessoa pelo ID.
- POST /pessoas → Cria uma nova pessoa.
- PUT /pessoas/{id} → Atualiza uma pessoa existente.
- DELETE /pessoas/{id} → Remove uma pessoa.

### Transações:

- GET /transacoes → Lista todas as transações.
- GET /transacoes/{id} → Retorna uma transação pelo ID.
- POST /transacoes/{pessoaId} → Cria uma transação associada a uma pessoa.
- PUT /transacoes/{id} → Atualiza uma transação existente.


