package co.edu.cue.parcue.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "parcue", indexes = { @Index(columnList = "id", name = "usuario_id_hidx"),
		@Index(columnList = "usuario", name = "usuario_usuario_hidx") })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 50, nullable = false)
	private String usuario;

	@Column(length = 250, nullable = false)
	private String pass;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rol_id", referencedColumnName = "id")
	private Rol rol;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tercero", referencedColumnName = "id")
	@JsonManagedReference
	@JsonIgnore
	private Tercero tercero;

	@Column(nullable = false)
	private Boolean estado;

	@PrePersist
	void PrePersist() {
		estado = estado == null ? true : estado;
	}
}
