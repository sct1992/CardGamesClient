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
import javax.swing.JPasswordField;

/**
 * 
 * @author Santiago
 *
 */
public class DialogInicioSesion extends JDialog implements ActionListener{
	
	
	/**
	 * atributos de interfaz
	 */
	private JTextField txtUserLogin;
	private JPasswordField txtPasswordLogin;
	private JTextField txtUsername;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtPassword;

	private InterfazClient ventana;
	

	/**
	 * Create the dialog.
	 */
	public DialogInicioSesion(InterfazClient interfaz) {
		ventana = interfaz;
		setTitle("Login");
		setResizable(false);
		setBounds(100, 100, 264, 341);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Iniciar Sesion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 238, 109);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 27, 88, 14);
		panel.add(lblUsername);
		
		txtUserLogin = new JTextField();
		txtUserLogin.setBounds(108, 24, 120, 20);
		panel.add(txtUserLogin);
		txtUserLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 52, 46, 14);
		panel.add(lblPassword);
		
		txtPasswordLogin = new JPasswordField();
		txtPasswordLogin.setBounds(108, 49, 120, 17);
		panel.add(txtPasswordLogin);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(139, 75, 89, 23);
		btnLogin.setActionCommand("LOGIN");
		btnLogin.addActionListener(this);
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Crear Cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 131, 238, 168);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setBounds(10, 34, 90, 14);
		panel_1.add(lblUsername_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 59, 90, 14);
		panel_1.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(10, 84, 90, 14);
		panel_1.add(lblCorreo);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(10, 109, 90, 14);
		panel_1.add(lblPassword_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(104, 31, 124, 20);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(104, 56, 124, 20);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(104, 81, 124, 20);
		panel_1.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(104, 106, 124, 20);
		panel_1.add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setBounds(139, 137, 89, 23);
		btnSignIn.setActionCommand("SIGN_IN");
		btnSignIn.addActionListener(this);
		panel_1.add(btnSignIn);
		this.setLocationRelativeTo(ventana);
	}


		
	/**
	 * boton listener
	 */
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		
		if(cmd.equalsIgnoreCase("LOGIN"))
		{
			String user = txtUserLogin.getText();
			String password = txtPasswordLogin.getText();
			
			ventana.login(user,password);
			dispose();
		
		}
		else if (cmd.equalsIgnoreCase("SIGN_IN"))
		{
			String user = txtUsername.getText();
			String password = txtPassword.getText();
			String correo = txtCorreo.getText();
			String nombre = txtNombre.getText();
			
			ventana.signIn(user,password,correo,nombre);
			dispose();
		}
		
	}
}
