package web.gamevote.controller;


import java.util.ArrayList;
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
import web.gamevote.model.Jogo;
import web.gamevote.model.JogosVotosDTO;
import web.gamevote.model.Status;
import web.gamevote.model.Usuario;
import web.gamevote.model.Voto;
import web.gamevote.repository.JogoRepository;
import web.gamevote.repository.PlataformaRepository;
import web.gamevote.repository.UsuarioRepository;
import web.gamevote.service.VotoService;

@Controller
@RequestMapping("/votos")
public class VotoController {
    
    private static final Logger logger = LoggerFactory.getLogger(VotoController.class);

    @Autowired
    private VotoService votoService;

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastrar")
    public String abrirCadastrarVoto(Voto voto, Model model){

        List<Jogo> jogos = jogoRepository.findByStatus(Status.ATIVO);
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("jogos", jogos);
        model.addAttribute("usuarios", usuarios); // temporário

        return "voto/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarVoto(Voto voto){

        votoService.salvarVoto(voto);

        return "redirect:/votos/cadastrosucesso";
    }

    @GetMapping("/cadastrosucesso")
    public String cadastroSucesso(Voto voto, Model model){

        List<Jogo> jogos = jogoRepository.findByStatus(Status.ATIVO);
        List<Usuario> usuarios = usuarioRepository.findAll();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("jogos", jogos);

        NotificacaoAlertify notificacaoAlertify = new NotificacaoAlertify("Voto cadastrado com sucesso", TipoNotificaoAlertify.SUCESSO);

        model.addAttribute("notificacao", notificacaoAlertify);

        return "voto/cadastrar";
    }

    @GetMapping("/buscarporplataforma")
    public String abrirRanking(Model model){
        /* List<Plataforma> plataformas = plataformaRepository.findAll();
        model.addAttribute("plataformas", plataformas); */
        return "ranking/entradaplataforma";
    }

    @PostMapping("/buscarporplataforma")
    public String mostraRanking(String nome, Model model){

        List<JogosVotosDTO> jogos = votoService.obterQuantidadeVotosPorJogo();
        List<JogosVotosDTO> jogosComVotosPorPlataforma = new ArrayList<>();

        if(!nome.isEmpty()){
            for (JogosVotosDTO jogo : jogos) {
                if(jogo.getPlataforma().equals(nome.toUpperCase())){
                    jogosComVotosPorPlataforma.add(jogo);
                }
            }
        }

        model.addAttribute("jogos", jogosComVotosPorPlataforma);
        return "ranking/mostraranking";

    }

    
}
