package web.gamevote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.gamevote.model.Jogo;
import web.gamevote.repository.queries.JogoQueries;

public interface JogoRepository extends JpaRepository<Jogo, Long>, JogoQueries{
     

}
