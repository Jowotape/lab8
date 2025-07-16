package prob2;

import javax.swing.*;
import java.awt.event.*;

public class Prob2 extends JFrame implements ActionListener{
    private JLabel lblInicio, lblFin;
    private JTextField txtInicio, txtFin;
    private JButton btnCalcular;
    private JTextArea txtResultado;
    private JScrollPane scroll;

    public Prob2() {
        setLayout(null);
        setTitle("Números Palíndromos");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblInicio = new JLabel("Número inicial:");
        lblInicio.setBounds(20, 20, 100, 25);
        add(lblInicio);

        txtInicio = new JTextField();
        txtInicio.setBounds(130, 20, 100, 25);
        add(txtInicio);

        lblFin = new JLabel("Número final:");
        lblFin.setBounds(20, 60, 100, 25);
        add(lblFin);

        txtFin = new JTextField();
        txtFin.setBounds(130, 60, 100, 25);
        add(txtFin);

        btnCalcular = new JButton("Calcular palíndromos");
        btnCalcular.setBounds(20, 100, 210, 30);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        scroll = new JScrollPane(txtResultado);
        scroll.setBounds(20, 150, 340, 180);
        add(scroll);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int inicio = Integer.parseInt(txtInicio.getText());
            int fin = Integer.parseInt(txtFin.getText());

            if (inicio > fin) {
                JOptionPane.showMessageDialog(this, "El número inicial no puede ser mayor que el final.");
                return;
            }

            txtResultado.setText("Números palíndromos:\n");

            for (int i = inicio; i <= fin; i++) {
                if (esPalindromo(i)) {
                    txtResultado.append(i + "\n");
                }
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos.");
        }
    }

    public boolean esPalindromo(int numero) {
        int original = numero;
        int invertido = 0;

        while (numero != 0) {
            int digito = numero % 10;
            invertido = invertido * 10 + digito;
            numero /= 10;
        }

        return original == invertido;
    }
}


