package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

public class VentanaLocalidad extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField txtLocalidad;
	private JButton btnAgregarLocalidad;
	private Controlador controlador;
	private JTextField textId;
	
	public VentanaLocalidad(Controlador controlador) {
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 180);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 284, 141);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(10, 37, 79, 14);
		panel.add(lblLocalidad);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(99, 34, 164, 20);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.addActionListener(this.controlador);
		btnAgregarLocalidad.setBounds(73, 80, 118, 23);
		panel.add(btnAgregarLocalidad);
		
		textId = new JTextField();
		textId.setBounds(10, 110, 86, 20);
		panel.add(textId);
		textId.setColumns(10);
		textId.setVisible(false);

		this.setVisible(true);
	}

	public JTextField getTxtLocalidad() {
		return txtLocalidad;
	}
	
	public JTextField getTextId() {
		return textId;
	}

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}
}
