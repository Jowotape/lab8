package prob3;

import exceptions.CedulaInvalidaException;
import exceptions.MontoInvalidoException;
import exceptions.NombreInvalidoException;

import javax.swing.*;
import java.awt.*;

public class Problema3 extends JFrame {
    private JPanel panel1;
    private JTextField campoNombre;
    private JTextField campoCedula;
    private JTextField campoMonto;
    private JComboBox<Integer> comboPlazo;
    private JButton botonCalcular;
    private JLabel lbResultado;
    private JLabel lbTitulo;

    public Problema3() {
        // Configuramos el JFrame
        setTitle("Banco - Plazo Fijo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Para cerrar solo esta ventana
        setSize(450, 350);
        setLocationRelativeTo(null); // Centrar pantalla

        // Panel principal con layout vertical
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margen

        // Título
        lbTitulo = new JLabel("Banco - Plazo Fijo");
        lbTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lbTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(lbTitulo);

        panel1.add(Box.createRigidArea(new Dimension(0, 10))); // espacio

        // Campo Nombre
        panel1.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panel1.add(campoNombre);

        // Campo Cédula
        panel1.add(new JLabel("Cédula:"));
        campoCedula = new JTextField();
        panel1.add(campoCedula);

        // Campo Monto
        panel1.add(new JLabel("Monto a depositar:"));
        campoMonto = new JTextField();
        panel1.add(campoMonto);

        // Combo Plazo
        panel1.add(new JLabel("Plazo en meses:"));
        comboPlazo = new JComboBox<>();
        comboPlazo.addItem(12);
        comboPlazo.addItem(24);
        comboPlazo.addItem(36);
        comboPlazo.addItem(48);
        comboPlazo.addItem(60);
        panel1.add(comboPlazo);

        panel1.add(Box.createRigidArea(new Dimension(0, 10))); // espacio

        // Botón calcular
        botonCalcular = new JButton("Calcular");
        botonCalcular.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(botonCalcular);

        panel1.add(Box.createRigidArea(new Dimension(0, 10)));

        // Label Resultado
        lbResultado = new JLabel("");
        lbResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(lbResultado);

        // Color inicial de resultado en rojo para errores
        lbResultado.setForeground(Color.RED);

        // Acción botón
        botonCalcular.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String cedula = campoCedula.getText().trim();
            String montoTexto = campoMonto.getText().trim();

            if (nombre.isEmpty() || cedula.isEmpty() || montoTexto.isEmpty()) {
                lbResultado.setText("<html><span style='color:red'>Todos los campos deben estar llenos.</span></html>");
                return;
            }

            try {
                double montoADepositar = Double.parseDouble(montoTexto);
                int plazo = (Integer) comboPlazo.getSelectedItem();

                Logica logica = new Logica();
                logica.setNombre(nombre);
                logica.setCedula(cedula);
                logica.setMontoADepositar(montoADepositar);
                logica.setPlazoEnMeses(plazo);

                double resultado = logica.calcularMontoAcumulado();
                lbResultado.setForeground(Color.GREEN);
                lbResultado.setText(String.format(
                        "<html><b>Depósito válido.</b><br>Monto acumulado: B/. %.2f</html>", resultado));

            } catch (NumberFormatException ex) {
                lbResultado.setForeground(Color.RED);
                lbResultado.setText("<html><span style='color:red'>Monto inválido. Debe ser un número válido.</span></html>");
            } catch (NombreInvalidoException | CedulaInvalidaException | MontoInvalidoException | IllegalArgumentException ex) {
                lbResultado.setForeground(Color.RED);
                lbResultado.setText(String.format("<html><span style='color:red'>%s</span></html>", ex.getMessage()));
            }
        });

        // Añadimos el panel al JFrame
        setContentPane(panel1);
    }

    public JPanel getPanel1() {
        return panel1;
    }
}
