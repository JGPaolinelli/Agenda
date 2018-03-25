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
	
	public VentanaLocalidad(Controlador controlador) {
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 180);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 300, 150);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(10, 20, 113, 14);
		panel.add(lblLocalidad);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(80, 20, 164, 20);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.addActionListener(this.controlador);
		btnAgregarLocalidad.setBounds(80, 80, 89, 23);
		panel.add(btnAgregarLocalidad);

		this.setVisible(true);
	}

	public JTextField getTxtLocalidad() {
		return txtLocalidad;
	}

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}
	
}
