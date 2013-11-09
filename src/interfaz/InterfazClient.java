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

import User.IGame;



/**
 * Es la clase principal de la interfaz
 */
public class InterfazClient extends JFrame 
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * conexion con el "mundo" (que se conecta con el servidor)
	 */
	private IGame game;

	 //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Es el panel del encabezado de imagen
     */
    private PanelImagen panelImagen;

    /**
     * Es el panel de manejo de opciones
     */
    private PanelOpcion panelOpcion;
	
    /**
     * Es el panel de interaccion del juego
     */
    private JPanel panelTablero;
	
    
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye la interfaz e inicializa el juego indicando que no se ha cargado la información de ningún archivo
     */
    public InterfazClient( )
    {

        setLayout( new BorderLayout( ) );
        setSize( 800, 740 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  
        panelImagen = new PanelImagen( );
        panelOpcion = new PanelOpcion( this );
        panelTablero = new JPanel();
        add( panelImagen, BorderLayout.NORTH );
        add( panelOpcion, BorderLayout.SOUTH );
        add( panelTablero, BorderLayout.CENTER );
        
        setTitle( "GameCards" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
       
    }
    //-----------------------------------------------------------------
    // Puntos de Extensión
    //-----------------------------------------------------------------
	
    /**
     * Ejecuta el punto de extensión 1
     */
	public void reqFuncOpcion1() 
	{
		//Producto p1 = server.darProducto();
		String resultado= "bla";
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}
	
    /**
     * Ejecuta el punto de extensión 2
     */
	public void reqFuncOpcion2() 
	{
		String resultado = "nopi";
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
	}
	

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
}