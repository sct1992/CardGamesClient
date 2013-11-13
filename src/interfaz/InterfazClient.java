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
	
	/**
	 * constantes para los botones
	 */
	public final static String INICIAR_SESION = "INICIAR_SESION";
	public final static String CERRAR_SESION = "CERRAR_SESION";
	public final static String SALIR = "SALIR";
	public final static String CREAR_CARTA = "CREAR_CARTA";
	public final static String MAS_CARTA = "MAS_CARTA";
	public final static String MENOS_CARTA = "MENOS_CARTA";
	public final static String MENOS_USER = "MENOS_USER";
	public final static String MAS_USER = "MAS_USER";
	public final static String CREAR_WORKSPACE = "CREAR_WORKSPACE";
	public final static String PROPONER = "PROPONER";
	public final static String SEND = "SEND";
	public final static String VOTAR = "VOTAR";

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
	 * Atributos de interfaz
	 */
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
	private JLabel lblNombreCartaWorkspace;
    private JList listMiBaraja;
    private JLabel lblInfoWS;
    
	private IGame game;
    private int idActiveWorkspace;
    private ArrayList<String> misCartasVotadas;
    
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa el juego indicando que no se ha cargado la información de ningún archivo
     */
    public InterfazClient( )
    {

    	misCartasVotadas = new ArrayList<String>();
    	idActiveWorkspace = -1;
    	game = new Game(this);
    	//CODIGO GENERADO
    	getContentPane().setBackground(Color.WHITE);
        setSize( 970, 565 );
        setResizable(false);
        
        setTitle( "GameCards" );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(new TitledBorder(null, "Mi Baraja", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 119, 191, 358);
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
        btnMasCarta.setBounds(49, 324, 50, 23);
        panel.add(btnMasCarta);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 199, 171, 119);
        panel.add(scrollPane_1);
        
        listMiBaraja = new JList();
        listMiBaraja.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        			cambioListMiBaraja();
        	}

        });
        scrollPane_1.setViewportView(listMiBaraja);
   
        btnMenosCarta = new JButton("-");
        btnMenosCarta.setBounds(10, 324, 40, 23);
        panel.add(btnMenosCarta);
        
        btnCrearCarta = new JButton("Crear");
        btnCrearCarta.setBounds(101, 324, 80, 23);
        panel.add(btnCrearCarta);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.LIGHT_GRAY);
        panel_1.setBorder(new TitledBorder(null, "Lobby", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(199, 119, 226, 358);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JPanel panel_10 = new JPanel();
        panel_10.setBackground(Color.LIGHT_GRAY);
        panel_10.setBorder(new TitledBorder(null, "Nuevo Workspace", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_10.setBounds(10, 172, 206, 173);
        panel_1.add(panel_10);
        panel_10.setLayout(null);
        
        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(10, 21, 186, 118);
        panel_10.add(scrollPane_2);
        
        listUsers = new JList();
        scrollPane_2.setViewportView(listUsers);
        
        btnMenosUser = new JButton("-");
        btnMenosUser.setBounds(10, 142, 50, 23);
        panel_10.add(btnMenosUser);
        
        btnMasUser = new JButton("+");
        btnMasUser.setBounds(58, 142, 50, 23);
        panel_10.add(btnMasUser);
        
        btnCrearWorkspace = new JButton("Crear");
        btnCrearWorkspace.setBounds(108, 142, 88, 23);
        panel_10.add(btnCrearWorkspace);
        
        JPanel panel_11 = new JPanel();
        panel_11.setBackground(Color.LIGHT_GRAY);
        panel_11.setBorder(new TitledBorder(null, "Workspaces Activos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_11.setBounds(10, 22, 206, 150);
        panel_1.add(panel_11);
        panel_11.setLayout(null);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(10, 21, 186, 118);
        panel_11.add(scrollPane_3);
        
        listMyWorkspaces = new JList();
        listMyWorkspaces.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        		cambioWorkspace();
        	}

		
        });
        scrollPane_3.setViewportView(listMyWorkspaces);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.LIGHT_GRAY);
        panel_2.setBorder(new TitledBorder(null, "Mi Cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(10, 11, 415, 97);
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
        btnIniciarSesion.setBounds(263, 17, 142, 37);
        panel_2.add(btnIniciarSesion);
        
        btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setBounds(263, 56, 142, 29);
        panel_2.add(btnCerrarSesion);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.LIGHT_GRAY);
        panel_3.setBorder(new LineBorder(SystemColor.inactiveCaption));
        panel_3.setBounds(10, 484, 415, 47);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);
        
        lblNotificacionesDelPrograma = new JLabel("Notificaciones del programa");
        lblNotificacionesDelPrograma.setHorizontalAlignment(SwingConstants.CENTER);
        lblNotificacionesDelPrograma.setBounds(10, 11, 395, 20);
        panel_3.add(lblNotificacionesDelPrograma);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.LIGHT_GRAY);
        panel_4.setBorder(new TitledBorder(null, "Workspace", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_4.setBounds(435, 11, 519, 520);
        getContentPane().add(panel_4);
        panel_4.setLayout(null);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.LIGHT_GRAY);
        panel_5.setBorder(new TitledBorder(null, "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_5.setBounds(227, 349, 282, 160);
        panel_4.add(panel_5);
        panel_5.setLayout(null);
        
        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(10, 22, 262, 99);
        panel_5.add(scrollPane_4);
        
        txtAreaChat = new JTextArea();
        txtAreaChat.setEditable(false);
        txtAreaChat.setForeground(Color.BLACK);
        txtAreaChat.setBackground(Color.WHITE);
        scrollPane_4.setViewportView(txtAreaChat);
        
        txtChatEnvio = new JTextField();
        txtChatEnvio.setBounds(10, 126, 173, 23);
        panel_5.add(txtChatEnvio);
        txtChatEnvio.setColumns(10);
        
        btnSend = new JButton("Send");
        btnSend.setBounds(193, 126, 79, 23);
        panel_5.add(btnSend);
        
        JPanel panel_6 = new JPanel();
        panel_6.setBackground(Color.LIGHT_GRAY);
        panel_6.setBorder(new TitledBorder(null, "Proponer Carta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_6.setBounds(10, 349, 207, 160);
        panel_4.add(panel_6);
        panel_6.setLayout(null);
        
        btnProponer = new JButton("Proponer");
        btnProponer.setBounds(88, 126, 109, 23);
        panel_6.add(btnProponer);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 23, 187, 100);
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
        panel_7.setBounds(10, 65, 259, 277);
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
        panel_8.setBounds(295, 65, 214, 117);
        panel_4.add(panel_8);
        panel_8.setLayout(null);
        
        JScrollPane scrollPane_6 = new JScrollPane();
        scrollPane_6.setBounds(10, 22, 194, 84);
        panel_8.add(scrollPane_6);
        
        listJugadas = new JList();
        listJugadas.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        		cambioListJugadas();
        	}

        });
        scrollPane_6.setViewportView(listJugadas);
        
        JPanel panel_9 = new JPanel();
        panel_9.setBackground(Color.LIGHT_GRAY);
        panel_9.setBorder(new TitledBorder(null, "Propuestas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_9.setBounds(295, 186, 214, 152);
        panel_4.add(panel_9);
        panel_9.setLayout(null);
        
        JScrollPane scrollPane_7 = new JScrollPane();
        scrollPane_7.setBounds(10, 24, 194, 94);
        panel_9.add(scrollPane_7);
        
        listPropuestas = new JList();
        listPropuestas.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent arg0) {
        		cambioListPropuestas();
        	}
        });
        scrollPane_7.setViewportView(listPropuestas);
        
        btnVotar = new JButton("votar");
        btnVotar.setBounds(10, 124, 194, 17);
        panel_9.add(btnVotar);
        
    	btnIniciarSesion.setActionCommand(INICIAR_SESION);
    	btnCerrarSesion.setActionCommand(CERRAR_SESION);
    	btnCrearCarta.setActionCommand(CREAR_CARTA);
    	btnMasCarta.setActionCommand(MAS_CARTA);
    	btnMenosCarta.setActionCommand(MENOS_CARTA);
    	btnMenosUser.setActionCommand(MENOS_USER);
    	btnMasUser.setActionCommand(MAS_USER);
    	btnCrearWorkspace.setActionCommand(CREAR_WORKSPACE);
    	btnProponer.setActionCommand(PROPONER);
    	btnSend.setActionCommand(SEND);
    	btnVotar.setActionCommand(VOTAR);
    	
    	JPanel panel_12 = new JPanel();
    	panel_12.setBackground(Color.LIGHT_GRAY);
    	panel_12.setBorder(new TitledBorder(null, "Informacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_12.setBounds(10, 22, 499, 43);
    	panel_4.add(panel_12);
    	panel_12.setLayout(null);
    	
    	btnSalir = new JButton("Salir");
    	btnSalir.setBounds(400, 11, 89, 23);
    	panel_12.add(btnSalir);
    	btnSalir.setActionCommand(SALIR);
    	
    	lblInfoWS = new JLabel("NA");
    	lblInfoWS.setHorizontalAlignment(SwingConstants.CENTER);
    	lblInfoWS.setBounds(10, 15, 380, 17);
    	panel_12.add(lblInfoWS);
    	btnSalir.addActionListener(this);
    	
    	btnIniciarSesion.addActionListener(this);
    	btnCerrarSesion.addActionListener(this);
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
    
   
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  // CAMBIO DE LISTAS, REFRESCO DE LA INTERFAZ
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
	
    /**
     * cuando se cambia la seleccion de una carta en la baraja
     */
    public void cambioListMiBaraja() {
			Card primera = (Card) listMiBaraja.getSelectedValue();
		
			lblIdNombreCarta.setText(primera.getId()+" - "+ primera.getName() );	
			ImageIcon image = new ImageIcon(primera.getImageUrl());
			lblImagenBaraja.setIcon(image);
			validarFuncionamientoWorkspace();
	
	}
	
	/**
	 * cuando se cambia la seleccion de un workspace en el lobby
	 */
	public void cambioWorkspace() {
		if(listMyWorkspaces.isSelectionEmpty()==false)
		{
		   	Workspace actual = (Workspace) listMyWorkspaces.getSelectedValue();
			idActiveWorkspace=actual.getId();
			actualizarWorkspace(idActiveWorkspace);
		}
	}
	
	/**
	 * cuando se cambia la seleccion de una carta que se puede proponer
	 */
	public void cambioListPropuestas() {
		Card actual = (Card) listPropuestas.getSelectedValue();
		actualizarCartaWorkspace(actual);
	}
	
	/**
	 * cuando se cambia la seleccion de una carta jugada
	 */
	public void cambioListJugadas() {
		Card actual = (Card) listJugadas.getSelectedValue();
		actualizarCartaWorkspace(actual);
		
	}
	
	/**
	 * cuando se cambia la seleccion de una carta propuesta
	 */
	public void cambioListProponerCarta() {
		Card actual = (Card) listProponerCarta.getSelectedValue();
		actualizarCartaWorkspace(actual);
		
	}
	

	/**
	 * Metodo que verifica que si esta en un workspace valido sean posible las acciones del workspace o no
	 */
	public void validarFuncionamientoWorkspace() {

		if(idActiveWorkspace<0)
		{
			btnProponer.setEnabled(false);
			btnSend.setEnabled(false);
			btnVotar.setEnabled(false);
			btnSalir.setEnabled(false);
		}
		else
		{
			btnSalir.setEnabled(true);
			btnProponer.setEnabled(true);
			btnSend.setEnabled(true);
			btnVotar.setEnabled(true);
		}
	}
	

	/**
	 * desactiva todas las interacciones cuando el usurio esta desconectado
	 */
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
    	lblNotificacionesDelPrograma.setText("Usuario Desconectado - Inicia Sesion para poder jugar");
    	lblIdNombreCarta.setText("NA");
    	lblCorreo.setText("NA");
    	lblImagen.removeAll();
    	lblImagenBaraja.removeAll();
    	lblNombre.setText("NA");
    	lblUserName.setText("NA");
    	lblVotosRecibidos.setText("NA");
    
	}
	
	/**
	 * activa todas las interacciones si el usuario esta conectado
	 */
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
	
	/**
	 * metodo que refresca la informacion de la baraja
	 */
	public void actualizarBaraja()
	{
		try{
			ArrayList<Card> baraja = game.getMyCards();

			if(baraja.isEmpty())
			{
				try {
					listMiBaraja.setListData(baraja.toArray());
				} catch (Exception e) {

				}
				try {
					listProponerCarta.setListData(baraja.toArray());
				} catch (Exception e) {

				}
			}
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
				}
				lblIdNombreCarta.setText(primera.getId()+" - "+ primera.getName() );	
				ImageIcon image = new ImageIcon(primera.getImageUrl());
				lblImagenBaraja.setIcon(image);
			}
		}
		catch (Exception e) {
		}	
	}
	
	/**
	 * metodo que refresca la informacion de la cuenta
	 */
	public void actualizarCuenta()
	{
		try{
			User user = game.getInfoUser();
			lblUserName.setText(user.getUsername());
			lblCorreo.setText(user.getEmail());
			lblNombre.setText(user.getName());
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Super Fatal Error (803): " +e.getMessage());
		}
	}

	/**
	 * metodo que refresca la informacion de un workspace dado
	 * @param id del workspace
	 */
	public void actualizarWorkspace(int id) {
		try{		
			ArrayList<Workspace> workspace = game.getMyWorkspaces();
			try
			{
				listMyWorkspaces.setListData(workspace.toArray());
			}catch (Exception e) {

			}

			this.idActiveWorkspace = id;
			Workspace actual = game.getWorkspace(id);
			txtAreaChat.setText(actual.getChat());
			try
			{
				listPropuestas.setListData(actual.getProposedCards().toArray());
			}catch (Exception e) {

			}
			try
			{

				listJugadas.setListData(actual.getPlayedCards().toArray());
			}catch (Exception e) {

			}
			lblInfoWS.setText("Workspace: " + id + " - " + actual.getUsers());
			lblNotificacionesDelPrograma.setText("Actualizacion en el Workspace id: " + id);
		}
		catch (Exception e) {

		}
	}

	/**
	 * metodo que actualiza una carta en el WS
	 * @param carta que se quiere actualizar
	 */
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

	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	// ESCUCHADOR DE BOTONES
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------



	/**
	 * metodo que se encarga de escuchar los botones
	 */
	public void actionPerformed(ActionEvent arg0) {


		String command = arg0.getActionCommand();

		if(command.equalsIgnoreCase(INICIAR_SESION))
		{
			DialogInicioSesion dialogo = new DialogInicioSesion(this);
			dialogo.setVisible(true);
		}
		else if(command.equals(CERRAR_SESION))
		{
			try {
				game.quit();
				idActiveWorkspace = -1;
				interfazModoDesconectado();
				validarFuncionamientoWorkspace();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "La carta que quieres proponer ya esta en el Workspace");
			}

		}
		else if(command.equals(SALIR))
		{
			salirPartida();

		}
		else if( command.equals(MAS_CARTA))
		{
			try{
				DialogAgregarCarta dialogo = new DialogAgregarCarta(this,game.getCards());
				dialogo.setVisible(true);
			} catch (Exception e) {

				JOptionPane.showMessageDialog(this, "Super Fatal Error (570): " +e.getMessage());
			}
		}
		else if( command.equals(MENOS_CARTA))
		{
			if(!listMiBaraja.isSelectionEmpty())
				quitarCartaBaraja();
		}
		else if( command.equals(CREAR_CARTA))
		{
			DialogCrearCarta dialogo = new DialogCrearCarta(this);
			dialogo.setVisible(true);
		}
		else if( command.equals(MAS_USER))
		{
			try
			{
				DialogAgregarUsuario dialogo = new DialogAgregarUsuario(this, game.getActiveUsers());
				dialogo.setVisible(true);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Super Fatal Error (590): " +e.getMessage());	
			}

		}
		else if( command.equals(MENOS_USER))
		{
			quitarUserJuego();
		}
		else if( command.equals(CREAR_WORKSPACE))
		{
			try{
				DialogAgregarWSporCarta dialogo = new DialogAgregarWSporCarta(this, game.getCards());
				dialogo.setVisible(true);
			} catch (Exception e) {

				JOptionPane.showMessageDialog(this, "Super Fatal Error (812): " +e.getMessage());
			}
		}
		else if( command.equals(PROPONER))
		{
			proponerCarta();
		}
		else if( command.equals(VOTAR))
		{
			votarCarta();
		}
		else if( command.equals(SEND))
		{
			enviarMensajeChat();
		}

	}



	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	// METODOS CON INTERACCION EN EL SERVIDOR
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------

	/**
	 * sale de la partida actual
	 */
	public void salirPartida() {
		try {
			game.quitWorkspace(idActiveWorkspace);
			idActiveWorkspace=-1;
			validarFuncionamientoWorkspace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * envia mensaje a lchat del workspace activo
	 */
	public void enviarMensajeChat() {

		try
		{
			game.sendMessage(idActiveWorkspace, txtChatEnvio.getText());
			txtChatEnvio.setText("");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Super Fatal Error (633): " +e.getMessage());
		}
	}


	/**
	 * vota la carta seleccionada en la lista de votacion
	 */
	public void votarCarta() {

		Card carta = (Card)listPropuestas.getSelectedValue();

		String idTemp = idActiveWorkspace + " - " + carta.getId();

		if(misCartasVotadas.contains(idTemp))
		{
			JOptionPane.showMessageDialog(this, "ya votaste por esta carta");
			return;
		}

		try{
			game.voteCard(idActiveWorkspace, carta.getId());
			misCartasVotadas.add(idTemp);
			actualizarWorkspace(idActiveWorkspace);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Super Fatal Error (657): " +e.getMessage());
		}
	}


	/**
	 * porpone una carta al workspace activo
	 */
	public void proponerCarta() {
		try
		{
			Card carta = (Card)listProponerCarta.getSelectedValue();

			boolean ans = game.proposeCard(idActiveWorkspace, carta.getId());
			if(ans==false)
			{
				JOptionPane.showMessageDialog(this, "La carta que quieres proponer ya esta en el Workspace");
			}
			actualizarWorkspace(idActiveWorkspace);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Super Fatal Error: " +e.getMessage());
		}		
	}


	/**
	 * crea un workspace sin carta
	 */
	public void crearWorkspace() {

		ArrayList<String> nuevo = new ArrayList<String>();
		int num = listUsers.getLastVisibleIndex();

		for (int i = 0; i <= num; i++) {

			listUsers.setSelectedIndex(i);
			User tmp = (User) listUsers.getSelectedValue();
			nuevo.add(tmp.getUsername());

		}

		if(nuevo.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "no has seleccionado a nadie para invitar, no puedes crear una partida vacia");
			return;
		}
		try {
			game.startGame(nuevo);
			JOptionPane.showMessageDialog(this,  "Porfavor esperar mientras los jugadores aceptan tu invitacion");
			listUsers.setListData(new String[0]);	

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "(699) "+ e.getMessage());
		}


	}

	/**
	 * crea un workspace dado una carta
	 * @param idCard id de la carta
	 */
	public void crearWorkspacePorCarta(int idCard) {

		ArrayList<String> nuevo = new ArrayList<String>();
		int num = listUsers.getLastVisibleIndex();

		for (int i = 0; i <= num; i++) {

			listUsers.setSelectedIndex(i);
			User tmp = (User) listUsers.getSelectedValue();
			nuevo.add(tmp.getUsername());

		}

		if(nuevo.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "no has seleccionado a nadie para invitar, no puedes crear una partida vacia");
			return;
		}
		try {
			game.startGame(nuevo, idCard);
			JOptionPane.showMessageDialog(this,  "Porfavor esperar mientras los jugadores aceptan tu invitacion");
			listUsers.setListData(new String[0]);	

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "(699) "+ e.getMessage());
		}


	}

	/**
	 * quita una carta de la baraja
	 */
	public void quitarCartaBaraja() {	
		try{
			Card carta = (Card)listMiBaraja.getSelectedValue();
			game.removeCardFromDeck(carta.getId());
			actualizarBaraja();

		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Super Fatal Error (711):  " +e.getMessage());
		}	

	}

	/**
	 * logea a un usuario 
	 * @param user usuario
	 * @param password contraseña
	 */
	public void login(String user, String password) {
		try {
			if(!game.login(user, password))
			{
				throw new Exception("Error de inicio de sesion, valida tu informacion");
			}
			interfazModoConectado();
			validarFuncionamientoWorkspace();
			actualizarCuenta();
			actualizarBaraja();
			idActiveWorkspace=-1;
		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, "Error al iniciar sesion: \n" + e.getMessage());
		}
	}

	/**
	 * registra a un usuario
	 * @param user usuario
	 * @param password contraseña
	 * @param correo correo
	 * @param nombre nombre 
	 */
	public void signIn(String user, String password, String correo,String nombre) {
		try {
			if(!game.signUp(user, nombre, password, correo))
			{
				throw new Exception("Error de inicio de sesion, valida tu informacion");
			}
			interfazModoConectado();
			validarFuncionamientoWorkspace();
			actualizarCuenta();
			actualizarBaraja();
			idActiveWorkspace=-1;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al iniciar sesion: \n" + e.getMessage());
		}
	}

	/**
	 * agrega una carta a la baraja 
	 * @param carta que se quiere agregar
	 */
	public void agregarCarta(Card carta) {
		try
		{
			boolean ans = game.addCardToDeck(carta.getId());
			if(ans==false)
			{
				JOptionPane.showMessageDialog(this, "La carta seleccionada ya esta en tu baraja");
				return;
			}
			actualizarBaraja();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Super Fatal Error: " +e.getMessage());
		}
	}

	/**
	 * crea una carta con los parametros dados
	 * @param nombre
	 * @param lugar 
	 * @param categoria
	 * @param url
	 * @param descripcion
	 */
	public void crearCarta(String nombre, String lugar, String categoria, String url, String descripcion) {

		try {
			game.createCard(nombre, descripcion, url, lugar, categoria);
			actualizarBaraja();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al crear la carta: \n" + e.getMessage());
		}	
	}


	/**
	 * agrega un usuario al listado de usuarios para crear workspace
	 * @param usuario
	 */
	public void agregarUsuario(User usuario) {

		try
		{
			if(game.getInfoUser().getUsername().equals(usuario.getUsername()))
			{
				JOptionPane.showMessageDialog(this, "no puedes agregarte a ti mismo");
				return;
			}
		}
		catch (Exception e) {

		}
		ArrayList<User> nuevo = new ArrayList<User>();
		int num = listUsers.getLastVisibleIndex();

		for (int i = 0; i <= num; i++) {

			listUsers.setSelectedIndex(i);
			User tmp = (User) listUsers.getSelectedValue();

			if(tmp.getUsername().equalsIgnoreCase(usuario.getUsername()))
			{
				return;
			}
			nuevo.add(tmp);

		}
		nuevo.add(usuario);


		listUsers.setListData(nuevo.toArray());
	}
	
	/**
	 * elimina el usuario seleccionado en la lista de jugadores a invitar
	 */
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

	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	// REFRESH DEL PUSH DEL SERVIDOR
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	
	/**
	 * notificacion push de refrescar una partida
	 */
	public void refresh(int id, boolean onTop) {
		validarFuncionamientoWorkspace();
		if(id==idActiveWorkspace)
		{
			actualizarWorkspace(id);	
		}
		else if(onTop)
		{

			actualizarWorkspace(id);
			JOptionPane.showMessageDialog(null, "Nuevo workspace para jugar (id: " + id+")" );
			idActiveWorkspace=id;
			validarFuncionamientoWorkspace();
		}




	}

	/**
	 * notificacion de juego cerrado
	 */
	public void pushedClosedGame(int idWorkspace, String userThatQuits) {

		try {

			JOptionPane.showMessageDialog(this, "La partida con id: " + idWorkspace + " se cerro debido a la salida del usuario: "+userThatQuits);
			if(idActiveWorkspace == idWorkspace)
			{
				idActiveWorkspace=-1;
			}
			ArrayList<Workspace> workspace = game.getMyWorkspaces();
			validarFuncionamientoWorkspace();
			try
			{
				listMyWorkspaces.setListData(workspace.toArray());
			}catch (Exception e) {

			}

		} catch (Exception e) {

		}
	}



	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	// DISPOSE ( MATA THREAD Y SACA DE PARTIDAS)
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	
	/**
	 * metodo que antes de cerrar cierra sesion
	 */
	public void dispose()
	{
		try
		{
			if(game.getInfoUser()!=null)
			{
				game.quit();
			}
			super.dispose();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Super Fatal Error (993): " +e.getMessage());
			super.dispose();
		}
	}
}