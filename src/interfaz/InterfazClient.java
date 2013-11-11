package interfaz;
/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: readme.txt,v 1.1 2009/09/15 16:09:20 dav-vill Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_elimiGemas
 * Autor: Juan David Villa - 31-ago-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

import User.Game;
import User.IGame;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import common.Card;
import common.User;
import common.Workspace;



/**
 * Es la clase principal de la interfaz
 */
public class InterfazClient extends JFrame implements ActionListener , IListenerPush
{
	
	
    //-----------------------------------------------------------------
    // Programa principal
    //-----------------------------------------------------------------

    /**
     * Ejecuta la aplicación
     * @param args Estos parámetros no se usan.
     */
    public static void main( String[] args )
    {
        InterfazClient ic = new InterfazClient( );
        ic.setVisible( true );
        
    }

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * conexion con el "mundo" (que se conecta con el servidor)
	 */
	private IGame game;
	private JTextField txtChatEnvio;
	private JTextField txtOwner;
	private JTextField txtLugar;
	private JTextField txtCategoria;
	private JButton btnIniciarSesion;
	private JButton btnCerrarSesion;
	private JButton btnSalir;
	private JButton btnCrearCarta;
	private JButton btnMasCarta;
	private JButton btnMenosCarta;
	private JButton btnMenosUser;
	private JButton btnMasUser;
	private JButton btnCrearWorkspace;
	private JButton btnProponer;
	private JButton btnSend;
	private JButton btnVotar;
	private JLabel lblUserName;
	private JLabel lblNombre;
	private JLabel lblCorreo;
	private JLabel lblImagenBaraja;
	private JList listMiBaraja;
	private JList listUsers;
	private JList listMyWorkspaces;
	private JList listProponerCarta;
	private JList listPropuestas;
	private JList listJugadas;
	private JTextArea txtAreaChat;
	private JTextArea txtDescripcionCarta;
	private JLabel lblNotificacionesDelPrograma;
	private JLabel lblIdNombreCarta;
	private JLabel lblImagen;
	private JLabel lblVotosRecibidos;

	private int idActiveWorkspace;
	private JLabel lblNombreCartaWorkspace;
    
