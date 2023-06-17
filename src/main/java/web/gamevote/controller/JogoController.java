package web.gamevote.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Sort;

import web.gamevote.ajax.NotificacaoAlertify;
import web.gamevote.ajax.TipoNotificaoAlertify;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;


import web.gamevote.model.Jogo;
import web.gamevote.model.Status;
import web.gamevote.model.filter.JogoFilter;
import web.gamevote.pagination.PageWrapper;
import web.gamevote.repository.JogoRepository;

@Controller
@RequestMapping("/jogos")
public class JogoController {

 @Autowired
 private JogoRepository jogoRepository; 

 @GetMapping("/cadastrar")
 public String abrirCadastro(Jogo jogo){
   return "jogos/cadastrar";
 }

 @PostMapping("/cadastrar")
 public String cadastrar(Jogo jogo){
    jogoRepository.save(jogo);
    return "redirect:/jogos/mostrarmensagemcadastrook";
 }

 @GetMapping("/mostrarmensagemcadastrook")
    public String mostrarMensagemCadastroOK(Model model, Jogo jogo) {
        NotificacaoAlertify notificacaoAlertify = new NotificacaoAlertify("Jogo inserido com sucesso!");
        notificacaoAlertify.setTipo(TipoNotificaoAlertify.SUCESSO);
        notificacaoAlertify.setIntervalo(5);
        model.addAttribute("notificacao", notificacaoAlertify);
        return "jogos/cadastrar";
    }

 @GetMapping("/abrirpesquisar")
    public String abrirPesquisar(Model model) {
        model.addAttribute("url", "/jogos/pesquisar");
        model.addAttribute("uso", "jogos");
        return "jogos/pesquisar";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(JogoFilter filtro, Model model,
            @PageableDefault(size = 5)
            @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) 
            Pageable pageable,
            HttpServletRequest request) {
        Page<Jogo> pagina = jogoRepository.filtrar(filtro, pageable);
        PageWrapper<Jogo> paginaWrapper = new PageWrapper<>(pagina, request);
        model.addAttribute("pagina", paginaWrapper);
        model.addAttribute("uso", "jogos");
        return "jogos/mostrartodas";
    }

    @PostMapping("/abriralterar")
    public String abrirAlterar(Long codigo, Model model) {
        Optional<Jogo> optjogo = jogoRepository.findById(codigo);
        if (optjogo.isPresent()) {
            model.addAttribute("jogo", optjogo.get());
            return "jogos/alterar";
        } else {
            model.addAttribute("opcao", "jogos");
            model.addAttribute("mensagem", "Não existe jogo com código: " + codigo);
            return "mostrarmensagem";
        }
    }

  @PostMapping("/alterar")
    public String alterar(Jogo jogo) {
        jogoRepository.save(jogo);
        return "redirect:/jogos/mostrarmensagemalterarok";
    }

    @GetMapping("/mostrarmensagemalterarok")
    public String mostrarMensagemAlterarOK(Model model) {
        NotificacaoAlertify notificacao = new NotificacaoAlertify("jogo alterado com sucesso!",
        TipoNotificaoAlertify.SUCESSO);
        model.addAttribute("notificacao", notificacao);
        model.addAttribute("url", "/jogos/pesquisar");
        model.addAttribute("uso", "jogos");
        return "jogos/pesquisar";
    }

     @PostMapping("/abrirremover")
    public String abrirConfirmar(Long codigo, Model model) {
        Optional<Jogo> optJogo = jogoRepository.findById(codigo);
        if (optJogo.isPresent()) {
            model.addAttribute("Jogo", optJogo.get());
            return "jogos/confirmarremocao";
        } else {
            model.addAttribute("opcao", "Jogos");
            model.addAttribute("mensagem", "Não existe Jogo com código: " + codigo);
            return "mostrarmensagem";
        }
    }

    @PostMapping("/remover")
    public String remover(Long codigo, Model model) {
        Optional<Jogo> optJogo = jogoRepository.findById(codigo);
        if (optJogo.isPresent()) {
            Jogo jogo = optJogo.get();
            jogo.setStatus(Status.INATIVO);
            jogoRepository.save(jogo);
            return "redirect:/jogos/mostrarmensagemremocaook";
        } else {
            model.addAttribute("opcao", "jogos");
            model.addAttribute("mensagem", "Impossível remover um jogo com o código: " + codigo);
            return "mostrarmensagem";
        }
    }

    @GetMapping("/mostrarmensagemremocaook")
    public String mostrarMensagemRemoverOK(Model model) {
        NotificacaoAlertify notificacao = new NotificacaoAlertify("Jogo removido com sucesso!",
         TipoNotificaoAlertify.SUCESSO);
        model.addAttribute("notificacao", notificacao);
        model.addAttribute("url", "/jogos/pesquisar");
        model.addAttribute("uso", "jogos");
        return "jogos/pesquisar";
    }

}
