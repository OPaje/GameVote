BEGIN;

CREATE TABLE public.jogo
(
    codigo serial NOT NULL,
    nome text,
    descricao text,
    status text DEFAULT 'ATIVO',
    PRIMARY KEY (codigo)
);

CREATE TABLE public.plataforma
(
    codigo serial NOT NULL,
    nome text,
    PRIMARY KEY (codigo)
);

CREATE TABLE public.jogo_plataforma
(
    codigo_jogo bigint NOT NULL,
    codigo_plataforma bigint NOT NULL
);

ALTER TABLE public.jogo_plataforma
    ADD FOREIGN KEY (codigo_jogo)
    REFERENCES public.jogo (codigo)
    NOT VALID;


ALTER TABLE public.jogo_plataforma
    ADD FOREIGN KEY (codigo_plataforma)
    REFERENCES public.plataforma (codigo)
    NOT VALID;

END;