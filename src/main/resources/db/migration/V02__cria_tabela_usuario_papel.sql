BEGIN;

CREATE TABLE public.usuario
(
    codigo serial NOT NULL,
    nome text,
    email text,
    senha text,
    nome_usuario text,
    data_nascimento date,
    codigo_papel bigint NOT NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE public.papel
(
    codigo serial NOT NULL,
    nome text,
    PRIMARY KEY (codigo)
);

ALTER TABLE public.usuario
    ADD FOREIGN KEY (codigo_papel)
    REFERENCES public.papel(codigo)
    NOT VALID;

END;