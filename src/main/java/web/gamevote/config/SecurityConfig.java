package web.gamevote.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(configurer -> configurer
				// Qualquer um pode fazer requisições para essas URLs
				.requestMatchers("/css/**", "/js/**", "/images/**", "/", "/index.html", "/usuarios/cadastrar").permitAll()
				// Um usuário autenticado e com o papel ADMIN pode fazer requisições para essas URLs
				.requestMatchers("/vacinas/cadastrar").hasRole("ADMIN")
				//.requestMatchers("/usuarios/cadastrar").hasRole("ADMIN")
				// .requestMatchers("URL").hasAnyRole("ADMIN", "USUARIO")
				// Todas as outras requisições exigem um usuário autenticado
				.anyRequest().permitAll()
			)
			.formLogin(form -> form
                // Uma página de login customizada
				.loginPage("/login")
				// Define a URL para o caso de falha no login
				//.failureUrl("/login-error")
				// Define a URL para o caso de sucesso no login se nenhuma URL segura foi acessada anteriormente ou solicitada
				.defaultSuccessUrl("/")
				.permitAll()
			)
            .logout(logout -> logout
				.permitAll()
				// Para fazer logout (já é configurado automaticamente para a URL /logout)
				// Só está habilitado aqui para mudarmos a URL de sucesso do logout
				// Define a URL para o caso do usuário fazer logout. O padrão é /login
				.logoutSuccessUrl("/")
			);
		return http.build();
	}

	@Bean
 	public UserDetailsService userDetailsService(DataSource dataSource) {    	
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("select nome_usuario, senha, ativo "
			  						+ "from usuario "
			  						+ "where nome_usuario = ?");
		manager.setAuthoritiesByUsernameQuery(
			  "SELECT tab.nome_usuario , papel.nome FROM"
			+ "(SELECT usuario.nome_usuario , usuario.codigo FROM usuario WHERE nome_usuario = ? ) as tab "
			+ " INNER JOIN usuario_papel ON codigo_usuario = tab.codigo "
			+ " INNER JOIN papel ON codigo_papel = papel.codigo;");
		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	
		String idEnconder = "argon2";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());

		PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idEnconder, encoders);
		return passwordEncoder;
	}

}