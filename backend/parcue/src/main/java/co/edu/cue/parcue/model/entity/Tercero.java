package co.edu.cue.parcue.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "parcue", indexes = { @Index(columnList = "id", name = "tercero_id_hidx"),
		@Index(columnList = "numIdentificacion", name = "tercero_num_id_hidx") })
public class Tercero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "tercero")
	@JsonBackReference
	private Usuario usuario;

	@Column(unique = true, length = 50, nullable = false)
	private String numIdentificacion;

	@Column(length = 200, nullable = false)
	private String nombres;

	@Column(length = 200, nullable = false)
	private String apellidos;

	@Column(length = 10, nullable = true)
	private String telefono;

	@Column(length = 200, nullable = false)
	private String correo;

	@Column(nullable = false)
	private Boolean estado;

	@PrePersist
	void PrePersist() {
		estado = estado == null ? true : estado;
	}
}
