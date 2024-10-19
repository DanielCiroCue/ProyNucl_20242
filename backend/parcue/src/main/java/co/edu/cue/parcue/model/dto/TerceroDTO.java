package co.edu.cue.parcue.model.dto;

import co.edu.cue.parcue.model.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerceroDTO {
	private Long id;
	private Usuario usuario;
	private String numIdentificacion;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String correo;
	private Boolean estado;
}
