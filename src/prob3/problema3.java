package prob3;

import exceptions.CedulaInvalidaException;
import exceptions.MontoInvalidoException;
import exceptions.NombreInvalidoException;

import javax.swing.*;

public class problema3 {
    private JPanel panel1;
    private JTextField campoNombre;
    private JTextField campoCedula;
    private JTextField campoMonto;
    private JComboBox<Integer> comboPlazo;
    private JButton botonCalcular;
    private JLabel lbResultado;

    public problema3() {
        // Inicializar ComboBox con los valores válidos
        comboPlazo.addItem(12);
        comboPlazo.addItem(24);
        comboPlazo.addItem(36);
        comboPlazo.addItem(48);
        comboPlazo.addItem(60);

        // Acción del botón
        botonCalcular.addActionListener(e -> {
            try {
                Logica logica = new Logica();

                String nombre = campoNombre.getText().trim();
                String cedula = campoCedula.getText().trim();
                int monto = Integer.parseInt(campoMonto.getText().trim());
                int plazo = (Integer) comboPlazo.getSelectedItem();

                logica.setNombre(nombre);
                logica.setCedula(cedula);
                logica.setMontoADepositar(monto);
                logica.setPlazoEnMeses(plazo);

                double resultado = logica.calcularMontoAcumulado();
                lbResultado.setText(String.format(
                        "<html><b>Depósito válido.</b><br>Monto acumulado: B/. %.2f</html>", resultado));
            } catch (NumberFormatException ex) {
                lbResultado.setText("<html><span style='color:red'>Monto inválido. Debe ser un número entero.</span></html>");
            } catch (NombreInvalidoException | CedulaInvalidaException |
                     MontoInvalidoException | IllegalArgumentException ex) {
                lbResultado.setText(String.format("<html><span style='color:red'>%s</span></html>", ex.getMessage()));
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    // MAIN dentro de esta clase
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Banco - Plazo Fijo");
            frame.setContentPane(new problema3().getPanel1());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); // Centrar en pantalla
            frame.setVisible(true);
        });
    }
}
