package co.edu.cue.parcue.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.edu.cue.parcue.model.dto.UsuarioDTO;
import co.edu.cue.parcue.model.entity.Usuario;
import co.edu.cue.parcue.model.repository.RolRepository;
import co.edu.cue.parcue.model.repository.TerceroRepository;
import co.edu.cue.parcue.model.repository.UsuarioRepository;
import co.edu.cue.parcue.model.service.IUsuarioService;
import co.edu.cue.parcue.utilities.Utilities;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	RolRepository rolRepository;
	@Autowired
	TerceroRepository terceroRepository;
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public List<UsuarioDTO> buscarUsuarios() {
		ModelMapper modelMapper = new ModelMapper();
		List<UsuarioDTO> usuarioDTO = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioRepository.findAll();
		usuarios.forEach((usuario) -> {
			usuarioDTO.add(modelMapper.map(usuario, UsuarioDTO.class));
		});
		return usuarioDTO;
	}

	@Override
	public UsuarioDTO crearUsuario(UsuarioDTO usuarioIn) {
		ModelMapper modelMapper = new ModelMapper();
		usuarioIn.setTercero(terceroRepository.findById(usuarioIn.getTercero().getId()).get());
		usuarioIn.setRol(rolRepository.findById(usuarioIn.getRol().getId()).get());
		
		Optional<Usuario> optUsuario = usuarioRepository.findByUsuario(usuarioIn.getUsuario());
		if (optUsuario.isEmpty()) {
			Usuario newUsuario = modelMapper.map(usuarioIn, Usuario.class);
			Usuario usuarioCreado = usuarioRepository.save(newUsuario);
			if (Utilities.isNotNull(usuarioCreado)) {
				return modelMapper.map(usuarioCreado, UsuarioDTO.class);
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No fue posible crear el usuario");
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Existe una cuenta con el mismo nombre de usuario");
	}

	@Override
	public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioIn) {
		ModelMapper modelMapper = new ModelMapper();
		usuarioIn.setTercero(terceroRepository.findById(usuarioIn.getTercero().getId()).get());
		usuarioIn.setRol(rolRepository.findById(usuarioIn.getRol().getId()).get());
		
		Optional<Usuario> usuarioNumId = usuarioRepository.findByIdNotAndUsuario(usuarioIn.getId(),
				usuarioIn.getUsuario());
		if (usuarioNumId.isEmpty()) {
			Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioIn.getId());
			if (optUsuario.isPresent()) {
				Usuario usuarioActu = modelMapper.map(usuarioIn, Usuario.class);
				Usuario usuarioCreado = usuarioRepository.save(usuarioActu);
				if (Utilities.isNotNull(usuarioCreado)) {
					return modelMapper.map(usuarioCreado, UsuarioDTO.class);
				}
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No fue posible actualizar el usuario");
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el usuario para actualizar");
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Existe una cuenta con el mismo nombre de usuario");
	}
}
