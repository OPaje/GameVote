package web.gamevote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.gamevote.model.Usuario;
import web.gamevote.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void salvar(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
