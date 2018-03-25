package dto;

import java.sql.Date;

public class PersonaDTO {
	
	private long idPersona;
	private String nombre;
	private String telefono;
	private String email;
	private Date fechaDeNacimiento;
	private long idDomicilio;
	private long idTipoDeContacto;
	
	public PersonaDTO(long idPersona, String nombre, String telefono, String email, Date fechaDeNacimiento,
			long idDomicilio, long idTipoDeContacto) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.idDomicilio = idDomicilio;
		this.idTipoDeContacto = idTipoDeContacto;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public long getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	public long getIdTipoDeContacto() {
		return idTipoDeContacto;
	}

	public void setIdTipoDeContacto(long idTipoDeContacto) {
		this.idTipoDeContacto = idTipoDeContacto;
	}
	

}
