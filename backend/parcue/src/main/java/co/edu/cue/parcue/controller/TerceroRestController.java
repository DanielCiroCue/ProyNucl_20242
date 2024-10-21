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

import co.edu.cue.parcue.model.dto.TerceroDTO;
import co.edu.cue.parcue.model.service.impl.TerceroService;

@RestController
@RequestMapping("/api/tercero")
public class TerceroRestController {

	@Autowired
	private TerceroService terceroService;

	@GetMapping("/buscarTerceros")
	public ResponseEntity<List<TerceroDTO>> buscarTerceros() {
		return ResponseEntity.ok(terceroService.buscarTerceros());
	}

	@PostMapping("/crearTercero")
	public ResponseEntity<TerceroDTO> crearTercero(@RequestBody TerceroDTO terceroIn) {
		return ResponseEntity.ok(terceroService.crearTercero(terceroIn));
	}

	@PutMapping("/actualizarTercero")
	public ResponseEntity<TerceroDTO> actualizarTercero(@RequestBody TerceroDTO terceroIn) {
		return ResponseEntity.ok(terceroService.actualizarTercero(terceroIn));
	}
}
