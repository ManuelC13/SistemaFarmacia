package com.example.sistemafarmacia;

import java.io.Serializable;

/**
 * Representa a los clientes e implementa el método Serealizable para que los objetos
 * de esta clase puedan convertirse en una secuencia de bytes para poder almacenarlos
 * en los archivos punto data. Contiene los siguientes atributos: nombre, apellido, edad,
 * domicilio, numeroContacto y numeroClienteAmigo.
 */
public class Cliente implements Serializable {

    private String nombre;
    private String apellido;
    private int edad;
    private Domicilio domicilio;
    private long numeroContacto;
    private long numeroClienteAmigo;


    /**
     * Constructor vacío para posible usos
     */
    public Cliente() {
    }

    /**
     * Constructor de la clase Producto que inicializa un objeto Producto con los siguientes
     * parámetros: nombre, apellido, edad, domicilio, numeroContacto, numeroClienteAmigo.
     * @param nombre nombre del cliente.
     * @param apellido apellido del cliente.
     * @param edad edad del cliente.
     * @param domicilio domicilio del cliente
     * @param numeroContacto número de contacto del cliente.
     * @param numeroClienteAmigo número de cliente amigo del cliente.
     */
    public Cliente(String nombre, String apellido, int edad, Domicilio domicilio, long numeroContacto, long numeroClienteAmigo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.domicilio = domicilio;
        this.numeroContacto = numeroContacto;
        this.numeroClienteAmigo = numeroClienteAmigo;
    }

    //getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public long getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(long numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public long getNumeroClienteAmigo() {
        return numeroClienteAmigo;
    }

    public void setNumeroClienteAmigo(long numeroClienteAmigo) {
        this.numeroClienteAmigo = numeroClienteAmigo;
    }

    /**
     * Convierte un valor o el objeto en una representación de cadena de texto para mostrar
     * en la interfaz de usuario.
     * @return Retorna la cadena de texto con los datos ingresados del Cliente (nombre, apellido, edad
     domicilio, numeroContacto, numeroClienteAmigo).
     */
    @Override
    public String toString() {
        return "Cliente: [" +
                "Nombre: " + nombre +
                ", Apellido: " + apellido +
                ", Edad: " + edad +
                ", Domicilio: " + domicilio +
                ", Teléfono: " + numeroContacto +
                ", Num. ClienteAmigo: " + numeroClienteAmigo + "]";
    }
}
