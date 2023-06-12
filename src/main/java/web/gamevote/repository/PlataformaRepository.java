package web.gamevote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.gamevote.model.Plataforma;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long>{
    
}
