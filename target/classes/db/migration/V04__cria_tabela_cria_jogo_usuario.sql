
CREATE TABLE public.jogo_usuario
(
    codigo_jogo bigint NOT NULL,
    codigo_usuario bigint NOT NULL
);

ALTER TABLE public.jogo_usuario
    ADD FOREIGN KEY (codigo_jogo)
    REFERENCES public.jogo (codigo)
    NOT VALID;


ALTER TABLE public.jogo_usuario
    ADD FOREIGN KEY (codigo_usuario)
    REFERENCES public.usuario (codigo)
    NOT VALID;

END;