    private ArrayList<String> misCartasVotadas;
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa el juego indicando que no se ha cargado la información de ningún archivo
     */
    public InterfazClient( )
    {
    	idActiveWorkspace = -1;
    	game = new Game(this);
    	//CODIGO GENERADO
    	getContentPane().setBackground(Color.WHITE);
        setSize( 867, 565 );
        setResizable(false);
        
        setTitle( "GameCards" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(new TitledBorder(null, "Mi Baraja", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 119, 161, 358);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        lblImagenBaraja = new JLabel("");
        lblImagenBaraja.setForeground(Color.BLACK);
        lblImagenBaraja.setBackground(Color.WHITE);
        lblImagenBaraja.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagenBaraja.setBounds(10, 43, 138, 125);
        panel.add(lblImagenBaraja);
        
        lblIdNombreCarta = new JLabel("Nombre Carta");
        lblIdNombreCarta.setHorizontalAlignment(SwingConstants.CENTER);
        lblIdNombreCarta.setBounds(10, 22, 141, 23);
        panel.add(lblIdNombreCarta);
        
        JLabel lblCartas = new JLabel("Mis Cartas");
        lblCartas.setBounds(10, 181, 89, 14);
        panel.add(lblCartas);
        
        btnMasCarta = new JButton("+");
        btnMasCarta.setBounds(49, 324, 40, 23);
        panel.add(btnMasCarta);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 199, 141, 119);
        panel.add(scrollPane_1);
        
        listMiBaraja = new JList();
        listMiBaraja.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        		cambioListMiBaraja(arg0);	
        	}

		
        });
        scrollPane_1.setViewportView(listMiBaraja);
        
        btnMenosCarta = new JButton("-");
        btnMenosCarta.setBounds(10, 324, 40, 23);
        panel.add(btnMenosCarta);
        
        btnCrearCarta = new JButton("Crear");
        btnCrearCarta.setBounds(89, 324, 62, 23);
        panel.add(btnCrearCarta);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.LIGHT_GRAY);
        panel_1.setBorder(new TitledBorder(null, "Lobby", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(181, 119, 209, 358);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JPanel panel_10 = new JPanel();
        panel_10.setBackground(Color.LIGHT_GRAY);
        panel_10.setBorder(new TitledBorder(null, "Nuevo Workspace", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_10.setBounds(10, 172, 189, 173);
        panel_1.add(panel_10);
        panel_10.setLayout(null);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(10, 21, 169, 118);
        panel_10.add(scrollPane_2);
        
        listUsers = new JList();
        scrollPane_2.setViewportView(listUsers);
        
        btnMenosUser = new JButton("-");
        btnMenosUser.setBounds(10, 142, 40, 23);
        panel_10.add(btnMenosUser);
        
        btnMasUser = new JButton("+");
        btnMasUser.setBounds(58, 142, 40, 23);
        panel_10.add(btnMasUser);
        
        btnCrearWorkspace = new JButton("Crear");
        btnCrearWorkspace.setBounds(108, 142, 71, 23);
        panel_10.add(btnCrearWorkspace);
        
        JPanel panel_11 = new JPanel();
        panel_11.setBackground(Color.LIGHT_GRAY);
        panel_11.setBorder(new TitledBorder(null, "Workspaces Activos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_11.setBounds(10, 22, 189, 150);
        panel_1.add(panel_11);
        panel_11.setLayout(null);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(10, 21, 169, 89);
        panel_11.add(scrollPane_3);
        
        listMyWorkspaces = new JList();
        listMyWorkspaces.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        		cambioWorkspace(arg0);
        	}

		
        });
        scrollPane_3.setViewportView(listMyWorkspaces);
        
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(90, 116, 89, 23);
        panel_11.add(btnSalir);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.LIGHT_GRAY);
        panel_2.setBorder(new TitledBorder(null, "Mi Cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(10, 11, 380, 97);
        getContentPane().add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblUsername = new JLabel("User-Name:");
        lblUsername.setBounds(10, 21, 78, 14);
        panel_2.add(lblUsername);
        
        JLabel lblNombredrg = new JLabel("Nombre:");
        lblNombredrg.setBounds(10, 46, 78, 14);
        panel_2.add(lblNombredrg);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 71, 78, 14);
        panel_2.add(lblEmail);
        
        lblUserName = new JLabel("NA");
        lblUserName.setBounds(82, 21, 142, 14);
        panel_2.add(lblUserName);
        
        lblNombre = new JLabel("NA");
        lblNombre.setBounds(82, 46, 142, 14);
        panel_2.add(lblNombre);
        
        lblCorreo = new JLabel("NA");
        lblCorreo.setBounds(82, 71, 142, 14);
        panel_2.add(lblCorreo);
        
        btnIniciarSesion = new JButton("Iniciar Sesion");
        btnIniciarSesion.setBounds(263, 17, 107, 37);
        panel_2.add(btnIniciarSesion);
        
        btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setBounds(263, 56, 107, 29);
        panel_2.add(btnCerrarSesion);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.LIGHT_GRAY);
        panel_3.setBorder(new TitledBorder(null, "Notifications", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.setBounds(10, 489, 841, 42);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);
        
        lblNotificacionesDelPrograma = new JLabel("Notificaciones del programa");
        lblNotificacionesDelPrograma.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNotificacionesDelPrograma.setBounds(21, 11, 810, 20);
        panel_3.add(lblNotificacionesDelPrograma);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.LIGHT_GRAY);
        panel_4.setBorder(new TitledBorder(null, "Workspace", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_4.setBounds(400, 11, 451, 466);
        getContentPane().add(panel_4);
        panel_4.setLayout(null);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.LIGHT_GRAY);
        panel_5.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_5.setBounds(196, 299, 245, 160);
        panel_4.add(panel_5);
        panel_5.setLayout(null);
        
        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(10, 22, 226, 99);
        panel_5.add(scrollPane_4);
        
        txtAreaChat = new JTextArea();
        txtAreaChat.setEditable(false);
        txtAreaChat.setForeground(Color.BLACK);
        txtAreaChat.setBackground(Color.WHITE);
        scrollPane_4.setViewportView(txtAreaChat);
        
        txtChatEnvio = new JTextField();
        txtChatEnvio.setBounds(10, 126, 146, 23);
        panel_5.add(txtChatEnvio);
        txtChatEnvio.setColumns(10);
        
        btnSend = new JButton("Send");
        btnSend.setBounds(157, 126, 79, 23);
        panel_5.add(btnSend);
        
        JPanel panel_6 = new JPanel();
        panel_6.setBackground(Color.LIGHT_GRAY);
        panel_6.setBorder(new TitledBorder(null, "Proponer Carta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_6.setBounds(10, 299, 183, 160);
        panel_4.add(panel_6);
        panel_6.setLayout(null);
        
        btnProponer = new JButton("Proponer");
        btnProponer.setBounds(94, 126, 79, 23);
        panel_6.add(btnProponer);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 23, 163, 100);
        panel_6.add(scrollPane);
        
        listProponerCarta = new JList();
        listProponerCarta.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        		cambioListProponerCarta();
        	}

	
        });
        scrollPane.setViewportView(listProponerCarta);
        
        JPanel panel_7 = new JPanel();
        panel_7.setBackground(Color.LIGHT_GRAY);
        panel_7.setBorder(new TitledBorder(null, "Carta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_7.setBounds(10, 21, 259, 277);
        panel_4.add(panel_7);
        panel_7.setLayout(null);
        
        JScrollPane scrollPane_5 = new JScrollPane();
        scrollPane_5.setBounds(10, 204, 237, 62);
        panel_7.add(scrollPane_5);
        
        txtDescripcionCarta = new JTextArea();
        txtDescripcionCarta.setBackground(Color.WHITE);
        txtDescripcionCarta.setEditable(false);
        scrollPane_5.setViewportView(txtDescripcionCarta);
        
        lblImagen = new JLabel("");
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setForeground(Color.BLACK);
        lblImagen.setBackground(Color.WHITE);
        lblImagen.setBounds(10, 42, 138, 133);
        panel_7.add(lblImagen);
        
        JLabel label_1 = new JLabel("Descripcion");
        label_1.setBounds(10, 186, 92, 14);
        panel_7.add(label_1);
        
        txtOwner = new JTextField();
        txtOwner.setBackground(Color.WHITE);
        txtOwner.setEditable(false);
        txtOwner.setColumns(10);
        txtOwner.setBounds(158, 139, 89, 20);
        panel_7.add(txtOwner);
        
        txtLugar = new JTextField();
        txtLugar.setBackground(Color.WHITE);
        txtLugar.setEditable(false);
        txtLugar.setColumns(10);
        txtLugar.setBounds(158, 100, 89, 20);
        panel_7.add(txtLugar);
        
        JLabel label_2 = new JLabel("Due\u00F1o");
        label_2.setBounds(158, 125, 86, 14);
        panel_7.add(label_2);
        
        JLabel label_3 = new JLabel("Lugar");
        label_3.setBounds(158, 87, 60, 14);
        panel_7.add(label_3);
        
        txtCategoria = new JTextField();
        txtCategoria.setBackground(Color.WHITE);
        txtCategoria.setEditable(false);
        txtCategoria.setColumns(10);
        txtCategoria.setBounds(158, 62, 89, 20);
        panel_7.add(txtCategoria);
        
        JLabel label_4 = new JLabel("Categoria");
        label_4.setBounds(158, 42, 66, 14);
        panel_7.add(label_4);
        
        lblNombreCartaWorkspace = new JLabel("Nombre Carta");
        lblNombreCartaWorkspace.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombreCartaWorkspace.setBounds(10, 21, 237, 20);
        panel_7.add(lblNombreCartaWorkspace);
        
        lblVotosRecibidos = new JLabel("votos recibidos: 12");
        lblVotosRecibidos.setHorizontalAlignment(SwingConstants.RIGHT);
        lblVotosRecibidos.setBounds(126, 166, 121, 14);
        panel_7.add(lblVotosRecibidos);
        
        JPanel panel_8 = new JPanel();
        panel_8.setBackground(Color.LIGHT_GRAY);
        panel_8.setBorder(new TitledBorder(null, "Jugadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_8.setBounds(279, 21, 162, 124);
        panel_4.add(panel_8);
        panel_8.setLayout(null);
        
        JScrollPane scrollPane_6 = new JScrollPane();
        scrollPane_6.setBounds(10, 22, 142, 91);
        panel_8.add(scrollPane_6);
        
        listJugadas = new JList();
        listJugadas.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        		cambioListJugadas(arg0);
        	}

        });
        scrollPane_6.setViewportView(listJugadas);
        
        JPanel panel_9 = new JPanel();
        panel_9.setBackground(Color.LIGHT_GRAY);
        panel_9.setBorder(new TitledBorder(null, "Propuestas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_9.setBounds(279, 146, 162, 152);
        panel_4.add(panel_9);
        panel_9.setLayout(null);
        
        JScrollPane scrollPane_7 = new JScrollPane();
        scrollPane_7.setBounds(10, 24, 142, 94);
        panel_9.add(scrollPane_7);
        
        listPropuestas = new JList();
        listPropuestas.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        		cambioListPropuestas(arg0);
        	}

	
        });
        scrollPane_7.setViewportView(listPropuestas);
        
        btnVotar = new JButton("votar");
        btnVotar.setBounds(10, 124, 142, 17);
        panel_9.add(btnVotar);
        
        //Codigo 
        
    	btnIniciarSesion.setActionCommand("INICIAR_SESION");
    	btnCerrarSesion.setActionCommand("CERRAR_SESION");
    	btnSalir.setActionCommand("SALIR");
    	btnCrearCarta.setActionCommand("CREAR_CARTA");
    	btnMasCarta.setActionCommand("MAS_CARTA");
    	btnMenosCarta.setActionCommand("MENOS_CARTA");
    	btnMenosUser.setActionCommand("MENOS_USER");
    	btnMasUser.setActionCommand("MAS_USER");
    	btnCrearWorkspace.setActionCommand("CREAR_WORKSPACE");
    	btnProponer.setActionCommand("PROPONER");
    	btnSend.setActionCommand("SEND");
    	btnVotar.setActionCommand("VOTAR");
    	
    	btnIniciarSesion.addActionListener(this);
    	btnCerrarSesion.addActionListener(this);
    	btnSalir.addActionListener(this);
    	btnCrearCarta.addActionListener(this);
    	btnMasCarta.addActionListener(this);
    	btnMenosCarta.addActionListener(this);
    	btnMenosUser.addActionListener(this);
    	btnMasUser.addActionListener(this);
    	btnCrearWorkspace.addActionListener(this);
    	btnProponer.addActionListener(this);
    	btnSend.addActionListener(this);
    	btnVotar.addActionListener(this);
    	
    	interfazModoDesconectado();
    	
       
    }
    
	private void cambioListMiBaraja(ListSelectionEvent arg0) {
		
		actualizarBaraja();
	}
	private void cambioWorkspace(ListSelectionEvent arg0) {
		
		Workspace actual = (Workspace) listMyWorkspaces.getSelectedValue();
		actualizarWorkspace(actual.getId());
		
	}
	private void cambioListPropuestas(ListSelectionEvent arg0) {
		Card actual = (Card) listPropuestas.getSelectedValue();
		actualizarCartaWorkspace(actual);
	}
	private void cambioListJugadas(ListSelectionEvent arg0) {
		Card actual = (Card) listJugadas.getSelectedValue();
		actualizarCartaWorkspace(actual);
		
	}
	private void cambioListProponerCarta() {
		Card actual = (Card) listProponerCarta.getSelectedValue();
		actualizarCartaWorkspace(actual);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		 
		
		String command = arg0.getActionCommand();
		
		if(command.equalsIgnoreCase("INICIAR_SESION"))
		{
			DialogInicioSesion dialogo = new DialogInicioSesion(this);
			dialogo.setVisible(true);
		}
		else if(command.equals("CERRAR_SESION"))
		{
			//TODO
			interfazModoDesconectado();
		}
		else if(command.equals("SALIR"))
		{
			//TODO
		
		}
		else if( command.equals("MAS_CARTA"))
		{
			
			DialogAgregarCarta dialogo = new DialogAgregarCarta(this,game.getCards());
			dialogo.setVisible(true);
		}
		else if( command.equals("MENOS_CARTA"))
		{
			quitarCartaBaraja();
		}
		else if( command.equals("CREAR_CARTA"))
		{
			DialogCrearCarta dialogo = new DialogCrearCarta(this);
			dialogo.setVisible(true);
		}
		else if( command.equals("MAS_USER"))
		{
			DialogAgregarUsuario dialogo = new DialogAgregarUsuario(this, game.getActiveUsers());
			dialogo.setVisible(true);
		}
		else if( command.equals("MENOS_USER"))
		{
			quitarUserJuego();
		}
		else if( command.equals("CREAR_WORKSPACE"))
		{
			crearWorkspace();
		}
		else if( command.equals("CREAR_WORKSPACE"))
		{
			crearWorkspace();
		}
		else if( command.equals("PROPONER"))
		{
			proponerCarta();
		}
		else if( command.equals("VOTAR"))
		{
			votarCarta();
		}
		else if( command.equals("SEND"))
		{
			enviarMensajeChat();
		}
		
	}
	


	

	private void enviarMensajeChat() {
		
		game.sendMessage(idActiveWorkspace, txtChatEnvio.getText());
		txtChatEnvio.setText("");
	}

	private void votarCarta() {

		
		
		//TODO toca ver como implementa silva el voto, si cuando la propongo o no
		Card carta = (Card)listPropuestas.getSelectedValue();
		
		String idTemp = idActiveWorkspace + " - " + carta.getId();
		
		if(misCartasVotadas.contains(idTemp))
		{
			JOptionPane.showMessageDialog(this, "ya votaste por esta carta");
		}
		
		game.voteCard(idActiveWorkspace, carta.getId());
		misCartasVotadas.add(idTemp);
		//TODO el servidor deberia pushearme para acutalizar interfaz
	}

	private void proponerCarta() {
		
		Card carta = (Card)listProponerCarta.getSelectedValue();
		
		boolean ans = game.proposeCard(idActiveWorkspace, carta.getId());
		 if(ans==false)
		 {
				JOptionPane.showMessageDialog(this, "La carta que quieres proponer ya esta en el Workspace");
		 }
		//TODO el servidor deberia pushearme para acutalizar interfaz
		
	}

	private void crearWorkspace() {

		ArrayList<String> nuevo = new ArrayList<String>();
		int num = listUsers.getLastVisibleIndex();
		
		for (int i = 0; i <= num; i++) {
			
			listUsers.setSelectedIndex(i);
			User tmp = (User) listUsers.getSelectedValue();
			nuevo.add(tmp.getUsername());
			
		}
		try {
			game.startGame(nuevo);
			JOptionPane.showMessageDialog(this,  "Porfavor esperar mientras los jugadores aceptan tu invitacion");
			listUsers.setListData(new String[0]);	
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,  e.getMessage());
		}
		
		
	}

	private void quitarCartaBaraja() {	
		Card carta = (Card)listMiBaraja.getSelectedValue();
		game.removeCardFromDeck(carta.getId());
		actualizarBaraja();
	}

	
	
	public void interfazModoDesconectado()
	{
		btnIniciarSesion.setEnabled(true);
    	btnCerrarSesion.setEnabled(false);
    	btnSalir.setEnabled(false);
    	btnCrearCarta.setEnabled(false);
    	btnMasCarta.setEnabled(false);
    	btnMenosCarta.setEnabled(false);
    	btnMenosUser.setEnabled(false);
    	btnMasUser.setEnabled(false);
    	btnCrearWorkspace.setEnabled(false);
    	btnProponer.setEnabled(false);
    	btnSend.setEnabled(false);
    	btnVotar.setEnabled(false);
    	txtChatEnvio.setEnabled(false);
    	lblNotificacionesDelPrograma.setText("Usuario Desconectado - Inicia Sesion para poder jugar - Juego creado por Santiago Carvajal y Sebastian Silva");
    	lblIdNombreCarta.setText("NA");
    	lblCorreo.setText("NA");
    	lblImagen.removeAll();
    	lblImagenBaraja.removeAll();
    	lblNombre.setText("NA");
    	lblUserName.setText("NA");
    	lblVotosRecibidos.setText("NA");
    	
    
    	
    	
	}
	
	public void interfazModoConectado()
	{
		txtChatEnvio.setEnabled(true);
		btnIniciarSesion.setEnabled(false);
    	btnCerrarSesion.setEnabled(true);
    	btnSalir.setEnabled(true);
    	btnCrearCarta.setEnabled(true);
    	btnMasCarta.setEnabled(true);
    	btnMenosCarta.setEnabled(true);
    	btnMenosUser.setEnabled(true);
    	btnMasUser.setEnabled(true);
    	btnCrearWorkspace.setEnabled(true);
    	btnProponer.setEnabled(true);
    	btnSend.setEnabled(true);
    	btnVotar.setEnabled(true);
    	lblNotificacionesDelPrograma.setText("Usuario conectado - Bienvenido a nuestro juego");
	}
	
	public void actualizarBaraja()
	{
		ArrayList<Card> baraja = game.getCards();
		listMiBaraja.setListData(baraja.toArray());
		listProponerCarta.setListData(baraja.toArray());
		if(baraja.isEmpty())
		{
			lblIdNombreCarta.setText("No tienes Cartas");
			lblImagenBaraja.removeAll();
		}
		else
		{
			Card primera = (Card) listMiBaraja.getSelectedValue();
			if(primera==null)
			{
				primera= baraja.get(0);
//				listMiBaraja.setSelectedIndex(0);
			}
			lblIdNombreCarta.setText(primera.getId()+" - "+ primera.getName() );	
			ImageIcon image = new ImageIcon(primera.getImageUrl());
			lblImagenBaraja.setIcon(image);
		}
		
	}
	
	public void actualizarCuenta()
	{
		User user = game.getInfoUser();
		lblUserName.setText(user.getUsername());
		lblCorreo.setText(user.getEmail());
		lblNombre.setText(user.getName());
	}
	
	public void actualizarWorkspace(int id) {
		this.idActiveWorkspace = id;
		Workspace actual = game.getWorkspace(id);
		txtAreaChat.setText(actual.getChat());
		listPropuestas.setListData(actual.getProposedCards().toArray());
		listJugadas.setListData(actual.getPlayedCards().toArray());
		lblNotificacionesDelPrograma.setText("Actualizacion en el Workspace id: " + id);
	}
	
	public void actualizarCartaWorkspace(Card carta)
	{
		lblNombreCartaWorkspace.setText(carta.getId() +" - " + carta.getName());
		txtCategoria.setText(carta.getCategory());
		txtLugar.setText(carta.getPlace());
		txtOwner.setText(carta.getOwner());
		lblVotosRecibidos.setText("votos recibidos: "+carta.getVotes());
		txtDescripcionCarta.setText(carta.getDescription());
		ImageIcon image = new ImageIcon(carta.getImageUrl());
		lblImagen.setIcon(image);
	}

	


	public void login(String user, String password) {
		try {
			game.login(user, password);
			interfazModoConectado();
			actualizarCuenta();
			actualizarBaraja();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al iniciar sesion: \n" + e.getMessage());
		}
	}

	public void signIn(String user, String password, String correo,String nombre) {
		try {
		game.signUp(user, nombre, password, correo);
		interfazModoConectado();
		actualizarCuenta();
		actualizarBaraja();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al iniciar sesion: \n" + e.getMessage());
		}
	}

	public void agregarCarta(Card carta) {
		game.addCardToDeck(carta.getId());
		actualizarBaraja();
	}

	public void crearCarta(String nombre, String lugar, String categoria, String url, String descripcion) {
		
		try {
			game.createCard(nombre, descripcion, url, lugar, categoria);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al crear la carta: \n" + e.getMessage());
		}	
	}

	
	
	public void agregarUsuario(User usuario) {
		ArrayList<User> nuevo = new ArrayList<User>();
		int num = listUsers.getLastVisibleIndex();
		
		for (int i = 0; i <= num; i++) {
			
			listUsers.setSelectedIndex(i);
			User tmp = (User) listUsers.getSelectedValue();
			
			if(tmp.getId()== usuario.getId())
			{
				return;
			}
			nuevo.add(tmp);
			
		}
		nuevo.add(usuario);
		listUsers.setListData(nuevo.toArray());
	}

	private void quitarUserJuego() {
		
		User borrar = (User) listUsers.getSelectedValue();
		
		ArrayList<User> nuevo = new ArrayList<User>();
		
		int num = listUsers.getLastVisibleIndex();
		
		for (int i = 0; i <= num; i++) {
			
			listUsers.setSelectedIndex(i);
			User tmp = (User) listUsers.getSelectedValue();
			
			if(tmp.getId()== borrar.getId())
			{
			
			}
			else
			{
			nuevo.add(tmp);
			}
		}
		listUsers.setListData(nuevo.toArray());
	}

	@Override
	public void refresh(int id, boolean onTop) {
		if(id==idActiveWorkspace)
		{
			actualizarWorkspace(id);	
		}
		else if(onTop)
		{
			
			actualizarWorkspace(id);
			JOptionPane.showMessageDialog(null, "Nuevo workspace para jugar (id: " + id+")" );
			listMyWorkspaces.setSelectedIndex(0);
		}
		
		
	}

	
	
	


}