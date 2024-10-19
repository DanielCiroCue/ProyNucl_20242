package co.edu.cue.parcue.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.cue.parcue.model.entity.Tercero;

public interface TerceroRepository extends JpaRepository<Tercero, Long> {

	Optional<Tercero> findByNumIdentificacion(String numIdentificacion);
	
	Optional<Tercero> findByIdNotAndNumIdentificacion(Long id, String numIdentificacion);
}
