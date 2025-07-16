package prob3;

import exceptions.CedulaInvalidaException;
import exceptions.MontoInvalidoException;
import exceptions.NombreInvalidoException;
import java.io.IOException;


public class Logica {
    private String nombre;
    private String cedula;
    private int montoADepositar;
    private int plazoEnMeses;


    public Logica() {
        this.nombre = "";
        this.cedula = "";
        this.montoADepositar = 0;
        this.plazoEnMeses = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public int getMontoADepositar() {
        return montoADepositar;
    }

    public void setNombre(String nombre) throws NombreInvalidoException { //Demasiados filtros
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreInvalidoException("El nombre no puede ser nulo o vacío.");
        }
        if (nombre.length() < 3 || nombre.length() > 50) {
            throw new NombreInvalidoException("El nombre debe tener entre 3 y 50 caracteres.");
        }
        if (nombre.matches(".*\\d.*")) {
            throw new NombreInvalidoException("El nombre no puede contener números.");
        }
        if (!nombre.matches("[a-zA-Z]+( [a-zA-Z]+)*")) {
            throw new NombreInvalidoException("El nombre no puede contener caracteres especiales.");
        }
        if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(\\s+[A-Za-zÁÉÍÓÚáéíóúÑñ]+)+$")) {
            throw new NombreInvalidoException("Debe ingresar al menos un nombre y un apellido, usando solo letras.");
        }
        if (nombre.matches(".*(.)\\1{3,}.*")) {
            throw new NombreInvalidoException("El nombre no debe contener la misma letra muchas veces seguidas.");
        }
        if (nombre.matches(".*\\s+\\s+.*")) {
            throw new NombreInvalidoException("El nombre no debe contener espacios en blanco.");
        }
        String[] palabras = nombre.trim().split("\\s+");
        for (String palabra : palabras) {
            if (!palabra.matches("[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+")) {
                throw new NombreInvalidoException("Cada palabra debe iniciar con mayúscula seguida de minúsculas. Error en: " + palabra);
            }
        }

        this.nombre = nombre; //si pasa por los filtros, se registra el nombre
    }

    public int getPlazoEnMeses() {
        return plazoEnMeses;
    }

    public void setCedula(String cedula) throws CedulaInvalidaException { //Esto tiene un montón de filtros
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new CedulaInvalidaException("La cédula no puede estar vacía.");
        }
        // Nacional: formato típico 8-2345-6789
        // Extranjero: formato E-123-456
        if (!(cedula.matches("\\d+-\\d+-\\d+") || cedula.matches("E-\\d+-\\d+"))) {
            throw new CedulaInvalidaException("Formato de cédula inválido. Ejemplos válidos: 8-1234-5678 o E-123-456.");
        }
        if (cedula.matches("\\d+-.*") && cedula.startsWith("0")) {
            throw new CedulaInvalidaException("La cédula nacional no puede comenzar con 0.");
        }
        if (cedula.startsWith("E-")) {
            String[] partes = cedula.split("-");
            if (partes.length < 2 || partes[1].startsWith("0")) {
                throw new CedulaInvalidaException("La cédula extranjera no puede comenzar con 0 después de la 'E-'.");
            }
        }

        String noCeros = cedula.replaceAll("[^0-9]", ""); // quita letras y guiones
        if (noCeros.matches("0+")) {
            throw new CedulaInvalidaException("La cédula no puede contener solo ceros.");
        }

        this.cedula = cedula; //si sale bien, se registra
    }

    public void setPlazoEnMeses(int meses) throws IllegalArgumentException {
        if (meses != 12 && meses != 24 && meses != 36 && meses != 48 && meses != 60) {
            throw new IllegalArgumentException("Plazo inválido. Debe ser 12, 24, 36, 48 o 60 meses.");
        }
        this.plazoEnMeses = meses;
    }


    public void setMontoADepositar(int montoADepositar) throws MontoInvalidoException {
        if (montoADepositar < 2000) {
            throw new MontoInvalidoException("El monto a depositar no puede ser menor a B/2000.00.");
        }

        this.montoADepositar = montoADepositar;
    }

    public double calcularMontoAcumulado() {
        double tasa = switch (plazoEnMeses) {
            case 12 -> 0.04;
            case 24 -> 0.045;
            case 36 -> 0.0455;
            case 48 -> 0.0475;
            case 60 -> 0.05;
            default -> 0.0;
        };

        int anios = plazoEnMeses / 12;
        return montoADepositar * Math.pow(1 + tasa, anios);
    }
}