package interfaz;
/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: readme.txt,v 1.1 2009/09/15 16:09:20 dav-vill Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_elimiGemas
 * Autor: Juan David Villa - 31-ago-2009
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * Clase donde se coloca la imagen encabezado
 */
public class PanelImagen extends JPanel
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor por defecto. Coloca la imagen del encabezado de la aplicaci�n.
     */
    public PanelImagen( )
    {
        JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "data/imagenes/banner.png" );
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        add( imagen );
        setBackground( Color.WHITE );
    }

}

