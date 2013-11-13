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
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import common.User;

/**
 * 
 * @author Santiago
 *
 */
public class DialogAgregarUsuario extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private InterfazClient ventana;
	
	/**
	 * 
	 */
	private JList listUsuarios;
	
	/**
	 * Create the dialog.
	 */
	public DialogAgregarUsuario(InterfazClient v, ArrayList<User> usuarios) {
		ventana = v;
		setTitle("Invitar");
		setBounds(100, 100, 265, 317);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 229, 198);
		getContentPane().add(scrollPane);
		
		listUsuarios = new JList();
		scrollPane.setViewportView(listUsuarios);
		listUsuarios.setListData(usuarios.toArray());
		
		JButton btnInvitar = new JButton("Invitar");
		btnInvitar.setBounds(129, 245, 110, 23);
		getContentPane().add(btnInvitar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 245, 109, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblInvitaAUn = new JLabel("Invita a un amigo a Jugar");
		lblInvitaAUn.setBounds(10, 11, 136, 14);
		getContentPane().add(lblInvitaAUn);
		
		btnCancelar.setActionCommand("CANCELAR");
		btnCancelar.addActionListener(this);
		
		btnInvitar.setActionCommand("INVITAR");
		btnInvitar.addActionListener(this);
		this.setLocationRelativeTo(v);
	}
	
	
	/**
	 * escuchador de botones
	 */
	public void actionPerformed(ActionEvent arg0) {
	
		String cmd = arg0.getActionCommand();
		
		if(cmd.equals("INVITAR"))
		{
			if(listUsuarios.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(this, "no seleccionaste nigun jugador para invitar");
			}
			User usuario = (User)listUsuarios.getSelectedValue();
			
			ventana.agregarUsuario(usuario);
			dispose();
			
		}
		else
		{
			dispose();
		}
		
	}
}
