package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class prueba extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba frame = new prueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private JList list;
	/**
	 * Create the frame.
	 */
	public prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		
		list = new JList();
		contentPane.add(list, BorderLayout.CENTER);
		
		String[] rr = {"h","l","d","f"};
		list.setListData(rr);
		JButton button = new JButton("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				ArrayList<String> ff = new ArrayList<String>();
				
				ff.add("hola");
				ff.add("gg");
				
				ArrayList<String> f = new ArrayList<String>();
				
				f.add("hola");
				f.add("gg");
				f.add("df");
				
				boolean r = Boolean.parseBoolean("True");
				
				String rta =""+(r&&true)+" ";
				JOptionPane.showMessageDialog(null, rta+"");
			}
		});
		contentPane.add(button, BorderLayout.SOUTH);
	}

}
