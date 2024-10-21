package co.edu.cue.parcue.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	PasswordEncoder passwordEncoder;
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
		setTercero(usuarioIn);
		setRol(usuarioIn);

		Optional<Usuario> optUsuario = usuarioRepository.findByUsuario(usuarioIn.getUsuario());
		if (optUsuario.isEmpty()) {
			Usuario newUsuario = modelMapper.map(usuarioIn, Usuario.class);
			newUsuario.setPass(passwordEncoder.encode(newUsuario.getPass()));
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
		setTercero(usuarioIn);
		setRol(usuarioIn);

		Optional<Usuario> usuarioNumId = usuarioRepository.findByIdNotAndUsuario(usuarioIn.getId(),
				usuarioIn.getUsuario());
		if (usuarioNumId.isEmpty()) {
			Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioIn.getId());
			if (optUsuario.isPresent()) {
				Usuario usuarioActu = modelMapper.map(usuarioIn, Usuario.class);
				usuarioActu.setPass(passwordEncoder.encode(usuarioActu.getPass()));
				Usuario usuarioCreado = usuarioRepository.save(usuarioActu);
				if (Utilities.isNotNull(usuarioCreado)) {
					return modelMapper.map(usuarioCreado, UsuarioDTO.class);
				}
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No fue posible actualizar el usuario");
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el usuario para actualizar");
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Existe una cuenta con el mismo nombre de usuario");
	}

	private void setTercero(UsuarioDTO usuarioIn) {
		if (Utilities.isNotNull(usuarioIn.getTercero()) && Utilities.isNotNull(usuarioIn.getTercero().getId())) {
			usuarioIn.setTercero(terceroRepository.findById(usuarioIn.getTercero().getId()).get());
		}
	}

	private void setRol(UsuarioDTO usuarioIn) {
		if (Utilities.isNotNull(usuarioIn.getRol()) && Utilities.isNotNull(usuarioIn.getRol().getId())) {
			usuarioIn.setRol(rolRepository.findById(usuarioIn.getRol().getId()).get());
		}
	}
}
