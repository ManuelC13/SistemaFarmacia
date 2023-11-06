package com.example.sistemafarmacia;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 *La clase InvetarioController representa el controlador de inventarioView e implementa
 *el método Initializable, aqui se le dan las funcionalidades a los elementos de la vista
 */
public class InventarioController implements Initializable {

    private ObservableList<Categoria> categorias;
    private ObservableList<Producto> productos;
    @FXML private ComboBox<Categoria> cboCategorias;
    @FXML private ListView<Producto> lstProductos;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombreProd;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private DatePicker dpFechaVenc;
    @FXML private ToggleGroup recetaMedica;
    @FXML private RadioButton rbtSi;
    @FXML private RadioButton rbtNo;
    @FXML private Button btnGuardar;
    @FXML private Button btnActualizar;
    @FXML private Button btnEliminar;
    @FXML private AnchorPane inventario;

    private  ArrayList<String>errores;
    private final String NOMBRE_ARCHIVO = "productos.data";
    //private ObjectInputStream flujoEntrada; //lectura

    /**
     * Representa al constructor de la clase e inicializa la variable errores. Se
     * está creando una nueva lista vacía para almacenar cadenas de texto.
     */
    public InventarioController() {
        //En el constructor aun no existen los componentes del UI
        errores = new ArrayList<String>();
    }

