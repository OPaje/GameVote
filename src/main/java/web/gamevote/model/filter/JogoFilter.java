package web.gamevote.model.filter;

import web.gamevote.model.Status;

public class JogoFilter {
   
    private Long codigo; 
    private String nome; 
    private String descricao; 
    private Status status;

    public Long getCodigo() {
     return codigo;
    }
    public void setCodigo(Long codigo) {
     this.codigo = codigo;
    }
    public String getNome() {
     return nome;
    }
    public void setNome(String nome) {
     this.nome = nome;
    }
    public String getDescricao() {
     return descricao;
    }
    public void setDescricao(String descricao) {
     this.descricao = descricao;
    }
    public Status getStatus() {
     return status;
    }
    public void setStatus(Status status) {
     this.status = status;
    }

    @Override
    public String toString() {
     return "JogoFilter [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", status=" + status + "]";
    }

}
