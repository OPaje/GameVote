package web.gamevote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.gamevote.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long>{
    
}
