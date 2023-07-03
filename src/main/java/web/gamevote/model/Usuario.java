package web.gamevote.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import web.gamevote.service.NomeUsuarioUnicoService;
import web.gamevote.validation.UniqueValueAttribute;

@Entity
@Table(name = "usuario")
@UniqueValueAttribute(attribute = "nomeUsuario", service = NomeUsuarioUnicoService.class, message = "Já existe esse nome de usuário cadastrado")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 7562368353372595992L;
	
	@Id
	@SequenceGenerator(name="gerador55", sequenceName="usuario_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador55", strategy=GenerationType.SEQUENCE)
	private Long codigo;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@Email(message = "Email inválido")
	@NotBlank(message = "O email é obrigatório")
	private String email;

	@NotBlank(message = "A senha é obrigatória")
	private String senha;

	@Column(name = "nome_usuario")
	@NotBlank(message = "O nome de usuário é obrigatório")
	private String nomeUsuario;

	@Column(name = "data_nascimento")
	@NotNull(message = "A data de nascimento é obrigatória")
	private LocalDate dataNascimento;
	
	@OneToOne
	@JoinColumn(name = "codigo_papel")
	private Papel papel;

	@ManyToMany
    private List<Jogo> jogosVotados = new ArrayList<>();
    
	public List<Jogo> getJogosVotados(){
        return jogosVotados;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Papel getPapel() {
		return papel;
	}
	public void setPapel(Papel papel) {
		this.papel = papel;
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", nomeUsuario=" + nomeUsuario + ", dataNascimento=" + dataNascimento + ", papel=" + papel + "]";
	}

	
	

	
}
