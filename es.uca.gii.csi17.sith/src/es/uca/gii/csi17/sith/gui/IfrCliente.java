package es.uca.gii.csi17.sith.gui;

import es.uca.gii.csi17.sith.data.Cliente;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import es.uca.gii.csi17.sith.data.Raza;

public class IfrCliente extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JTextField txtNombre;

	private Cliente _cliente = null;
	
	/**
	 * Create the frame.
	 */
	public IfrCliente(Cliente cliente) {
		_cliente = cliente;
		setClosable(true);
		setResizable(true);
		setTitle("Cliente");
		setBounds(100, 100, 450, 299);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][][][][][][][]"));
		
		JComboBox<Raza> cmbRaza = new JComboBox<Raza>();
		try {
			cmbRaza.setModel(new RazaListModel(Raza.Select()));
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(cmbRaza, "Error en el desplegable.");
		}
		getContentPane().add(cmbRaza, "flowx,cell 0 0");
		
		txtNombre = new JTextField();
		getContentPane().add(txtNombre, "cell 0 0,growx");
		txtNombre.setColumns(10);
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{	
				try
				{
					if(_cliente == null)
						_cliente = Cliente.create(txtNombre.getText(), (Raza) cmbRaza.getModel().getSelectedItem());
					else
					{
						_cliente.setNombre(txtNombre.getText());
						_cliente.setRaza((Raza) cmbRaza.getModel().getSelectedItem());
						_cliente.Update();
					}
				}catch(Exception e) {}

			}
		});
		getContentPane().add(butGuardar, "cell 0 8");
	}
}
