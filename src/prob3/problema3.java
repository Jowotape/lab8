package prob3;

import exceptions.CedulaInvalidaException;
import exceptions.MontoInvalidoException;
import exceptions.NombreInvalidoException;

import javax.swing.*;

public class problema3 {
    private JPanel panelPrincipal;
    private JTextField campoNombre;
    private JTextField campoCedula;
    private JTextField campoMonto;
    private JComboBox<Integer> comboPlazo;
    private JButton botonCalcular;
    private JLabel lbResultado;

    public problema3() {
        // Agrega los valores al ComboBox
        comboPlazo.addItem(12);
        comboPlazo.addItem(24);
        comboPlazo.addItem(36);
        comboPlazo.addItem(48);
        comboPlazo.addItem(60);

        botonCalcular.addActionListener(e -> {
            try {
                Logica logica = new Logica();

                String nombre = campoNombre.getText();
                String cedula = campoCedula.getText();
                int monto = Integer.parseInt(campoMonto.getText());
                int plazo = (Integer) comboPlazo.getSelectedItem();

                logica.setNombre(nombre);
                logica.setCedula(cedula);
                logica.setMontoADepositar(monto);
                logica.setPlazoEnMeses(plazo);

                double resultado = logica.calcularMontoAcumulado();

                lbResultado.setText(String.format("<html><b>Depósito válido.</b><br>Monto acumulado: B/. %.2f</html>", resultado));
            } catch (NombreInvalidoException | CedulaInvalidaException | MontoInvalidoException | IllegalArgumentException ex) {
                lbResultado.setText("<html><span style='color:red'>" + ex.getMessage() + "</span></html>");
            } catch (NumberFormatException ex) {
                lbResultado.setText("<html><span style='color:red'>Monto inválido. Debe ser un número.</span></html>");
            }
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
