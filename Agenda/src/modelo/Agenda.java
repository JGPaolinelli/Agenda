package modelo;

import java.util.List;

import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoDeContactoDAO;

public class Agenda {
	private DomicilioDAO domicilio;
	private LocalidadDAO localidad;
	private PersonaDAO persona;
	private TipoDeContactoDAO tipoDeContacto;

	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.domicilio = metodo_persistencia.createDomicilioDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.persona = metodo_persistencia.createPersonaDAO();
		this.tipoDeContacto = metodo_persistencia.createTipoDeContactoDAO();
	}
	
	//******************************************************************//
	public void agregarDomicilio(DomicilioDTO nuevoDomicilio) {
		this.domicilio.insert(nuevoDomicilio);
	}

	public void borrarDomicilio(DomicilioDTO domicilio_a_eliminar) {
		this.domicilio.delete(domicilio_a_eliminar);
	}
	
	public void actualizarDomicilio(DomicilioDTO domicilio_a_actualizar) {
		this.domicilio.update(domicilio_a_actualizar);
	}

	public int idUltimoDomicilioAgregado() {
		return this.domicilio.max();
	}
	
	public DomicilioDTO obtenerDomicilioPorId(long idDomicilio) {
		return this.domicilio.getDomicilioById(idDomicilio);
	}
	
	public List<DomicilioDTO> obtenerDomicilios() {
		return this.domicilio.readAll();
	}
	//******************************************************************//
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad) {
		this.localidad.insert(nuevaLocalidad);
	}

	public void borrarLocalidad(LocalidadDTO localidad_a_eliminar) {
		this.localidad.delete(localidad_a_eliminar);
	}
	
	public void actualizarLocalidad(LocalidadDTO localidad_a_actualizar) {
		this.localidad.update(localidad_a_actualizar);
	}

	public List<LocalidadDTO> obtenerLocalidades() {
		return this.localidad.readAll();
	}
	//******************************************************************//
	public void agregarPersona(PersonaDTO nuevaPersona) {
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		this.persona.delete(persona_a_eliminar);
	}
	
	public void actualizarrPersona(PersonaDTO personaParaEditar) {
		this.persona.update(personaParaEditar);
	}

	public List<PersonaDTO> obtenerPersonas() {
		return this.persona.readAll();
	}
	
	public List<PersonaDTO> obtenerPersonasOrdenadasPorFechaNacimiento() {
		return this.persona.readAllByDate();
	}
	
	//******************************************************************//
	public void agregarTipoDeContacto(TipoDeContactoDTO nuevoTipoDeContacto) {
		this.tipoDeContacto.insert(nuevoTipoDeContacto);
	}

	public void borrartipoDeContacto(TipoDeContactoDTO tipoDeContacto_a_eliminar) {
		this.tipoDeContacto.delete(tipoDeContacto_a_eliminar);
	}
	
	public void actualizarTipoDeContacto(TipoDeContactoDTO tipoDeContactor_a_actualizar) {
		this.tipoDeContacto.update(tipoDeContactor_a_actualizar);
	}

	public List<TipoDeContactoDTO> obtenerTipoDeContactos() {
		return this.tipoDeContacto.readAll();
	}

}
