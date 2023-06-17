package web.gamevote.repository.queries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.gamevote.model.Jogo;
import web.gamevote.model.filter.JogoFilter;

public interface JogoQueries {

      Page<Jogo> filtrar(JogoFilter filtro, Pageable pageable);
 
}
