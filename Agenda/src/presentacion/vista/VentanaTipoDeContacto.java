package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

public class VentanaTipoDeContacto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtTipoDeContacto;
	private JButton btnAgregarTipoDeContacto;
	private Controlador controlador;
	private JTextField textId;

	public VentanaTipoDeContacto(Controlador controlador) {
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 180);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 314, 119);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblTipoDeContacto = new JLabel("Tipo de Contacto:");
		lblTipoDeContacto.setBounds(10, 20, 113, 20);
		panel.add(lblTipoDeContacto);

		txtTipoDeContacto = new JTextField();
		txtTipoDeContacto.setBounds(130, 20, 164, 20);
		panel.add(txtTipoDeContacto);
		txtTipoDeContacto.setColumns(10);

		btnAgregarTipoDeContacto = new JButton("Agregar");
		btnAgregarTipoDeContacto.addActionListener(this.controlador);
		btnAgregarTipoDeContacto.setBounds(86, 63, 113, 23);
		panel.add(btnAgregarTipoDeContacto);
		
		textId = new JTextField();
		textId.setBounds(10, 88, 86, 20);
		panel.add(textId);
		textId.setColumns(10);
		textId.setVisible(false);

		this.setVisible(true);
	}

	public JTextField getTxtTipoDeContacto() {
		return txtTipoDeContacto;
	}
	
	public JTextField getTxtId() {
		return textId;
	}

	public JButton getBtnAgregarTipoDeContacto() {
		return btnAgregarTipoDeContacto;
	}

}
