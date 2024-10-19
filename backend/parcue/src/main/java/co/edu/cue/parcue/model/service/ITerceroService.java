package co.edu.cue.parcue.model.service;

import java.util.List;

import co.edu.cue.parcue.model.dto.TerceroDTO;

public interface ITerceroService {

	List<TerceroDTO> buscarTerceros();

	TerceroDTO crearTercero(TerceroDTO terceroIn);

	TerceroDTO actualizarTercero(TerceroDTO terceroIn);

}
