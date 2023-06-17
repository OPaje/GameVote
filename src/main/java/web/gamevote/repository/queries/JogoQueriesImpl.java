package web.gamevote.repository.queries;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;

import web.gamevote.model.Jogo;
import web.gamevote.model.Status;
import web.gamevote.model.filter.JogoFilter;
import web.gamevote.repository.pagination.PaginacaoUtil;

public class JogoQueriesImpl implements JogoQueries {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Jogo> filtrar(JogoFilter filtro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Jogo> criteriaQuery = builder.createQuery(Jogo.class);
        Root<Jogo> v = criteriaQuery.from(Jogo.class);
        TypedQuery<Jogo> typedQuery;
        List<Predicate> predicateList = new ArrayList<>();

        if (filtro.getCodigo() != null) {
            predicateList.add(builder.equal(v.<Long>get("codigo"), filtro.getCodigo()));
        }

        if (StringUtils.hasText(filtro.getNome())) {
            predicateList.add(builder.like(
                    builder.lower(v.<String>get("nome")),
                    "%" + filtro.getNome().toLowerCase() + "%"));
        }
      

        if (StringUtils.hasText(filtro.getDescricao())) {
            predicateList.add(builder.like(
                    builder.lower(v.<String>get("profissao")),
                    "%" + filtro.getDescricao().toLowerCase() + "%"));
        }

        predicateList.add(builder.equal(v.<Status>get("status"), Status.ATIVO));

        Predicate[] predArray = new Predicate[predicateList.size()];
        predicateList.toArray(predArray);

        criteriaQuery.select(v).where(predArray);

        PaginacaoUtil.prepararOrdem(v, criteriaQuery, builder, pageable);

        typedQuery = manager.createQuery(criteriaQuery);

        PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

        List<Jogo> Jogos = typedQuery.getResultList();
        
        long totalJogos = getTotalJogos(filtro);
        return new PageImpl<>(Jogos, pageable, totalJogos); 
    }

    private Long getTotalJogos(JogoFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Jogo> v = criteriaQuery.from(Jogo.class);
        List<Predicate> predicateList = new ArrayList<>();

        if (filtro.getCodigo() != null) {
            predicateList.add(builder.equal(v.<Long>get("codigo"), filtro.getCodigo()));
        }

        if (StringUtils.hasText(filtro.getNome())) {
            predicateList.add(builder.like(
                    builder.lower(v.<String>get("nome")),
                    "%" + filtro.getNome().toLowerCase() + "%"));
        }

        if (StringUtils.hasText(filtro.getDescricao())) {
            predicateList.add(builder.like(
                    builder.lower(v.<String>get("cpf")),
                    "%" + filtro.getDescricao().toLowerCase() + "%"));
        }

        predicateList.add(builder.equal(v.<Status>get("status"), Status.ATIVO));

        Predicate[] predArray = new Predicate[predicateList.size()];
        predicateList.toArray(predArray);

        criteriaQuery.select(builder.count(v)).where(predArray);

        return manager.createQuery(criteriaQuery).getSingleResult();

    }
}
