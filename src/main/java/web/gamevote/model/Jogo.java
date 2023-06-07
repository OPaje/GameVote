package web.gamevote.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "jogo")
public class Jogo implements Serializable{

    private static final long serialVersionUID = 7562368353372595092L;
	
	@Id
	@SequenceGenerator(name="gerador5", sequenceName="jogo_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador5", strategy=GenerationType.SEQUENCE)
	private Long codigo;
	private String nome;
    private String descricao;
    @ManyToMany
    @JoinTable(name = "jogo_plataforma", joinColumns = @JoinColumn(name = "codigo_jogo"), inverseJoinColumns = @JoinColumn(name = "codigo_plataforma"))
    private List<Plataforma> plataformas = new ArrayList<>();

    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public void adicionarPlataforma(Plataforma plataforma) {
		plataformas.add(plataforma);
	}

	public void removerPlataforma(Plataforma plataforma) {
		plataformas.remove(plataforma);
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jogo other = (Jogo) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Jogo [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", plataformas=" + plataformas
                + "]";
    }

    
    
}