    /**
     * Inicializa los datos de la pantalla, configura las propiedades de los elementos y
     * estable los valores iniciales para los controles.
     * @param arg0 arg 0
     * @param arg1 arg 1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //metodo que se ejecuta cuando se instanciaron todos los componentes del FXML

        categorias = FXCollections.observableArrayList();
        productos = FXCollections.observableArrayList();

        cboCategorias.setItems(categorias);
        lstProductos.setItems(productos);

        //Lectura del archivo
        try {
            ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO));
            while (true){
                productos.add((Producto) flujoEntrada.readObject());
            }
        } catch (EOFException e){
            System.out.println("Fin de archivo");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        categorias.add(new Categoria("Vitaminas"));
        categorias.add(new Categoria("Suplementos"));
        categorias.add(new Categoria("Analgésicos"));
        categorias.add(new Categoria("Cremas y ungüentos"));
        categorias.add(new Categoria("Sueros"));
        categorias.add(new Categoria("Jabones"));
        categorias.add(new Categoria("Anticepticos"));
        categorias.add(new Categoria("Anticonceptivos"));
        categorias.add(new Categoria("Productos básicos"));

        /*cboCategorias.setItems(categorias);
        lstProductos.setItems(productos);*/
        lstProductos.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Producto>(){

                    /**
                     * Permite responder a los cambios de propiedades observables para actualizar o
                     * eliminar a un Cliente.
                     * @param arg0 arg 0
                     * @param productoAnterior Producto anterior seleccionado.
                     * @param productoSeleccionado Producto Seleccionado.
                     */
                    @Override
                    public void changed(ObservableValue<? extends Producto> arg0,
                                        Producto productoAnterior,
                                        Producto productoSeleccionado) {
                        if(productoSeleccionado != null) {
                            System.out.println("Se seleccionó un elemento de la lista");
                            System.out.println("Anterior: " + productoAnterior);
                            System.out.println("Nuevo: " + productoSeleccionado);

                            txtCodigo.setText(productoSeleccionado.getCodigo());
                            txtNombreProd.setText(productoSeleccionado.getNombreProducto());
                            cboCategorias.getSelectionModel().select(productoSeleccionado.getCategoria());
                            if (productoSeleccionado.getReceta().equals("Si"))
                                rbtSi.setSelected(true);
                            else
                                rbtNo.setSelected(true);
                            txtPrecio.setText(String.valueOf(productoSeleccionado.getPrecio()));
                            txtStock.setText(String.valueOf(productoSeleccionado.getStock()));
                            dpFechaVenc.setValue(LocalDate.parse(productoSeleccionado.getFechaVencimiento()));

                            btnGuardar.setDisable(true);
                            btnActualizar.setDisable(false);
                            btnEliminar.setDisable(false);
                        }
                    }
                }
        );
    }

    /**
     * Guarda los datos introducidos por el usuario, se llama el método
     * validar(); para validar que los datos introducidos sean correctos, permite escribir
     * y guardar en el archivo, guardar los datos en el arreglo y limpiar pantalla.
     */
    @FXML
    public void guardar() {
        System.out.println("Guardar producto");
        validar();
        if (errores.size()>0){
            String cadenaErrores = "";
            for (int i=0; i<errores.size(); i++)
                cadenaErrores += errores.get(i) + "\n";
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setHeaderText("Se encontraron los siguientes errores:");
            mensaje.setContentText(cadenaErrores);
            mensaje.initOwner(Main.ventana);
            mensaje.show();
            return;
        }

        Producto producto = new Producto(
                txtCodigo.getText(),
                txtNombreProd.getText(),
                cboCategorias.getSelectionModel().getSelectedItem(),
                ((RadioButton)recetaMedica.getSelectedToggle()).getText(),
                Float.parseFloat(txtPrecio.getText()),
                Integer.parseInt(txtStock.getText()),
                dpFechaVenc.getValue().toString()
        );
        productos.add(producto);
        guardarEnArchivo();

        limpiar();
    }

    /**
     * Este método permite que los datos introducidos sean guardados en el archivo
     * punto data previamente creada para los productos.
     */
    public void guardarEnArchivo(){
        try {
            ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO));
            for(int i=0; i< productos.size(); i++)
                flujoSalida.writeObject(productos.get(i));

            flujoSalida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método tiene la tarea de limpiar los campos del formulario para que de este
     * modo se puedan introducir nuevos datos.
     */
    @FXML
    public void limpiar(){
        System.out.println("Limpiar productos");
        txtCodigo.setText(null);
        txtNombreProd.setText(null);
        cboCategorias.getSelectionModel().select(null);
        recetaMedica.selectToggle(null);
        txtPrecio.setText(null);
        txtStock.setText(null);
        dpFechaVenc.setValue(null);

        btnGuardar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);

        lstProductos.getSelectionModel().clearSelection();
    }

    /**
     * Representa la actualización de datos del producto en dado caso de que estos
     * requieran una modificación, permite rellenar nuevamente los campos y guardar esos
     * nuevos datos en el archivo punto data.
     */
    @FXML
    public void actualizar(){
        productos.set(
                lstProductos.getSelectionModel().getSelectedIndex(),
                new Producto(
                        txtCodigo.getText(),
                        txtNombreProd.getText(),
                        cboCategorias.getSelectionModel().getSelectedItem(),
                        ((RadioButton)recetaMedica.getSelectedToggle()).getText(),
                        Float.parseFloat(txtPrecio.getText()),
                        Integer.parseInt(txtStock.getText()),
                        dpFechaVenc.getValue().toString()
                )
        );

        guardarEnArchivo();
        limpiar();
    }

    /**
     * Elimina al objeto seleccionado en el formulario, despliga un mensaje de alerta
     * de confirmación para saber si en realidad se desea elimianar el registro,
     * permite guardar los datos en el archivo y limpiar los campos una vez el registro
     * haya sido eliminado.
     */
    @FXML
    public void eliminar(){
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Eliminar");
        mensaje.setHeaderText("Se eliminará un registro");
        mensaje.setContentText("¿Desea continuar con la acción?");
        mensaje.initOwner(Main.ventana);
        Optional<ButtonType> resultado = mensaje.showAndWait();
        if(resultado.get() == ButtonType.OK){
            productos.remove(lstProductos.getSelectionModel().getSelectedIndex());
            guardarEnArchivo();
            limpiar();
        }
    }

    /**
     * Permite la validación de los datos que se introducen en pantalla,
     * verfíca que se llenen los campos obligatorios, que sean del tipo de dato correcto
     * y  que cumplan ciertas propiedades.
     */
    public void validar(){
        errores.clear();
        if(txtCodigo.getText().isEmpty())
            errores.add("Código es un campo obligatorio.");

        if(txtNombreProd.getText().isEmpty())
            errores.add("Nombre es un campo obligatorio.");

        if(cboCategorias.getSelectionModel().getSelectedItem() == null)
            errores.add("Debe seleccionar una categoría.");

        if(recetaMedica.getSelectedToggle() == null)
            errores.add("Debe indicar si el producto requiere receta.");

        if(txtPrecio.getText().isEmpty())
            errores.add("Precio es un campo obligatorio.");

        if(txtStock.getText().isEmpty())
            errores.add("Stock es un campo obligatorio.");

        if(dpFechaVenc.getValue() == null)
            errores.add("Fecha de vencimiento es un campo obligatorio.");


        if(!txtPrecio.getText().isEmpty())
            try {
                Float.parseFloat(txtStock.getText());
            } catch (NumberFormatException e){
                errores.add("El campo precio debe ser numérico");
            }

        if(!txtStock.getText().isEmpty())
            try {
                Integer.parseInt(txtStock.getText());
            } catch (NumberFormatException e){
                //e.printStackTrace();
                errores.add("El campo Stock debe ser numérico");
            }

        // 7-123456-123456
        String regexCodigo = "7-[0-9]{6}-[0-9]{6}";
        if(!Pattern.matches(regexCodigo, txtCodigo.getText()))
            errores.add("Código inválido, no cumple con el patrón");
    }
}