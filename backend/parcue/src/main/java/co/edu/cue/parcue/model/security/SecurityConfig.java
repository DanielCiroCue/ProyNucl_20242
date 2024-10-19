package co.edu.cue.parcue.model.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()) // Desactivar CSRF para peticiones REST
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/api/login", "/api/usuario/**", "/api/tercero/**").permitAll()
						//PETICIONES MASTER
//						.requestMatchers("/api/tercero/**").hasRole("MASTER")
						//PETICIONES ADMIN
//						.requestMatchers("/api/tercero/**").hasRole("ADMIN")
						//PETICIONES USER
//						.requestMatchers("/api/tercero/**").hasRole("USER")
						//DEMAS
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).userDetailsService(userDetailsService).build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
