package web.gamevote.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.gamevote.model.Jogo;
import web.gamevote.model.Usuario;
import web.gamevote.repository.JogoRepository;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public void votar(Long jogoId, String name) {
        Optional<Jogo> optionalJogo = jogoRepository.findById(jogoId);
        if (optionalJogo.isPresent()) {
            Jogo jogo = optionalJogo.get();
           // if (jogo.getVotos().contains(usuario)) {
               // throw new IllegalStateException("Usuário já votou neste jogo.");
           // } else {
                //jogo.getVotos().add(usuario);
               // usuario.getJogosVotados().add(jogo);
              //jogoRepository.save(jogo);
          //  }
        } else {
            throw new IllegalArgumentException("Jogo não encontrado.");
        }
    }
    
}
