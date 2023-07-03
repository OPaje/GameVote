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

				.requestMatchers("/css/**", "/js/**", "/images/**", "/login", "/cadastrar").permitAll()
				.requestMatchers("/", "/index.html").hasRole("COMUM, ADMIN")
                .requestMatchers("/jogos/cadastrar").hasRole("ADMIN")
				.requestMatchers("/usuarios/cadastrar").hasRole("ADMIN")
				
				.anyRequest().permitAll()
			)
			.formLogin(form -> form

                .loginPage("/login")
			
				.defaultSuccessUrl("/")
				.permitAll()
			)
            .logout(logout -> logout
				.permitAll()
				
				.logoutSuccessUrl("/")
			);
		return http.build();
	}

	@Bean
 	public UserDetailsService userDetailsService(DataSource dataSource) {    	
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("select nome_usuario, senha "
			  						+ "from usuario "
			  						+ "where nome = ?");
		manager.setAuthoritiesByUsernameQuery(
			  "SELECT usuario.nome_usuario , papel.nome FROM"
			+ "(SELECT usuario.nome_usuario , usuario.codigo FROM usuario WHERE nome_usuario = ?) as usuario "
			+ " INNER JOIN usuario_papel ON codigo_usuario = usuario.codigo "
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