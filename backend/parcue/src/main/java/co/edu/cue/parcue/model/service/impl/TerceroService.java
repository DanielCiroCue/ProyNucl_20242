package co.edu.cue.parcue.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.edu.cue.parcue.model.dto.TerceroDTO;
import co.edu.cue.parcue.model.entity.Tercero;
import co.edu.cue.parcue.model.repository.TerceroRepository;
import co.edu.cue.parcue.model.service.ITerceroService;
import co.edu.cue.parcue.utilities.Utilities;

@Service
public class TerceroService implements ITerceroService {

	@Autowired
	TerceroRepository terceroRepository;

	@Override
	public List<TerceroDTO> buscarTerceros() {
		ModelMapper modelMapper = new ModelMapper();
		List<TerceroDTO> tercerosDTO = new ArrayList<TerceroDTO>();
		List<Tercero> terceros = terceroRepository.findAll();
		terceros.forEach((tercero) -> {
			tercerosDTO.add(modelMapper.map(tercero, TerceroDTO.class));
		});
		return tercerosDTO;
	}

	@Override
	public TerceroDTO crearTercero(TerceroDTO terceroIn) {
		ModelMapper modelMapper = new ModelMapper();
		Optional<Tercero> optTercero = terceroRepository.findByNumIdentificacion(terceroIn.getNumIdentificacion());
		if (optTercero.isEmpty()) {
			Tercero newTercero = modelMapper.map(terceroIn, Tercero.class);
			Tercero terceroCreado = terceroRepository.save(newTercero);
			if (Utilities.isNotNull(terceroCreado)) {
				return modelMapper.map(terceroCreado, TerceroDTO.class);
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No fue posible crear el tercero");
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Existe un usuario con el mismo número de identificación");
	}

	@Override
	public TerceroDTO actualizarTercero(TerceroDTO terceroIn) {
		ModelMapper modelMapper = new ModelMapper();
		Optional<Tercero> terceroNumId = terceroRepository.findByIdNotAndNumIdentificacion(terceroIn.getId(),
				terceroIn.getNumIdentificacion());
		if (terceroNumId.isEmpty()) {
			Optional<Tercero> optTercero = terceroRepository.findById(terceroIn.getId());
			if (optTercero.isPresent()) {
				Tercero terceroActu = modelMapper.map(terceroIn, Tercero.class);
				Tercero terceroCreado = terceroRepository.save(terceroActu);
				if (Utilities.isNotNull(terceroCreado)) {
					return modelMapper.map(terceroCreado, TerceroDTO.class);
				}
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No fue posible actualizar el tercero");
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No existe el tercero para actualizar");
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Existe un usuario con el mismo número de identificación");
	}
}
