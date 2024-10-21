package co.edu.cue.parcue.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.cue.parcue.model.dto.LoginDTO;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginRequest) {
		try {
			// Autenticar las credenciales del usuario
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			// Si la autenticación es exitosa, guarda la autenticación en el contexto de
			// seguridad
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Retornar respuesta de éxito
			Map<String, String> response = new HashMap<>();
			response.put("message", "Login successful");

			// Retornar la respuesta como JSON
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException ex) {
			// Si las credenciales no son válidas, devuelve un estado 401 con un mensaje de
			// error en JSON
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "Credenciales no validas");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
	}
}
