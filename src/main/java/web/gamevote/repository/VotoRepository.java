package web.gamevote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import web.gamevote.model.Jogo;
import web.gamevote.model.JogosVotosDTO;
import web.gamevote.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    
    /* @Query(value = "SELECT j.nome, COUNT(v.codigo_jogo) AS quantidade" +
         " FROM voto v INNER JOIN jogo j ON v.codigo_jogo = j.codigo GROUP BY j.nome ORDER BY quantidade DESC", nativeQuery = true)
    List<Object[]> obterQuantidadeVotosJogosSemPlataforma();
 */
    @Query(value = "WITH t1 AS (\r\n" + //
            "    SELECT DISTINCT j.nome AS nome_jogo, p.nome AS nome_plataforma\r\n" + //
            "    FROM jogo_plataforma \r\n" + //
            "    INNER JOIN jogo j ON jogo_plataforma.codigo_jogo = j.codigo\r\n" + //
            "    INNER JOIN plataforma p ON p.codigo = jogo_plataforma.codigo_plataforma\r\n" + //
            "),\r\n" + //
            "t2 AS (\r\n" + //
            "    SELECT j.nome AS nome_jogo, COUNT(v.codigo_jogo) AS quantidade\r\n" + //
            "    FROM voto v \r\n" + //
            "    INNER JOIN jogo j ON v.codigo_jogo = j.codigo \r\n" + //
            "    GROUP BY j.nome \r\n" + //
            ")\r\n" + //
            "SELECT t1.nome_jogo, t1.nome_plataforma, t2.quantidade\r\n" + //
            "FROM t1\r\n" + //
            "JOIN t2 ON t1.nome_jogo = t2.nome_jogo\r\n" + //
            "ORDER BY t2.quantidade DESC;", nativeQuery = true)
    List<Object[]> obterQuantidadeVotosJogos();

}
