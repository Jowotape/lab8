package prob3;

import exceptions.CedulaInvalidaException;
import exceptions.MontoInvalidoException;
import exceptions.NombreInvalidoException;

import javax.swing.*;
import java.awt.*;

public class Problema3 {
    private JPanel panel1;
    private JTextField campoNombre;
    private JTextField campoCedula;
    private JTextField campoMonto;
    private JComboBox<Integer> comboPlazo;
    private JButton botonCalcular;
    private JLabel lbResultado;
    private JLabel lbTitulo;

    public Problema3() {
        lbTitulo.setText("Banco - Plazo Fijo");
        lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitulo.setPreferredSize(new Dimension(400, 40));
        lbTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        comboPlazo.addItem(12);
        comboPlazo.addItem(24);
        comboPlazo.addItem(36);
        comboPlazo.addItem(48);
        comboPlazo.addItem(60);

        botonCalcular.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String cedula = campoCedula.getText().trim();
            String montoTexto = campoMonto.getText().trim();

            if (nombre.isEmpty() || cedula.isEmpty() || montoTexto.isEmpty()) {
                lbResultado.setText("<html><span style='color:red'>Todos los campos deben estar llenos.</span></html>");
                return;
            }

            try {
                int monto = Integer.parseInt(montoTexto);
                int plazo = (Integer) comboPlazo.getSelectedItem();

                Logica logica = new Logica();
                logica.setNombre(nombre);
                logica.setCedula(cedula);
                logica.setMontoADepositar(monto);
                logica.setPlazoEnMeses(plazo);

                double resultado = logica.calcularMontoAcumulado();
                lbResultado.setText(String.format(
                        "<html><b>Depósito válido.</b><br>Monto acumulado: B/. %.2f</html>", resultado));

            } catch (NombreInvalidoException | CedulaInvalidaException |
                     MontoInvalidoException ex) {
                lbResultado.setText(String.format("<html><span style='color:red'>%s</span></html>", ex.getMessage()));
            } catch (NumberFormatException | IllegalArgumentException ex) {
                lbResultado.setText("<html><span style='color:red'>Monto inválido. Debe ser un número entero o el plazo no es válido.</span></html>");
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Banco - Plazo Fijo");
            frame.setContentPane(new Problema3().getPanel1());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrado
            frame.setVisible(true);
        });
    }
}
