package co.edu.cue.parcue.model.service;

import java.util.List;

import co.edu.cue.parcue.model.dto.UsuarioDTO;

public interface IUsuarioService {

	List<UsuarioDTO> buscarUsuarios();

	UsuarioDTO crearUsuario(UsuarioDTO usuarioIn);

	UsuarioDTO actualizarUsuario(UsuarioDTO usuarioIn);
}
