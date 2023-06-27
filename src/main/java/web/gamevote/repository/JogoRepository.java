package web.gamevote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.gamevote.model.Jogo;
import web.gamevote.model.Status;
import web.gamevote.repository.queries.JogoQueries;

public interface JogoRepository extends JpaRepository<Jogo, Long>, JogoQueries{
     
    List<Jogo> findByStatus(Status status);
}
