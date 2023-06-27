package web.gamevote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
