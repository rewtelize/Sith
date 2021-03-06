package es.uca.gii.csi17.sith.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FrmMain {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmMain() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Sith application");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mitNuevo = new JMenu("Nuevo");
		menuBar.add(mitNuevo);
		
		JMenuItem mitNuevoCliente = new JMenuItem("Cliente");
		mitNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					IfrCliente ifrCliente = new IfrCliente(null);
					ifrCliente.setBounds(10, 27, 244, 192);
					frame.getContentPane().add(ifrCliente);
					ifrCliente.setVisible(true);
				}catch(Exception e1) {}
			}
		});
		mitNuevo.add(mitNuevoCliente);
		
		JMenu mitBuscar = new JMenu("Buscar");
		menuBar.add(mitBuscar);
		
		JMenuItem mitBuscarCliente = new JMenuItem("Cliente");
		mitBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IfrClientes ifrClientes = new IfrClientes(frame);
				ifrClientes.setBounds(12, 28, 244, 192);
				// El segundo parámetro es para que siempre aparezca delante
				frame.getContentPane().add(ifrClientes, 0);
				ifrClientes.setVisible(true);
			}
		});
		mitBuscar.add(mitBuscarCliente);
		frame.getContentPane().setLayout(null);
	}

}
