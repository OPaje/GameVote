package web.gamevote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.gamevote.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    
}
