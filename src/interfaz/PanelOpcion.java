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


import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
	
/**
 * Panel de manejo de opciones
 */
public class PanelOpcion extends JPanel implements ActionListener
{
	
	    // -----------------------------------------------------------------
	    // Constantes
	    // -----------------------------------------------------------------

    	/**
    	 * Constante para la serialización
    	 */
    	private static final long serialVersionUID = 1L;
    	
	    /**
	     * Comando Opción 1
	     */
	    private static final String OPCION_1 = "OPCION_1";

	    /**
	     * Comando Opción 2
	     */
	    private static final String OPCION_2 = "OPCION_2";
	    
	 
	    // -----------------------------------------------------------------
	    // Atributos
	    // -----------------------------------------------------------------

	    /**
	     * Ventana principal de la aplicación
	     */
	    private InterfazClient principal;

	    // -----------------------------------------------------------------
	    // Atributos de interfaz
	    // -----------------------------------------------------------------

	    /**
	     * Botón Opción 1
	     */
	    private JButton btnOpcion1;

	    /**
	     * Botón Opción 2
	     */
	    private JButton btnOpcion2;

	 
	    // -----------------------------------------------------------------
	    // Constructores
	    // -----------------------------------------------------------------

	    /**
	     * Constructor del panel
	     * @param ventana Ventana principal
	     */
	    public PanelOpcion(InterfazClient ventana )
	    {
	        principal = ventana;

	        setBorder( new TitledBorder( "Opciones" ) );
	        setLayout( new GridLayout( 1, 2 ) );

	        btnOpcion1 = new JButton( "Opción 1" );
	        btnOpcion1.setActionCommand( OPCION_1 );
	        btnOpcion1.addActionListener( this );
	        btnOpcion1.setIcon(new ImageIcon( "data/imagenes/opc1.png" ) );
	        add( btnOpcion1 );

	        btnOpcion2 = new JButton( "Opción 2" );
	        btnOpcion2.setActionCommand( OPCION_2 );
	        btnOpcion2.addActionListener( this );
	        btnOpcion2.setIcon(new ImageIcon( "data/imagenes/opc2.png" ) );
	        add( btnOpcion2 );


	    }

	    // -----------------------------------------------------------------
	    // Métodos
	    // -----------------------------------------------------------------

	    /**
	     * Manejo de los eventos de los botones
	     * @param e Acción que generó el evento.
	     */
		public void actionPerformed(ActionEvent e)
		{
			String com = e.getActionCommand();
			 if( OPCION_1.equals( com ) )
		        {
		            principal.reqFuncOpcion1( );
		        }
		        else if( OPCION_2.equals( com ) )
		        {
		            principal.reqFuncOpcion2( );
		        }
			}

}
