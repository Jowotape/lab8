package prob1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JList;


public class problema1<E> extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JList<String> list;
	private DefaultListModel<String> contenidoDeLaLista = new DefaultListModel<String>();

	
	void identificarVocales()
	{
		Pattern regexVocales = Pattern.compile("[aeiouAEIOUáéíóúÁÉÍÓÚüÜ]");
		Matcher buscadorRegex;
		
		String salida = "";
		
		buscadorRegex = regexVocales.matcher(textField.getText());
		String texto = textField.getText();
		for (int i = 0; i < texto.length(); i++)
		{
			while(buscadorRegex.find())
			{
				salida += buscadorRegex.group();
			}
		}
		
		contenidoDeLaLista.addElement(salida);
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					problema1 frame = new problema1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public problema1() {
		setTitle("Problema 1 - Detectar vocales");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(24, 21, 143, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton botonRevisarVocales = new JButton("Identificar vocales");
		botonRevisarVocales.setBounds(177, 20, 148, 23);
		botonRevisarVocales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				contenidoDeLaLista.clear();
				
				if (textField.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+"))
				{					
					identificarVocales();
				}
				else
				{
					contenidoDeLaLista.addElement("Error: Solo se admiten letras.");
				}
			}
		});
		contentPane.add(botonRevisarVocales);
		
		list = new JList<String>(contenidoDeLaLista);
		list.setBounds(24, 52, 301, 72);
		contentPane.add(list);
		
		

		
	}

}
