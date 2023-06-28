package web.gamevote.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.gamevote.model.JogosVotosDTO;
import web.gamevote.model.Voto;
import web.gamevote.repository.VotoRepository;

@Service
public class VotoService {
    
    @Autowired
    private VotoRepository votoRepository;

    @Transactional
    public void salvarVoto(Voto voto){
        votoRepository.save(voto);
    }

    public List<JogosVotosDTO> obterQuantidadeVotosPorJogo() {

        List<Object[]> listaConsulta = votoRepository.obterQuantidadeVotosJogos();
        List<JogosVotosDTO> listaJogos = new ArrayList<>();

        for (Object[] resultado : listaConsulta) {
            String nome = (String) resultado[0];
            Long quantidade = (Long) resultado[1];
            JogosVotosDTO dto = new JogosVotosDTO();
            dto.setNome(nome);
            dto.setQuantidade(quantidade);
            listaJogos.add(dto);
        }

        return listaJogos;
    }
}
