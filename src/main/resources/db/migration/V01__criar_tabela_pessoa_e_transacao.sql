-- Criar a tabela 'pessoa'
CREATE TABLE pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    idade INT NOT NULL
);

-- Inserir dados na tabela 'pessoas'
INSERT INTO pessoa (nome, email, idade) VALUES ('Ana Souza', 'ana@email.com', 30);
INSERT INTO pessoa (nome, email, idade) VALUES ('Bruno Lima', 'bruno@email.com', 25);
INSERT INTO pessoa (nome, email, idade) VALUES ('Carlos Mendes', 'carlos@email.com', 40);
INSERT INTO pessoa (nome, email, idade) VALUES ('Daniela Ferreira', 'daniela@email.com', 35);
INSERT INTO pessoa (nome, email, idade) VALUES ('Eduardo Santos', 'eduardo@email.com', 28);
INSERT INTO pessoa (nome, email, idade) VALUES ('Fernanda Oliveira', 'fernanda@email.com', 32);
INSERT INTO pessoa (nome, email, idade) VALUES ('Gabriel Costa', 'gabriel@email.com', 22);
INSERT INTO pessoa (nome, email, idade) VALUES ('Helena Martins', 'helena@email.com', 27);
INSERT INTO pessoa (nome, email, idade) VALUES ('Isabela Nunes', 'isabela@email.com', 45);
INSERT INTO pessoa (nome, email, idade) VALUES ('João Pedro', 'joao@email.com', 29);

-- Criar a tabela 'transacao'
CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(8) NOT NULL,
    CHECK (tipo IN ('RECEITA', 'DESPESA')),
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    data DATE,
    pessoa_id INT NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id) ON DELETE CASCADE
);

-- Inserir dados na tabela 'transacao'
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Salário', 5000.00, 'RECEITA', 1);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Compra mercado', 200.00, 'DESPESA', 1);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Venda de produto', 150.00, 'RECEITA', 2);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Aluguel', 1200.00, 'DESPESA', 3);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Investimento', 3000.00, 'RECEITA', 4);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Conta de luz', 150.00, 'DESPESA', 5);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Freelance', 800.00, 'RECEITA', 6);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Transporte', 100.00, 'DESPESA', 7);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Compra de móveis', 2200.00, 'DESPESA', 8);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Bônus anual', 7000.00, 'RECEITA', 9);
INSERT INTO transacao (descricao, valor, tipo, pessoa_id) VALUES ('Teste', 5000.00, 'DESPESA', 8);

