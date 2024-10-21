package co.edu.cue.parcue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.cue.parcue.model.dto.UsuarioDTO;
import co.edu.cue.parcue.model.service.impl.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping("/buscarUsuarios")
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarios() {
		return ResponseEntity.ok(usuarioService.buscarUsuarios());
	}
	
	@PostMapping("/crearUsuario")
	public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioIn) {
		return ResponseEntity.ok(usuarioService.crearUsuario(usuarioIn));
	}
	
	@PutMapping("/actualizarUsuario")
	public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioIn) {
		return ResponseEntity.ok(usuarioService.actualizarUsuario(usuarioIn));
	}
}
