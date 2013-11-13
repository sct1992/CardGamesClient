package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import common.Card;

/**
 * 
 * @author Santiago
 *
 */
public class DialogAgregarWSporCarta extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();

	/**
	 * 
	 */
	private InterfazClient ventana;
	
	/**
	 * 
	 */
	private JList listCarta;

	/**
	 * Create the dialog.
	 */
	public DialogAgregarWSporCarta(InterfazClient v, ArrayList<Card> cartas) {
		ventana=v;
		
		setTitle("Agregar Carta");
		setResizable(false);
		setBounds(100, 100, 257, 315);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblSeleccionaUnaCarta = new JLabel("Quieres a\u00F1adir una carta para invitarla a tus amigos");
		lblSeleccionaUnaCarta.setBounds(10, 11, 205, 14);
		contentPanel.add(lblSeleccionaUnaCarta);
		
		JButton btnCancelar = new JButton("No gracias");
		btnCancelar.setBounds(10, 245, 109, 23);
		contentPanel.add(btnCancelar);
		
		JButton btnAgregar = new JButton("Enviar");
		btnAgregar.setBounds(129, 245, 110, 23);
		contentPanel.add(btnAgregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 231, 199);
		contentPanel.add(scrollPane);
		
		listCarta = new JList();
		scrollPane.setViewportView(listCarta);
		listCarta.setListData(cartas.toArray());
		
		btnCancelar.setActionCommand("CANCELAR");
		btnCancelar.addActionListener(this);
		
		btnAgregar.setActionCommand("AGREGAR");
		btnAgregar.addActionListener(this);
		try
		{
		listCarta.setSelectedIndex(0);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		this.setLocationRelativeTo(v);
	}

	
	/**
	 * escuchador de botones
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		String command = arg0.getActionCommand();
		
		if(command.equals("AGREGAR"))
		{
	
			Card carta = (Card)listCarta.getSelectedValue();
			if(carta==null)
			{
				JOptionPane.showMessageDialog(this, "No seleccionaste ninguna carta");
				dispose();
				return;		
			}
			ventana.crearWorkspacePorCarta(carta.getId());
			dispose();

		}
		else
		{
			ventana.crearWorkspace();
			dispose();
		}
		
		
	}
	

}
