package co.edu.cue.parcue.model.dto;

import co.edu.cue.parcue.model.entity.Rol;
import co.edu.cue.parcue.model.entity.Tercero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	private Long id;
	private String usuario;
	private String pass;
	private Rol rol;
	private Tercero tercero;
	private Boolean estado;
}
