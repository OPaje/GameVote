package web.gamevote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.gamevote.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
