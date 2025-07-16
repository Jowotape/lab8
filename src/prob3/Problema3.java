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
        lbResultado.setForeground(Color.RED);

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
                // Cambiado a double
                double montoADepositar = Double.parseDouble(montoTexto);
                int plazo = (Integer) comboPlazo.getSelectedItem();

                Logica logica = new Logica();
                logica.setNombre(nombre);
                logica.setCedula(cedula);
                // Cambiado a double
                logica.setMontoADepositar(montoADepositar);
                logica.setPlazoEnMeses(plazo);

                double resultado = logica.calcularMontoAcumulado();
                lbResultado.setText(String.format(
                        "<html><span style='color:green;'><b>Depósito válido.</b><br>Monto acumulado: B/. %.2f</span></html>", resultado));

            } catch (NumberFormatException ex) {
                lbResultado.setText("<html><span style='color:red'>Monto inválido. Debe ser un número válido.</span></html>");
            } catch (NombreInvalidoException | CedulaInvalidaException | MontoInvalidoException ex) {
                lbResultado.setText(String.format("<html><span style='color:red'>%s</span></html>", ex.getMessage()));
            } catch (IllegalArgumentException ex) {
                lbResultado.setText(String.format("<html><span style='color:red'>%s</span></html>", ex.getMessage()));
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

}
