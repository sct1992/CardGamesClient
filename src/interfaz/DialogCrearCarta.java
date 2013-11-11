package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DialogCrearCarta extends JDialog implements ActionListener {
	private JTextField txtNombre;
	private JTextField txtLugar;
	private JTextField txtUrl;


	private InterfazClient ventana;
	private JTextArea txtDescripcion;
	private JComboBox cmbCategoria;
	/**
	 * Create the dialog.
	 */
	public DialogCrearCarta(InterfazClient v) {
		ventana= v;
		setTitle("Crear Carta");
		setResizable(false);
		setBounds(100, 100, 290, 286);
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(null, "Crear Carta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(10, 11, 263, 240);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblNombreCarta = new JLabel("Nombre Carta");
					lblNombreCarta.setBounds(10, 25, 116, 14);
					panel_1.add(lblNombreCarta);
				}
				{
					JLabel lblLugar = new JLabel("Lugar");
					lblLugar.setBounds(10, 50, 116, 14);
					panel_1.add(lblLugar);
				}
				{
					JLabel lblUrlImagenweb = new JLabel("Url Imagen (web)");
					lblUrlImagenweb.setBounds(10, 75, 116, 14);
					panel_1.add(lblUrlImagenweb);
				}
				{
					JLabel lblCategoria = new JLabel("Categoria");
					lblCategoria.setBounds(10, 100, 116, 14);
					panel_1.add(lblCategoria);
				}
				{
					JLabel lblDescripcion = new JLabel("Descripcion");
					lblDescripcion.setBounds(10, 125, 116, 14);
					panel_1.add(lblDescripcion);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setBounds(136, 22, 116, 20);
					panel_1.add(txtNombre);
					txtNombre.setColumns(10);
				}
				{
					txtLugar = new JTextField();
					txtLugar.setBounds(136, 47, 116, 20);
					panel_1.add(txtLugar);
					txtLugar.setColumns(10);
				}
				{
					txtUrl = new JTextField();
					txtUrl.setBounds(136, 72, 116, 20);
					panel_1.add(txtUrl);
					txtUrl.setColumns(10);
				}
				
				cmbCategoria = new JComboBox();
				cmbCategoria.setBounds(136, 97, 116, 20);
				cmbCategoria.addItem("Categoria 1");
				cmbCategoria.addItem("Categoria 2");
				cmbCategoria.addItem("Categoria 3");
				cmbCategoria.addItem("Categoria 4");
				cmbCategoria.addItem("Categoria 5");
				panel_1.add(cmbCategoria);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 150, 243, 52);
				panel_1.add(scrollPane);
				
				txtDescripcion = new JTextArea();
				scrollPane.setViewportView(txtDescripcion);
				
				JButton btnCrear = new JButton("Crear");
				btnCrear.setBounds(136, 206, 116, 23);
				panel_1.add(btnCrear);
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBounds(10, 206, 116, 23);
				panel_1.add(btnCancelar);
				
				btnCancelar.setActionCommand("CANCELAR");
				btnCancelar.addActionListener(this);
				
				btnCrear.setActionCommand("CREAR");
				btnCrear.addActionListener(this);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		String cmd = arg0.getActionCommand();
		
		if(cmd.equals("CREAR"))
		{
			String nombre = txtNombre.getText();
			String lugar = txtLugar.getText();
			String url = txtUrl.getText();
			String descripcion = txtDescripcion.getText();
			String categoria = (String)cmbCategoria.getSelectedItem();
			
			ventana.crearCarta(nombre,lugar,categoria,url,descripcion);
			dispose();
		}
		else
		{
			dispose();
		}
		
	}
}
