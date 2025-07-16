package prob1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Problema1<E> extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JList<String> list;
	private DefaultListModel<String> contenidoDeLaLista = new DefaultListModel<>();

	public Problema1() {
		setTitle("Problema 1 - Detectar vocales");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // para que no cierre todo el programa
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
		contentPane.add(botonRevisarVocales);

		list = new JList<>(contenidoDeLaLista);
		list.setBounds(24, 52, 301, 72);
		contentPane.add(list);

		botonRevisarVocales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contenidoDeLaLista.clear();
				String texto = textField.getText();

				if (texto.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+")) {
					identificarVocales(texto);
				} else {
					contenidoDeLaLista.addElement("Error: Solo se admiten letras.");
				}
			}
		});
	}

	private void identificarVocales(String texto) {
		Pattern regexVocales = Pattern.compile("[aeiouAEIOUáéíóúÁÉÍÓÚüÜ]");
		Matcher buscadorRegex = regexVocales.matcher(texto);
		StringBuilder salida = new StringBuilder();

		while (buscadorRegex.find()) {
			salida.append(buscadorRegex.group());
		}

		contenidoDeLaLista.addElement(salida.toString());
	}
}
