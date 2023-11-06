package com.example.sistemafarmacia;

import java.io.Serializable;

/**
 * Representa a los productos e implementa el método Serealizable para que los objetos
 * de esta clase puedan convertirse en una secuencia de bytes para poder almacenarlos
 * en los archivos punto data. contiene los siguientes atributos: codigo, nombreProducto,
 * categoría, receta, precio, stock y fecha de vencimiento.
 */
public class Producto implements Serializable {

    private String codigo;
    private String nombreProducto;
    private Categoria categoria;
    private String receta;
    private float precio;
    private int stock;
    private String fechaVencimiento;

    /**
     * Constructor de la clase Producto que inicializa un objeto Producto con los siguientes
     * parámetros: codigo, nombreProducto, categoria, receta, precio, stock, fechaVencimiento.
     * @param codigo El código del producto.
     * @param nombreProducto El nombre del producto.
     * @param categoria La categoría a la cual pertenece el producto.
     * @param receta Receta del producto.
     * @param precio Precio del producto.
     * @param stock Canatidad en Stock del producto.
     * @param fechaVencimiento Fecha de vencimiento del producto.
     */
    public Producto(String codigo, String nombreProducto, Categoria categoria, String receta, float precio, int stock, String fechaVencimiento) {
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.categoria = categoria;
        this.receta = receta;
        this.precio = precio;
        this.stock = stock;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Convierte un valor o el objeto en una representación de cadena de texto para mostrar
     * en la interfaz de usuario.
     * @return Retorna la cadena de texto con los datos ingresados del Producto (codigo, nombreProducto, categoria, receta, precio, stock, fechaVencimiento).
     */
    @Override
    public String toString() {
        return "Producto[" +
                "Código='" + codigo + '\'' +
                ", Nombre='" + nombreProducto + '\'' +
                ", Categoría=" + categoria +
                ", Receta Médica='" + receta + '\'' +
                ", Precio=" + precio +
                ", Stock=" + stock +
                ", Fecha de Vencimiento='" + fechaVencimiento + '\'' +
                ']';
    }
}
