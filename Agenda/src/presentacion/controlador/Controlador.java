package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoDeContacto;
import presentacion.vista.Vista;

public class Controlador implements ActionListener {

	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private List<LocalidadDTO> localidadesEnTabla;
	private List<TipoDeContactoDTO> tipoDeContactosEnTabla;
	private VentanaPersona ventanaPersona;
	private VentanaLocalidad ventanaLocalidad;
	private VentanaTipoDeContacto ventanaTipoDeContacto;
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregarPersona().addActionListener(this);
		this.vista.getBtnAgregarTipoDeContacto().addActionListener(this);
		this.vista.getBtnAgregarLocalidad().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.vista.getBtnBorrarLocalidad().addActionListener(this);
		this.vista.getBtnBorrarTipoDeContacto().addActionListener(this);
		this.agenda = agenda;
		this.personas_en_tabla = null;
	}

	public void inicializar() {
		this.llenarTabla();
		this.llenarTablaLocalidades();
		llenarTablaTipoDeContactos();
		this.vista.show();
	}

	private void llenarTabla() {
		this.vista.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.vista.getModelPersonas().setColumnCount(0);
		this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());

		this.personas_en_tabla = agenda.obtenerPersonas();
		for (int i = 0; i < this.personas_en_tabla.size(); i++) {
			PersonaDTO persona = this.personas_en_tabla.get(i);
			DomicilioDTO domicilio = agenda.obtenerDomicilioPorId(persona.getIdDomicilio());
			TipoDeContactoDTO tipoDeContacto = null;
			for (TipoDeContactoDTO tipoDeContactoDTO : agenda.obtenerTipoDeContactos()) {
				if (tipoDeContactoDTO.getIdTipoDeContacto() == persona.getIdTipoDeContacto()) {
					tipoDeContacto = tipoDeContactoDTO;
				}
			}
			Object[] fila = {persona.getNombre(), persona.getTelefono(), persona.getEmail(),
					persona.getFechaDeNacimiento(), domicilio.getCalle() + " - " + domicilio.getAltura(),
					tipoDeContacto.getTipoDeContacto() };
			this.vista.getModelPersonas().addRow(fila);
		}
	}

	private void llenarTablaLocalidades() {
		this.vista.getModelLocalidades().setRowCount(0); // Para vaciar la tabla
		this.vista.getModelLocalidades().setColumnCount(0);
		this.vista.getModelLocalidades().setColumnIdentifiers(this.vista.getNombreColumnaLocalidades());

		this.localidadesEnTabla = agenda.obtenerLocalidades();
		for (int i = 0; i < this.localidadesEnTabla.size(); i++) {
			LocalidadDTO localidad = this.localidadesEnTabla.get(i);
			Object[] fila = { localidad.getLocalidad() };
			this.vista.getModelLocalidades().addRow(fila);
		}
	}

	private void llenarTablaTipoDeContactos() {
		this.vista.getModelTipoDeContactos().setRowCount(0); // Para vaciar la tabla
		this.vista.getModelTipoDeContactos().setColumnCount(0);
		this.vista.getModelTipoDeContactos().setColumnIdentifiers(this.vista.getNombreColumnaTipoDeContactos());

		this.tipoDeContactosEnTabla = agenda.obtenerTipoDeContactos();
		for (int i = 0; i < this.tipoDeContactosEnTabla.size(); i++) {
			TipoDeContactoDTO tipoDeContacto = this.tipoDeContactosEnTabla.get(i);
			Object[] fila = { tipoDeContacto.getTipoDeContacto() };
			this.vista.getModelTipoDeContactos().addRow(fila);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBtnAgregarPersona()) {
			this.ventanaPersona = new VentanaPersona(this);
			setLocalidades();
			setTipoDeContacto();
			
		} else if (e.getSource() == this.vista.getBtnBorrar()) {
			int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			}
			this.llenarTabla();
			
		} else if (e.getSource() == this.vista.getBtnReporte()) {			
			ReporteAgenda reporte = new ReporteAgenda(Herramientas.agregarSignoZodiaco(agenda.obtenerPersonasOrdenadasPorFechaNacimiento()));
			reporte.mostrar();
			
		} else if (e.getSource() == this.vista.getBtnEditar()) {
			int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			if (filas_seleccionadas.length == 0) {
				JOptionPane.showMessageDialog(null, "SELECCIONA UN CONTACTO ANTES DE QUERER EDITARLO", "ALERTA",
						JOptionPane.WARNING_MESSAGE);
			} else {
				PersonaDTO persona = null;
				for (int fila : filas_seleccionadas) {
					persona = this.personas_en_tabla.get(fila);
				}
				DomicilioDTO domicilio = agenda.obtenerDomicilioPorId(persona.getIdDomicilio());
				this.ventanaPersona = new VentanaPersona(this);
				this.ventanaPersona.getTxtIdPersona().setText(Long.toString(persona.getIdPersona()));
				this.ventanaPersona.getTxtNombre().setText(persona.getNombre());
				this.ventanaPersona.getTxtTelefono().setText(persona.getTelefono());
				this.ventanaPersona.getTxtEmail().setText(persona.getEmail());
				this.ventanaPersona.getTxtFechaDeNacimiento().setText(persona.getFechaDeNacimiento().toString());
				this.ventanaPersona.getTxtCalle().setText(domicilio.getCalle());
				this.ventanaPersona.getTxtAltura().setText(Integer.toString(domicilio.getAltura()));
				this.ventanaPersona.getTxtPiso().setText(Integer.toString(domicilio.getPiso()));
				this.ventanaPersona.getTxtDepartamento().setText(Integer.toString(domicilio.getDepartamento()));
				this.ventanaPersona.getTxtIdDomicilio().setText(Long.toString(domicilio.getIdDomicilio()));
				
				setLocalidades();
				setTipoDeContacto();
				
				for (int i = 0; i < this.ventanaPersona.getComboBoxLocalidades().getItemCount(); i++) {
					if (domicilio.getIdLocalidad()==this.ventanaPersona.getComboBoxLocalidades().getItemAt(i).getIdLocalidad()) 
					{
						this.ventanaPersona.getComboBoxLocalidades().setSelectedIndex(i);
					}
				}
				
				for (int i = 0; i < this.ventanaPersona.getComboBoxTipoDeContacto().getItemCount(); i++) {
					if (persona.getIdTipoDeContacto()==this.ventanaPersona.getComboBoxTipoDeContacto().getItemAt(i).getIdTipoDeContacto()) {
						this.ventanaPersona.getComboBoxTipoDeContacto().setSelectedIndex(i);
					}
				}
			
			}
			
		} else if (e.getSource() == this.vista.getBtnAgregarLocalidad()) {
			this.ventanaLocalidad = new VentanaLocalidad(this);
			
		} else if (e.getSource() == this.vista.getBtnAgregarTipoDeContacto()) {
			this.ventanaTipoDeContacto = new VentanaTipoDeContacto(this);
			
		}  else if (e.getSource() == this.vista.getBtnBorrarTipoDeContacto()) {
			int[] filas_seleccionadas = this.vista.getTablaTipoDeContactos().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				this.agenda.borrartipoDeContacto(this.tipoDeContactosEnTabla.get(fila));
			}
			this.llenarTablaTipoDeContactos();
		}  else if (e.getSource() == this.vista.getBtnBorrarLocalidad()) {
			int[] filas_seleccionadas = this.vista.getTablaLocalidades().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				this.agenda.borrarLocalidad(this.localidadesEnTabla.get(fila));
			}
			this.llenarTablaLocalidades();
		}

		if (this.ventanaPersona != null && e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
			String idTxt = this.ventanaPersona.getTxtIdPersona().getText();
			if (idTxt.equals("")) {
				agregarPersona();				
			} else {
				editarPersona();
			}			
			
		} else if (this.ventanaLocalidad != null && e.getSource() == this.ventanaLocalidad.getBtnAgregarLocalidad()) {
			LocalidadDTO localidad = new LocalidadDTO(0, this.ventanaLocalidad.getTxtLocalidad().getText());
			this.agenda.agregarLocalidad(localidad);
			llenarTablaLocalidades();
			this.ventanaLocalidad.dispose();
		} else if (this.ventanaTipoDeContacto != null
				&& e.getSource() == this.ventanaTipoDeContacto.getBtnAgregarTipoDeContacto()) {
			TipoDeContactoDTO tipoDeContacto = new TipoDeContactoDTO(0,
					this.ventanaTipoDeContacto.getTxtTipoDeContacto().getText());
			this.agenda.agregarTipoDeContacto(tipoDeContacto);
			llenarTablaTipoDeContactos();
			this.ventanaTipoDeContacto.dispose();
		}

	}

	private void editarPersona() {
		try {
			String fechaString = this.ventanaPersona.getTxtFechaDeNacimiento().getText();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(fechaString);
			@SuppressWarnings("deprecation")
			java.sql.Date sqlDate = new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());

			LocalidadDTO localidad = (LocalidadDTO) this.ventanaPersona.getComboBoxLocalidades()
					.getSelectedItem();
			TipoDeContactoDTO tipoDeContacto = (TipoDeContactoDTO) this.ventanaPersona
					.getComboBoxTipoDeContacto().getSelectedItem();

			DomicilioDTO domicilio = new DomicilioDTO(
									 Long.parseLong(this.ventanaPersona.getTxtIdDomicilio().getText()), 
									 this.ventanaPersona.getTxtCalle().getText(),
									 Integer.parseInt(this.ventanaPersona.getTxtAltura().getText()),
									 Integer.parseInt(this.ventanaPersona.getTxtPiso().getText()),
									 Integer.parseInt(this.ventanaPersona.getTxtDepartamento().getText()),
									 localidad.getIdLocalidad());
			this.agenda.actualizarDomicilio(domicilio);

			PersonaDTO persona = new PersonaDTO(Long.parseLong(
								 this.ventanaPersona.getTxtIdPersona().getText()), 
								 this.ventanaPersona.getTxtNombre().getText(),
								 this.ventanaPersona.getTxtTelefono().getText(), 
								 this.ventanaPersona.getTxtEmail().getText(),
								 sqlDate, 
								 domicilio.getIdDomicilio(), 
								 tipoDeContacto.getIdTipoDeContacto());
			this.agenda.actualizarrPersona(persona);
			this.llenarTabla();
			this.ventanaPersona.dispose();

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	private void agregarPersona() {
		try {
			String fechaString = this.ventanaPersona.getTxtFechaDeNacimiento().getText();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(fechaString);
			@SuppressWarnings("deprecation")
			java.sql.Date sqlDate = new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());

			LocalidadDTO localidad = (LocalidadDTO) this.ventanaPersona.getComboBoxLocalidades().getSelectedItem();
			TipoDeContactoDTO tipoDeContacto = (TipoDeContactoDTO) this.ventanaPersona.getComboBoxTipoDeContacto()
					.getSelectedItem();

			DomicilioDTO domicilio = new DomicilioDTO(0, this.ventanaPersona.getTxtCalle().getText(),
					Integer.parseInt(this.ventanaPersona.getTxtAltura().getText()),
					Integer.parseInt(this.ventanaPersona.getTxtPiso().getText()),
					Integer.parseInt(this.ventanaPersona.getTxtDepartamento().getText()),
					localidad.getIdLocalidad());
			this.agenda.agregarDomicilio(domicilio);
			int idDomicilio = agenda.idUltimoDomicilioAgregado();

			PersonaDTO persona = new PersonaDTO(0, this.ventanaPersona.getTxtNombre().getText(),
					this.ventanaPersona.getTxtTelefono().getText(), this.ventanaPersona.getTxtEmail().getText(),
					sqlDate, idDomicilio, tipoDeContacto.getIdTipoDeContacto());
			this.agenda.agregarPersona(persona);
			this.llenarTabla();
			this.ventanaPersona.dispose();

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	private void setLocalidades() {
		List<LocalidadDTO> localidades = agenda.obtenerLocalidades();
		for (LocalidadDTO localidadDTO : localidades) {
			this.ventanaPersona.getComboBoxLocalidades().addItem(localidadDTO);
		}
	}

	private void setTipoDeContacto() {
		List<TipoDeContactoDTO> tiposDeContactos = agenda.obtenerTipoDeContactos();
		for (TipoDeContactoDTO tipoDeContactoDTO : tiposDeContactos) {
			this.ventanaPersona.getComboBoxTipoDeContacto().addItem(tipoDeContactoDTO);
		}
	}

}
