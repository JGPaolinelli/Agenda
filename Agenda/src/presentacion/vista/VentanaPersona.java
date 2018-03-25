package presentacion.vista;

import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dto.LocalidadDTO;
import dto.TipoDeContactoDTO;
import presentacion.controlador.Controlador;

public class VentanaPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JFormattedTextField txtFechaDeNacimiento;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JComboBox<LocalidadDTO> comboBoxLocalidades;
	private JComboBox<TipoDeContactoDTO> comboBoxTipoDeContacto;
	private JButton btnAgregarPersona;
	private Controlador controlador;
	private JTextField textIdPersona;
	private JTextField txtIdDomicilio;

	public VentanaPersona(Controlador controlador) {
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 350, 550);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 20, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setBounds(10, 60, 113, 14);
		panel.add(lblTelfono);

		JLabel lblEmail = new JLabel("Correo Electronico");
		lblEmail.setBounds(10, 100, 113, 14);
		panel.add(lblEmail);

		JLabel lblFechaDeNacimiento = new JLabel("F. De Nacimiento");
		lblFechaDeNacimiento.setBounds(10, 140, 113, 14);
		panel.add(lblFechaDeNacimiento);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 180, 113, 14);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 220, 113, 14);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 260, 113, 14);
		panel.add(lblPiso);

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(10, 300, 113, 14);
		panel.add(lblDepartamento);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 340, 113, 14);
		panel.add(lblLocalidad);

		JLabel lblTipoDeContacto = new JLabel("Tipo de Contacto");
		lblTipoDeContacto.setBounds(10, 380, 113, 14);
		panel.add(lblTipoDeContacto);

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 20, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 60, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(133, 100, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		txtFechaDeNacimiento = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
		txtFechaDeNacimiento.setBounds(133, 140, 164, 20);
		panel.add(txtFechaDeNacimiento);
		txtFechaDeNacimiento.setColumns(10);

		txtCalle = new JTextField();
		txtCalle.setBounds(133, 180, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		txtAltura = new JTextField();
		txtAltura.setBounds(133, 220, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		txtPiso = new JTextField();
		txtPiso.setBounds(133, 260, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, 300, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);

		comboBoxLocalidades = new JComboBox<LocalidadDTO>();
		comboBoxLocalidades.setBounds(133, 340, 164, 20);
		panel.add(comboBoxLocalidades);
		
		comboBoxTipoDeContacto = new JComboBox<TipoDeContactoDTO>();
		comboBoxTipoDeContacto.setBounds(133, 380, 164, 20);
		panel.add(comboBoxTipoDeContacto);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(110, 440, 89, 23);
		panel.add(btnAgregarPersona);
		
		textIdPersona = new JTextField();
		textIdPersona.setBounds(209, 441, 88, 20);
		panel.add(textIdPersona);
		textIdPersona.setColumns(10);
		textIdPersona.setVisible(false);
		
		txtIdDomicilio = new JTextField();
		txtIdDomicilio.setBounds(0, 441, 86, 20);
		panel.add(txtIdDomicilio);
		txtIdDomicilio.setColumns(10);
		txtIdDomicilio.setVisible(false);
		

		this.setVisible(true);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JFormattedTextField getTxtFechaDeNacimiento() {
		return txtFechaDeNacimiento;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JTextField getTxtAltura() {
		return txtAltura;
	}

	public JTextField getTxtPiso() {
		return txtPiso;
	}

	public JTextField getTxtDepartamento() {
		return txtDepartamento;
	}
	
	public JComboBox<LocalidadDTO> getComboBoxLocalidades(){
		return comboBoxLocalidades;
	}
	
	public JComboBox<TipoDeContactoDTO> getComboBoxTipoDeContacto(){
		return comboBoxTipoDeContacto;
	}

	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}

	public JTextField getTxtIdPersona() {
		return textIdPersona;
	}

	public void setTxtIdPersona(JTextField textIdPersona) {
		this.textIdPersona = textIdPersona;
	}

	public JTextField getTxtIdDomicilio() {
		return txtIdDomicilio;
	}

	public void setTxtIdDomicilio(JTextField txtIdDomicilio) {
		this.txtIdDomicilio = txtIdDomicilio;
	}
}
