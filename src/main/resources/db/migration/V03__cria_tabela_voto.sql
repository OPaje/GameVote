CREATE TABLE public.voto
(
    codigo serial NOT NULL,
    codigo_jogo integer,
    codigo_usuario integer,
    PRIMARY KEY(codigo)
);

ALTER TABLE public.voto
    ADD FOREIGN KEY (codigo_jogo)
    REFERENCES public.jogo (codigo)
    NOT VALID;

ALTER TABLE public.voto
    ADD FOREIGN KEY (codigo_usuario)
    REFERENCES public.usuario (codigo)
    NOT VALID;