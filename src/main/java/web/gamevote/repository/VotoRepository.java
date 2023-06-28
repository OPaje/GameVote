package web.gamevote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.gamevote.model.Jogo;
import web.gamevote.model.JogosVotosDTO;
import web.gamevote.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    
    @Query(value = "SELECT j.nome, COUNT(v.codigo_jogo) AS quantidade" +
            " FROM voto v INNER JOIN jogo j ON v.codigo_jogo = j.codigo GROUP BY j.nome ORDER BY quantidade DESC", nativeQuery = true)
    List<Object[]> obterQuantidadeVotosJogos();
}
