package web.gamevote.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.gamevote.ajax.NotificacaoAlertify;
import web.gamevote.ajax.TipoNotificaoAlertify;
import web.gamevote.model.Papel;
import web.gamevote.model.Usuario;
import web.gamevote.repository.PapelRepository;
import web.gamevote.service.CadastroUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private PapelRepository papelRepository;

    @GetMapping("/cadastrar")
    public String abrirCadastroUsuario(Usuario usuario, Model model){
        
        List<Papel> papeis = papelRepository.findAll();
        model.addAttribute("todosPapeis", papeis);

        return "usuario/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Usuario usuario){

        cadastroUsuarioService.salvar(usuario);
        return "redirect:/usuarios/cadastrosucesso";
    }

    @GetMapping("/cadastrosucesso")
    public String mostrarCadastroSucesso(Usuario usuario, Model model){
        List<Papel> papeis = papelRepository.findAll();
		model.addAttribute("todosPapeis", papeis);
		NotificacaoAlertify notificacao = 
				new NotificacaoAlertify("Cadastro de usuário efetuado com sucesso.",
						                TipoNotificaoAlertify.SUCESSO);
		model.addAttribute("notificacao", notificacao);
		return "usuario/cadastrar";
    }

}
