package co.edu.cue.parcue.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.cue.parcue.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsuario(String usuario);
	
	Optional<Usuario> findByIdNotAndUsuario(Long id, String usuario);

}
