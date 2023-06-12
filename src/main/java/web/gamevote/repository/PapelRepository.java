package web.gamevote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.gamevote.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long>{
    
}
