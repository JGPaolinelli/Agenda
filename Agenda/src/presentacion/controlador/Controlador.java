package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		this.vista.getBtnEditarLocalidad().addActionListener(this);
		this.vista.getBtnEditarTipoDeContacto().addActionListener(this);
		this.vista.getBtnCerrarAgenda().addActionListener(this);
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
			Object[] fila = { persona.getNombre(), persona.getTelefono(), persona.getEmail(),
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
			this.llenarTablaLocalidades();
			llenarTablaTipoDeContactos();
		} else if (e.getSource() == this.vista.getBtnReporte()) {
			ReporteAgenda reporte = new ReporteAgenda(
					Herramientas.agregarSignoZodiaco(agenda.obtenerPersonasOrdenadasPorFechaNacimiento()));
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
					if (domicilio.getIdLocalidad() == this.ventanaPersona.getComboBoxLocalidades().getItemAt(i)
							.getIdLocalidad()) {
						this.ventanaPersona.getComboBoxLocalidades().setSelectedIndex(i);
					}
				}

				for (int i = 0; i < this.ventanaPersona.getComboBoxTipoDeContacto().getItemCount(); i++) {
					if (persona.getIdTipoDeContacto() == this.ventanaPersona.getComboBoxTipoDeContacto().getItemAt(i)
							.getIdTipoDeContacto()) {
						this.ventanaPersona.getComboBoxTipoDeContacto().setSelectedIndex(i);
					}
				}

			}

		} else if (e.getSource() == this.vista.getBtnAgregarLocalidad()) {
			this.ventanaLocalidad = new VentanaLocalidad(this);

		} else if (e.getSource() == this.vista.getBtnAgregarTipoDeContacto()) {
			this.ventanaTipoDeContacto = new VentanaTipoDeContacto(this);

		} else if (e.getSource() == this.vista.getBtnBorrarTipoDeContacto()) {
			int[] filas_seleccionadas = this.vista.getTablaTipoDeContactos().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				if (!this.agenda.estaUsadoTipoDeContacto(this.tipoDeContactosEnTabla.get(fila))) {
					this.agenda.borrartipoDeContacto(this.tipoDeContactosEnTabla.get(fila));
				} else {
					JOptionPane.showMessageDialog(null, "EL TIPO DE CONTACTO QUE SELECCIONO ESTA EN USO",
							"TIPO DE CONTACTO", JOptionPane.WARNING_MESSAGE);
				}
			}
			this.llenarTabla();
			this.llenarTablaLocalidades();
			llenarTablaTipoDeContactos();
		} else if (e.getSource() == this.vista.getBtnBorrarLocalidad()) {
			int[] filas_seleccionadas = this.vista.getTablaLocalidades().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				if (!this.agenda.estaUsadaLocalidad(this.localidadesEnTabla.get(fila))) {
					this.agenda.borrarLocalidad(this.localidadesEnTabla.get(fila));
				} else {
					JOptionPane.showMessageDialog(null, "LA LOCALIDAD SELECCIONADA ESTA EN USO", "LOCALIDAD",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			this.llenarTabla();
			this.llenarTablaLocalidades();
			llenarTablaTipoDeContactos();
		} else if (e.getSource() == this.vista.getBtnEditarLocalidad()) {
			int[] filas_seleccionadas = this.vista.getTablaLocalidades().getSelectedRows();
			if (filas_seleccionadas.length == 0) {
				JOptionPane.showMessageDialog(null, "SELECCIONA UNA LOCALIDAD ANTES DE EDITARLA", "ALERTA",
						JOptionPane.WARNING_MESSAGE);
			} else {
				LocalidadDTO localidad = null;
				for (int fila : filas_seleccionadas) {
					localidad = this.localidadesEnTabla.get(fila);
				}
				this.ventanaLocalidad = new VentanaLocalidad(this);
				this.ventanaLocalidad.getTextId().setText(localidad.getIdLocalidad() + "");
				this.ventanaLocalidad.getTxtLocalidad().setText(localidad.getLocalidad());
			}
		} else if (e.getSource() == this.vista.getBtnEditarTipoDeContacto()) {
			int[] filas_seleccionadas = this.vista.getTablaTipoDeContactos().getSelectedRows();
			if (filas_seleccionadas.length == 0) {
				JOptionPane.showMessageDialog(null, "SELECCIONA UN TIPO DE CONTACTO ANTES DE EDITARLO", "ALERTA",
						JOptionPane.WARNING_MESSAGE);
			} else {
				TipoDeContactoDTO tipoDeContacto = null;
				for (int fila : filas_seleccionadas) {
					tipoDeContacto = this.tipoDeContactosEnTabla.get(fila);
				}
				this.ventanaTipoDeContacto = new VentanaTipoDeContacto(this);
				this.ventanaTipoDeContacto.getTxtId().setText(tipoDeContacto.getIdTipoDeContacto() + "");
				this.ventanaTipoDeContacto.getTxtTipoDeContacto().setText(tipoDeContacto.getTipoDeContacto());
			}
		} else if (e.getSource() == this.vista.getBtnCerrarAgenda()) {
			this.vista.cerrarAgenda();
		}

		if (this.ventanaPersona != null && e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
			String idTxt = this.ventanaPersona.getTxtIdPersona().getText();
			if (idTxt.equals("")) {
				agregarPersona();
			} else {
				editarPersona();
			}

		} else if (this.ventanaLocalidad != null && e.getSource() == this.ventanaLocalidad.getBtnAgregarLocalidad()) {
			if (this.ventanaLocalidad.getTxtLocalidad().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "INGRESA UN NOMBRE DE LOCALIDAD", "LOCALIDAD",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (this.ventanaLocalidad.getTextId().getText().equals("")) {
					LocalidadDTO localidad = new LocalidadDTO(0, this.ventanaLocalidad.getTxtLocalidad().getText());
					this.agenda.agregarLocalidad(localidad);
					this.ventanaLocalidad.dispose();
				} else {
					LocalidadDTO localidad = new LocalidadDTO(
							Long.parseLong(this.ventanaLocalidad.getTextId().getText()),
							this.ventanaLocalidad.getTxtLocalidad().getText());
					this.agenda.actualizarLocalidad(localidad);
					this.ventanaLocalidad.dispose();
				}
				this.llenarTabla();
				this.llenarTablaLocalidades();
				llenarTablaTipoDeContactos();
			}
		} else if (this.ventanaTipoDeContacto != null
				&& e.getSource() == this.ventanaTipoDeContacto.getBtnAgregarTipoDeContacto()) {
			if (this.ventanaTipoDeContacto.getTxtTipoDeContacto().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "INGRESA UN TIPO DE CONTACTO", "TIPO DE CONTACTO",
						JOptionPane.WARNING_MESSAGE);
			} else {

				if (this.ventanaTipoDeContacto.getTxtId().getText().equals("")) {
					TipoDeContactoDTO tipoDeContacto = new TipoDeContactoDTO(0,
							this.ventanaTipoDeContacto.getTxtTipoDeContacto().getText());
					this.agenda.agregarTipoDeContacto(tipoDeContacto);
					this.ventanaTipoDeContacto.dispose();
				} else {
					TipoDeContactoDTO tipoDeContacto = new TipoDeContactoDTO(
							Long.parseLong(this.ventanaTipoDeContacto.getTxtId().getText()),
							this.ventanaTipoDeContacto.getTxtTipoDeContacto().getText());
					this.agenda.actualizarTipoDeContacto(tipoDeContacto);
					this.ventanaTipoDeContacto.dispose();
				}
			}
			this.llenarTabla();
			this.llenarTablaLocalidades();
			llenarTablaTipoDeContactos();
		}

	}

	private void editarPersona() {
		try {
			if (this.ventanaPersona.getTxtTelefono().getText().length() == 0
					|| this.ventanaPersona.getTxtNombre().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios para la persona", "Persona",
						JOptionPane.WARNING_MESSAGE);
			} else if (this.ventanaPersona.getTxtFechaDeNacimiento().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Ingresa la Fecha de Nacimiento", "Persona",
						JOptionPane.WARNING_MESSAGE);
			} else if (this.ventanaPersona.getTxtCalle().getText().length() == 0
					|| this.ventanaPersona.getTxtAltura().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios para el domicilio",
						"Domicilio", JOptionPane.WARNING_MESSAGE);
			} else if (this.ventanaPersona.getTxtEmail().getText().length() != 0
					&& validarMail(this.ventanaPersona.getTxtEmail().getText()) == false) {
				JOptionPane.showMessageDialog(null, "Ingresa un correo electronico valido", "Email",
						JOptionPane.WARNING_MESSAGE);
			} else {
				String fechaString = this.ventanaPersona.getTxtFechaDeNacimiento().getText();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = format.parse(fechaString);
				@SuppressWarnings("deprecation")
				java.sql.Date sqlDate = new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());

				LocalidadDTO localidad = (LocalidadDTO) this.ventanaPersona.getComboBoxLocalidades().getSelectedItem();
				TipoDeContactoDTO tipoDeContacto = (TipoDeContactoDTO) this.ventanaPersona.getComboBoxTipoDeContacto()
						.getSelectedItem();
				DomicilioDTO domicilio;
				if (this.ventanaPersona.getTxtPiso().getText().length() == 0
						&& this.ventanaPersona.getTxtDepartamento().getText().length() == 0) {
					domicilio = new DomicilioDTO(Long.parseLong(this.ventanaPersona.getTxtIdDomicilio().getText()),
							this.ventanaPersona.getTxtCalle().getText(),
							Integer.parseInt(this.ventanaPersona.getTxtAltura().getText()), localidad.getIdLocalidad());
					this.agenda.actualizarDomicilio(domicilio);
				} else {
					domicilio = new DomicilioDTO(Long.parseLong(this.ventanaPersona.getTxtIdDomicilio().getText()),
							this.ventanaPersona.getTxtCalle().getText(),
							Integer.parseInt(this.ventanaPersona.getTxtAltura().getText()),
							Integer.parseInt(this.ventanaPersona.getTxtPiso().getText()),
							Integer.parseInt(this.ventanaPersona.getTxtDepartamento().getText()),
							localidad.getIdLocalidad());
					this.agenda.actualizarDomicilio(domicilio);
				}

				PersonaDTO persona = new PersonaDTO(Long.parseLong(this.ventanaPersona.getTxtIdPersona().getText()),
						this.ventanaPersona.getTxtNombre().getText(), this.ventanaPersona.getTxtTelefono().getText(),
						this.ventanaPersona.getTxtEmail().getText(), sqlDate, domicilio.getIdDomicilio(),
						tipoDeContacto.getIdTipoDeContacto());
				this.agenda.actualizarrPersona(persona);
				this.llenarTabla();
				this.ventanaPersona.dispose();
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Formato de Fecha Invalido", "Fecha de Naciminento",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void agregarPersona() {
		try {
			if (this.ventanaPersona.getTxtTelefono().getText().length() == 0
					|| this.ventanaPersona.getTxtNombre().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios para la persona", "Persona",
						JOptionPane.WARNING_MESSAGE);
			} else if (this.ventanaPersona.getTxtFechaDeNacimiento().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Ingresa la Fecha de Nacimiento", "Persona",
						JOptionPane.WARNING_MESSAGE);
			} else if (this.ventanaPersona.getTxtCalle().getText().length() == 0
					|| this.ventanaPersona.getTxtAltura().getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios para el domicilio",
						"Domicilio", JOptionPane.WARNING_MESSAGE);
			} else if (this.ventanaPersona.getTxtEmail().getText().length() != 0
					&& validarMail(this.ventanaPersona.getTxtEmail().getText()) == false) {
				JOptionPane.showMessageDialog(null, "Ingresa un correo electronico valido", "Email",
						JOptionPane.WARNING_MESSAGE);
			} else {
				String fechaString = this.ventanaPersona.getTxtFechaDeNacimiento().getText();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = format.parse(fechaString);
				@SuppressWarnings("deprecation")
				java.sql.Date sqlDate = new java.sql.Date(date.getYear(), date.getMonth(), date.getDay());
				LocalidadDTO localidad = (LocalidadDTO) this.ventanaPersona.getComboBoxLocalidades().getSelectedItem();
				TipoDeContactoDTO tipoDeContacto = (TipoDeContactoDTO) this.ventanaPersona.getComboBoxTipoDeContacto()
						.getSelectedItem();

				if (this.ventanaPersona.getTxtPiso().getText().length() == 0
						&& this.ventanaPersona.getTxtDepartamento().getText().length() == 0) {
					DomicilioDTO domicilio = new DomicilioDTO(0, this.ventanaPersona.getTxtCalle().getText(),
							Integer.parseInt(this.ventanaPersona.getTxtAltura().getText()), localidad.getIdLocalidad());
					this.agenda.agregarDomicilio(domicilio);
				} else {
					DomicilioDTO domicilio = new DomicilioDTO(0, this.ventanaPersona.getTxtCalle().getText(),
							Integer.parseInt(this.ventanaPersona.getTxtAltura().getText()),
							Integer.parseInt(this.ventanaPersona.getTxtPiso().getText()),
							Integer.parseInt(this.ventanaPersona.getTxtDepartamento().getText()),
							localidad.getIdLocalidad());
					this.agenda.agregarDomicilio(domicilio);
				}
				int idDomicilio = agenda.idUltimoDomicilioAgregado();
				PersonaDTO persona = new PersonaDTO(0, this.ventanaPersona.getTxtNombre().getText(),
						this.ventanaPersona.getTxtTelefono().getText(), this.ventanaPersona.getTxtEmail().getText(),
						sqlDate, idDomicilio, tipoDeContacto.getIdTipoDeContacto());
				this.agenda.agregarPersona(persona);
				this.llenarTabla();
				this.ventanaPersona.dispose();
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Formato de Fecha Invalido", "Fecha de Naciminento",
					JOptionPane.WARNING_MESSAGE);
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

	private boolean validarMail(String mail) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(mail);
		return mather.find() == true;
	}

}
