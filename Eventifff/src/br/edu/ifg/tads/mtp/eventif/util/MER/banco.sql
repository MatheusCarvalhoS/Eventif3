
CREATE SEQUENCE cracha_idcracha_seq_1;

CREATE TABLE Cracha (
                idCracha INTEGER NOT NULL DEFAULT nextval('cracha_idcracha_seq_1'),
                CONSTRAINT cracha_pk PRIMARY KEY (idCracha)
);


ALTER SEQUENCE cracha_idcracha_seq_1 OWNED BY Cracha.idCracha;

CREATE SEQUENCE endereco_idenderecoevento_seq;

CREATE TABLE Endereco (
                idEndereco INTEGER NOT NULL DEFAULT nextval('endereco_idenderecoevento_seq'),
                numero VARCHAR,
                bairro VARCHAR NOT NULL,
                cep VARCHAR NOT NULL,
                cidade VARCHAR,
                uf VARCHAR(2),
                CONSTRAINT endereco_pk PRIMARY KEY (idEndereco)
);


ALTER SEQUENCE endereco_idenderecoevento_seq OWNED BY Endereco.idEndereco;

CREATE SEQUENCE pessoa_idpessoa_seq;

CREATE TABLE Pessoa (
                idPessoa INTEGER NOT NULL DEFAULT nextval('pessoa_idpessoa_seq'),
                nomePessoa VARCHAR,
                cpf VARCHAR,
                ativo BOOLEAN DEFAULT true NOT NULL,
                rg VARCHAR,
                idEndereco INTEGER,
                CONSTRAINT pessoa_pk PRIMARY KEY (idPessoa)
);


ALTER SEQUENCE pessoa_idpessoa_seq OWNED BY Pessoa.idPessoa;

CREATE SEQUENCE gerente_idgerente_seq;

CREATE TABLE Gerente (
                idGerente INTEGER NOT NULL DEFAULT nextval('gerente_idgerente_seq'),
                senha VARCHAR NOT NULL,
                idPessoa INTEGER NOT NULL,
                CONSTRAINT gerente_pk PRIMARY KEY (idGerente)
);


ALTER SEQUENCE gerente_idgerente_seq OWNED BY Gerente.idGerente;

CREATE SEQUENCE monitor_idmonitor_seq;

CREATE TABLE Monitor (
                idMonitor INTEGER NOT NULL DEFAULT nextval('monitor_idmonitor_seq'),
                senha VARCHAR NOT NULL,
                idPessoa INTEGER NOT NULL,
                CONSTRAINT monitor_pk PRIMARY KEY (idMonitor)
);


ALTER SEQUENCE monitor_idmonitor_seq OWNED BY Monitor.idMonitor;

CREATE SEQUENCE aluno_idaluno_seq;

CREATE TABLE Aluno (
                idAluno INTEGER NOT NULL DEFAULT nextval('aluno_idaluno_seq'),
                idPessoa INTEGER NOT NULL,
                senha VARCHAR NOT NULL,
                idCracha INTEGER,
                CONSTRAINT aluno_pk PRIMARY KEY (idAluno)
);


ALTER SEQUENCE aluno_idaluno_seq OWNED BY Aluno.idAluno;

CREATE SEQUENCE evento_idevento_seq;

CREATE TABLE Evento (
                idEvento INTEGER NOT NULL DEFAULT nextval('evento_idevento_seq'),
                nomeEvento VARCHAR,
                descricaoEvento VARCHAR,
                dataInicio DATE,
                dataEncerramento DATE,
                organizador VARCHAR,
                telefoneContato VARCHAR,
                localEvento VARCHAR,
                idEndereco INTEGER,
                CONSTRAINT evento_pk PRIMARY KEY (idEvento)
);


ALTER SEQUENCE evento_idevento_seq OWNED BY Evento.idEvento;

CREATE TABLE AlunoEvento (
                idAluno INTEGER NOT NULL,
                idEvento INTEGER NOT NULL
);


CREATE SEQUENCE presencaevento_idpresencaevento_seq;

CREATE TABLE PresencaEvento (
                idPresencaEvento INTEGER NOT NULL DEFAULT nextval('presencaevento_idpresencaevento_seq'),
                horaCheckin TIMESTAMP NOT NULL,
                horaCheckout TIMESTAMP NOT NULL,
                idAtividade INTEGER NOT NULL,
                idAluno INTEGER NOT NULL,
                idEvento INTEGER NOT NULL,
                CONSTRAINT presencaevento_pk PRIMARY KEY (idPresencaEvento)
);


