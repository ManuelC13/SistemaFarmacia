package com.example.sistemafarmacia;

import java.io.Serializable;

/**
 * Representa al Domicilio del cliente e implementa el método Serealizable para que los objetos
 * de esta clase puedan convertirse en una secuencia de bytes para poder almacenarlos
 * en los archivos punto data. Contiene los siguientes atributos: calle, cp
 * colonia y cruzamientos.
 */
public class Domicilio implements Serializable {

    private String calle;
    private int cp;
    private String colonia;
    private String cruzamientos;

    /**
     * Constructor de la clase Domicilio que inicializa un objeto Domicilio con los siguientes
     * parámetros: calle, cp, colonia, cruzamientos.
     * @param calle calle  del cliente.
     * @param cp cp del cliente.
     * @param colonia colonia del cliente.
     * @param cruzamientos cruzamientos del cliente.
     */
    public Domicilio(String calle, int cp, String colonia, String cruzamientos) {
        this.calle = calle;
        this.cp = cp;
        this.colonia = colonia;
        this.cruzamientos = cruzamientos;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCruzamientos() {
        return cruzamientos;
    }

    public void setCruzamientos(String cruzamientos) {
        this.cruzamientos = cruzamientos;
    }

    /**
     * Convierte un valor o el objeto en una representación de cadena de texto para mostrar
     * en la interfaz de usuario.
     * @return Retorna la cadena de texto con los datos ingresados del Domicilio (calle, cp, colonia, cruzamientos).
     */
    @Override
    public String toString() {
        return "Calle: " + calle +
                ", Cruzamientos: " + cruzamientos +
                ", Colonia: " + colonia +
                ", C.P: " + cp;
    }
}
