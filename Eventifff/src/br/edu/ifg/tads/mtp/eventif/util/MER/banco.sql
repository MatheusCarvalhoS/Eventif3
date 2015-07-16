
CREATE TABLE Endereco (
                idEndereco INTEGER NOT NULL DEFAULT nextval('endereco_idenderecoevento_seq'),
                numero VARCHAR,
                bairro VARCHAR NOT NULL,
                cep VARCHAR NOT NULL,
                cidade VARCHAR,
                uf VARCHAR(2),
                CONSTRAINT endereco_pk PRIMARY KEY (idEndereco)
);

CREATE TABLE Pessoa (
                idPessoa INTEGER NOT NULL DEFAULT nextval('pessoa_idpessoa_seq'),
                nomePessoa VARCHAR,
                cpf VARCHAR,
                ativo BOOLEAN DEFAULT true,
                rg VARCHAR,
                idEndereco INTEGER,
                CONSTRAINT pessoa_pk PRIMARY KEY (idPessoa)
);

CREATE TABLE Aluno (
                idAluno INTEGER NOT NULL DEFAULT nextval('aluno_idaluno_seq'),
                idPessoa INTEGER,
                senha VARCHAR,
                CONSTRAINT aluno_pk PRIMARY KEY (idAluno)
);

CREATE TABLE Monitor (
                idMonitor INTEGER NOT NULL DEFAULT nextval('monitor_idmonitor_seq'),
                senha VARCHAR,
                idAluno INTEGER NOT NULL,
                CONSTRAINT monitor_pk PRIMARY KEY (idMonitor)
);

CREATE TABLE Evento (
                idEvento INTEGER NOT NULL DEFAULT nextval('evento_idevento_seq'),
                nomeEvento VARCHAR,
                descricaoEvento VARCHAR,
                dataInicio DATE,
                email VARCHAR,
                dataEncerramento DATE,
                organizador VARCHAR,
                telefoneContato VARCHAR,
                localEvento VARCHAR,
                idEndereco INTEGER,
                CONSTRAINT evento_pk PRIMARY KEY (idEvento)
);

CREATE TABLE AlunoEvento (
                idAluno INTEGER,
                idEvento INTEGER NOT NULL
);

CREATE TABLE PresencaEvento (
                idPresencaEvento INTEGER NOT NULL DEFAULT nextval('presencaevento_idpresencaevento_seq'),
                horaCheckin TIMESTAMP,
                horaCheckout TIMESTAMP,
                idAluno INTEGER,
                idEvento INTEGER,
                CONSTRAINT presencaevento_pk PRIMARY KEY (idPresencaEvento)
);


CREATE TABLE Atividade (
                idAtividade INTEGER NOT NULL DEFAULT nextval('atividade_idatividade_seq'),
                idEvento INTEGER NOT NULL,
                nomeAtividade VARCHAR,
                descricaoAtividade VARCHAR,
                horaInicio VARCHAR,
                horaEncerramento VARCHAR,
                data DATE,
                palestrante VARCHAR,
                tipoAtividade VARCHAR,
                cargaHoraria VARCHAR,
                numeroVagas INTEGER,
                CONSTRAINT atividade_pk PRIMARY KEY (idAtividade)
);

CREATE TABLE MonitorAtividade (
                idMonitor INTEGER,
                idAtividade INTEGER
);


CREATE TABLE AlunoAtividade (
                idAluno INTEGER NOT NULL,
                idAtividade INTEGER NOT NULL
);

CREATE TABLE PresencaAtividade (
                idPresencaAtividade INTEGER NOT NULL DEFAULT nextval('presencaatividade_idpresencaatividade_seq'),
                horaCheckin TIMESTAMP,
                horaCheckout TIMESTAMP,
                idAtividade INTEGER,
                idAluno INTEGER,
                CONSTRAINT presencaatividade_pk PRIMARY KEY (idPresencaAtividade)
);


ALTER TABLE Evento ADD CONSTRAINT enderecoevento_evento_fk
FOREIGN KEY (idEndereco)
REFERENCES Endereco (idEndereco)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Pessoa ADD CONSTRAINT endereco_pessoa_fk
FOREIGN KEY (idEndereco)
REFERENCES Endereco (idEndereco)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Aluno ADD CONSTRAINT pessoa_aluno_fk
FOREIGN KEY (idPessoa)
REFERENCES Pessoa (idPessoa)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE PresencaAtividade ADD CONSTRAINT aluno_presenca_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE AlunoAtividade ADD CONSTRAINT aluno_aluno_atividade_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE PresencaEvento ADD CONSTRAINT aluno_presencaevento_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE AlunoEvento ADD CONSTRAINT aluno_alunoevento_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Monitor ADD CONSTRAINT aluno_monitor_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE MonitorAtividade ADD CONSTRAINT monitor_monitor_evento_fk
FOREIGN KEY (idMonitor)
REFERENCES Monitor (idMonitor)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Atividade ADD CONSTRAINT evento_atividade_fk
FOREIGN KEY (idEvento)
REFERENCES Evento (idEvento)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE PresencaEvento ADD CONSTRAINT evento_presencaevento_fk
FOREIGN KEY (idEvento)
REFERENCES Evento (idEvento)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE AlunoEvento ADD CONSTRAINT evento_alunoevento_fk
FOREIGN KEY (idEvento)
REFERENCES Evento (idEvento)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE PresencaAtividade ADD CONSTRAINT atividade_presenca_fk
FOREIGN KEY (idAtividade)
REFERENCES Atividade (idAtividade)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE AlunoAtividade ADD CONSTRAINT atividade_aluno_atividade_fk
FOREIGN KEY (idAtividade)
REFERENCES Atividade (idAtividade)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE MonitorAtividade ADD CONSTRAINT atividade_monitor_evento_fk
FOREIGN KEY (idAtividade)
REFERENCES Atividade (idAtividade)
ON DELETE CASCADE
ON UPDATE CASCADE;