ALTER SEQUENCE presencaevento_idpresencaevento_seq OWNED BY PresencaEvento.idPresencaEvento;

CREATE TABLE Atividade (
                idAtividade INTEGER NOT NULL,
                idEvento INTEGER NOT NULL,
                nomeAtividade VARCHAR,
                descricaoAtividade VARCHAR,
                horaInicio TIME,
                horaEncerramento TIME,
                tipoAtividade VARCHAR,
                cargaHoraria VARCHAR,
                numeroVagas INTEGER NOT NULL,
                CONSTRAINT atividade_pk PRIMARY KEY (idAtividade)
);


CREATE TABLE MonitorAtividade (
                idMonitor INTEGER NOT NULL,
                idAtividade INTEGER NOT NULL
);


CREATE TABLE AlunoAtividade (
                idAluno INTEGER NOT NULL,
                idAtividade INTEGER NOT NULL
);


CREATE SEQUENCE presencaatividade_idpresencaatividade_seq;

CREATE TABLE PresencaAtividade (
                idPresencaAtividade INTEGER NOT NULL DEFAULT nextval('presencaatividade_idpresencaatividade_seq'),
                horaCheckin TIMESTAMP NOT NULL,
                horaCheckout TIMESTAMP NOT NULL,
                idAtividade INTEGER NOT NULL,
                idAluno INTEGER NOT NULL,
                CONSTRAINT presencaatividade_pk PRIMARY KEY (idPresencaAtividade)
);


ALTER SEQUENCE presencaatividade_idpresencaatividade_seq OWNED BY PresencaAtividade.idPresencaAtividade;

ALTER TABLE Aluno ADD CONSTRAINT cracha_aluno_fk
FOREIGN KEY (idCracha)
REFERENCES Cracha (idCracha)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Evento ADD CONSTRAINT enderecoevento_evento_fk
FOREIGN KEY (idEndereco)
REFERENCES Endereco (idEndereco)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Pessoa ADD CONSTRAINT endereco_pessoa_fk
FOREIGN KEY (idEndereco)
REFERENCES Endereco (idEndereco)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Aluno ADD CONSTRAINT pessoa_aluno_fk
FOREIGN KEY (idPessoa)
REFERENCES Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Monitor ADD CONSTRAINT pessoa_monitor_fk
FOREIGN KEY (idPessoa)
REFERENCES Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Gerente ADD CONSTRAINT pessoa_gerente_fk
FOREIGN KEY (idPessoa)
REFERENCES Pessoa (idPessoa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE MonitorAtividade ADD CONSTRAINT monitor_monitor_evento_fk
FOREIGN KEY (idMonitor)
REFERENCES Monitor (idMonitor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PresencaAtividade ADD CONSTRAINT aluno_presenca_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE AlunoAtividade ADD CONSTRAINT aluno_aluno_atividade_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PresencaEvento ADD CONSTRAINT aluno_presencaevento_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE AlunoEvento ADD CONSTRAINT aluno_alunoevento_fk
FOREIGN KEY (idAluno)
REFERENCES Aluno (idAluno)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Atividade ADD CONSTRAINT evento_atividade_fk
FOREIGN KEY (idEvento)
REFERENCES Evento (idEvento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PresencaEvento ADD CONSTRAINT evento_presencaevento_fk
FOREIGN KEY (idEvento)
REFERENCES Evento (idEvento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE AlunoEvento ADD CONSTRAINT evento_alunoevento_fk
FOREIGN KEY (idEvento)
REFERENCES Evento (idEvento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE PresencaAtividade ADD CONSTRAINT atividade_presenca_fk
FOREIGN KEY (idAtividade)
REFERENCES Atividade (idAtividade)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE AlunoAtividade ADD CONSTRAINT atividade_aluno_atividade_fk
FOREIGN KEY (idAtividade)
REFERENCES Atividade (idAtividade)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE MonitorAtividade ADD CONSTRAINT atividade_monitor_evento_fk
FOREIGN KEY (idAtividade)
REFERENCES Atividade (idAtividade)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
