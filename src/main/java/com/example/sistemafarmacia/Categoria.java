package com.example.sistemafarmacia;

import java.io.Serializable;

/**
 * Representa a la categoria de los productos e implementa el método Serealizable para que los objetos
 * de esta clase puedan convertirse en una secuencia de bytes para poder almacenarlos
 * en los archivos punto data. Contiene los siguientes atributos: nombreCategoria.
 */
public class Categoria implements Serializable {
    String nombreCategoria;

    public Categoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Convierte un valor o el objeto en una representación de cadena de texto para mostrar
     * en la interfaz de usuario.
     * @return Retorna la cadena de texto con los datos ingresados de la categoria (nombreCategoria).
     */
    @Override
    public String toString() {
        return nombreCategoria;
    }
}
