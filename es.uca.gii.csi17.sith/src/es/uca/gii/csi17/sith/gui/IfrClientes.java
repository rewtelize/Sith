package es.uca.gii.csi17.sith.gui;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import es.uca.gii.csi17.sith.data.Cliente;
import es.uca.gii.csi17.sith.data.Raza;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class IfrClientes extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtIntroduceCliente;
	private JTable tabResult;
	private Container pnlParent;
	
	/**
	 * Create the frame.
	 */
	public IfrClientes(JFrame frame) {
		pnlParent = frame;
		setResizable(true);
		setClosable(true);
		setTitle("Clientes");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNombre = new JLabel("Nombre");
		getContentPane().add(lblNombre, BorderLayout.NORTH);
		
		txtIntroduceCliente = new JTextField();
		txtIntroduceCliente.setForeground(Color.BLACK);
		txtIntroduceCliente.setBackground(Color.WHITE);
		txtIntroduceCliente.setText("Introduce cliente...");
		txtIntroduceCliente.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(txtIntroduceCliente, BorderLayout.WEST);
		txtIntroduceCliente.setColumns(12);
		
		JComboBox<Raza> cmbRaza = new JComboBox<Raza>();
		cmbRaza.setEditable(true);
		try {
			cmbRaza.setModel(new RazaListModel(Raza.Select()));
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(cmbRaza, "Error en el desplegable.");
		}
		getContentPane().add(cmbRaza, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sNombre = null;
				if (!txtIntroduceCliente.getText().isEmpty() && 
						!txtIntroduceCliente.getText().equals("Introduce cliente..."))
					sNombre = txtIntroduceCliente.getText();
				try {
					if(cmbRaza.getSelectedItem() == null)
						tabResult.setModel(new ClientesTableModel(
								Cliente.Select(null, sNombre, null)));						
					else
						tabResult.setModel(new ClientesTableModel(
								Cliente.Select(null, sNombre, 
									cmbRaza.getSelectedItem().toString())));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(btnNewButton, "Error al buscar");
				}
			}
		});
		
		getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		
		tabResult = new JTable();
		tabResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int iRow = ((JTable)e.getSource()).getSelectedRow();
					Cliente cliente =
					((ClientesTableModel)tabResult.getModel()).getData(iRow);
					if (cliente != null) {
						IfrCliente ifrCliente = new IfrCliente (cliente);
						ifrCliente.setBounds(10, 27, 244, 192);
						pnlParent.add(ifrCliente, 0);
						ifrCliente.setVisible(true);
					}
				}
			}
		});
		getContentPane().add(tabResult, BorderLayout.CENTER);
	}
}
