package com.example.sistemafarmacia;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * La clase RegistroClienteController representa el controlador de RegistroClienteView e implementa
 * el método Initializable, aqui se le dan las funcionalidades a los elementos de la vista.
 */
public class RegistroClienteController implements Initializable {

    private ObservableList<Cliente> clientes; //Crea el arreglo de clientes
    private ObservableList<Domicilio> domicilios;
    @FXML private ListView<Cliente> lstClientes; //Enlaza la lista de clientes con la ListView de scene builder
    @FXML private ListView<Domicilio> lstDomicilios;
    @FXML private TextField txtNombre;//Para acceder a los textfield de la ventana
    @FXML private TextField txtApellido;
    @FXML private TextField txtEdad;
    @FXML private TextField txtNumeroContacto;
    @FXML private TextField txtNumeroClienteAmigo;
    @FXML private TextField txtCalle;
    @FXML private TextField txtCP;
    @FXML private TextField txtColonia;
    @FXML private TextField txtCruzamientos;
    @FXML private Button btnGuardar;
    @FXML private Button btnActualizar;
    @FXML private Button btnEliminar;

    private ArrayList<String> errores;
    private final String NOMBRE_ARCHIVO = "clientes.data";

    /**
     * Representa al constructor de la clase e inicializa la variable errores. Se
     * está creando una nueva lista vacía para almacenar cadenas de texto.
     */
    public RegistroClienteController(){
        //Aun no existen los componentes de UI dentro de este constructor
        errores = new ArrayList<String>();
    }

    /**
     *Inicializa los datos de la pantalla, configura las propiedades de los elementos y
     *estable los valores iniciales para los controles.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientes = FXCollections.observableArrayList();// se crea el nuevo objeto "clientes"
        lstClientes.setItems(clientes); //envia la lista creada a la pantalla

        //lectura del archivo
        try{
            ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO));
            while(true){
                clientes.add((Cliente) flujoEntrada.readObject());
            }
        } catch (EOFException e){
            System.out.println("Fin de archivo");
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        lstClientes.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Cliente>() {

                    /**
                     * Permite responder a los cambios de propiedades observables para actualizar o
                     * eliminar a un Cliente.
                     */
                    @Override
                    public void changed(ObservableValue<? extends Cliente> arg0,
                                        Cliente clienteAnterior,
                                        Cliente clienteSeleccionado) {

                        if(clienteSeleccionado != null) {
                            System.out.println("Se seleccionó un elemento de la lista");
                            System.out.println("Anterior: " + clienteAnterior);
                            System.out.println("Nuevo: " + clienteSeleccionado);

                            txtNombre.setText(clienteSeleccionado.getNombre());
                            txtApellido.setText(clienteSeleccionado.getApellido());
                            txtEdad.setText(String.valueOf(clienteSeleccionado.getEdad()));
                            txtCalle.setText(clienteSeleccionado.getDomicilio().getCalle());
                            txtCP.setText(String.valueOf(clienteSeleccionado.getDomicilio().getCp()));
                            txtColonia.setText(clienteSeleccionado.getDomicilio().getColonia());
                            txtCruzamientos.setText(clienteSeleccionado.getDomicilio().getCruzamientos());
                            txtNumeroContacto.setText(String.valueOf(clienteSeleccionado.getNumeroContacto()));
                            txtNumeroClienteAmigo.setText(String.valueOf(clienteSeleccionado.getNumeroClienteAmigo()));

                            btnGuardar.setDisable(true);
                            btnActualizar.setDisable(false);
                            btnEliminar.setDisable(false);
                        }
                    }
                }
        );

    }

    /**
     *Guarda los datos introducidos por el usuario, se llama el método
     *validar(); para validar que los datos introducidos sean correctos, permite escribir
     *y guardar en el archivo, guardar los datos en el arreglo y limpiar pantalla.
     */
    @FXML
    public void guardar() {
        System.out.println("Guardar usuario");
        //VALIDACION DE LOS DATOS
        validar();
        if(errores.size()>0){
                String cadenaErrores = "";

                for (int i=0 ; i<errores.size() ; i++ )
                    cadenaErrores+= errores.get(i)+"\n";
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Error");
            mensaje.setHeaderText("Se encontraron los siguentes errores:");
            mensaje.setContentText(cadenaErrores);
            mensaje.initOwner(Main.ventana);
            mensaje.show();
            return;
        }
        //Escribir en el archivo
        Cliente cliente = new Cliente(txtNombre.getText(),txtApellido.getText(),
                Integer.parseInt(txtEdad.getText()), new Domicilio(txtCalle.getText(),
                Integer.parseInt(txtCP.getText()), txtColonia.getText(), txtCruzamientos.getText()) ,
                Long.parseLong(txtNumeroContacto.getText()), Long.parseLong(txtNumeroClienteAmigo.getText())
        );
        clientes.add(cliente);

        //CAPTURA DE DATOS DEL CLIENTE
        guardarEnArchivo();
        limpiar();
    }

    /**
     * Este método permite que los datos introducidos sean guardados en el archivo
     * punto data previamente creada para los clientes.
     */
    public void guardarEnArchivo(){
        try{
            ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO));
            for(int i=0 ;i<clientes.size(); i++){
                flujoSalida.writeObject(clientes.get(i));
            }
            flujoSalida.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Este método tiene la tarea de limpiar los campos del formulario para que de este
     * modo se puedan introducir nuevos datos.
     */
    @FXML void limpiar(){
        System.out.println("Limpiar usuarios");
                txtNombre.setText(null);
                txtApellido.setText(null);
                txtEdad.setText(null);
                txtCalle.setText(null);
                txtCP.setText(null);
                txtColonia.setText(null);
                txtCruzamientos.setText(null);
                txtNumeroContacto.setText(null);
                txtNumeroClienteAmigo.setText(null);

                btnGuardar.setDisable(false);
                btnActualizar.setDisable(true);
                btnEliminar.setDisable(true);

                lstClientes.getSelectionModel().clearSelection();
    }

    /**
     * Representa la actualización de datos del cliente en dado caso de que estos
     * requieran una modificación, permite rellenar nuevamente los campos y guardar esos
     * nuevos datos en el archivo punto data.
     */
   @FXML public void actualizar(){

        clientes.set(
                lstClientes.getSelectionModel().getSelectedIndex(),
                new Cliente(txtNombre.getText(),txtApellido.getText(),
                Integer.parseInt(txtEdad.getText()), new Domicilio(txtCalle.getText(),
                Integer.parseInt(txtCP.getText()), txtColonia.getText(), txtCruzamientos.getText()) ,
                Long.parseLong(txtNumeroContacto.getText()), Long.parseLong(txtNumeroClienteAmigo.getText()))
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
    @FXML public void eliminar(){
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Eliminar");
        mensaje.setHeaderText("Se eliminará un registro");
        mensaje.setContentText("¿Desea continuar con la acción?");
        mensaje.initOwner(Main.ventana);
        Optional<ButtonType> resultado = mensaje.showAndWait();

        if(resultado.get() == ButtonType.OK){
            clientes.remove(lstClientes.getSelectionModel().getSelectedIndex());
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

        //Cuando el usuario no ha ingresado nada
        if(txtNombre.getText().isEmpty())
            errores.add("Nombre es un campo obligatorio.");

        if(txtApellido.getText().isEmpty())
            errores.add("Apellido es un campo obligatorio.");

        if(txtEdad.getText().isEmpty())
            errores.add("Edad es un campo obligatario.");

        if(txtNumeroContacto.getText().isEmpty())
            errores.add("Número de contacto es un campo obligatario.");

        if(txtNumeroClienteAmigo.getText().isEmpty())
            errores.add("Número de Cliente Amigo es un campo obligatario.");

        if(txtCalle.getText().isEmpty())
            errores.add("Calle es un campo obligatorio.");

        if(txtCP.getText().isEmpty())
            errores.add("Codigo Postal es un campo obligatorio.");

        if(txtColonia.getText().isEmpty())
            errores.add("Colonia es un campo obligatorio.");

        if(txtCruzamientos.getText().isEmpty())
            errores.add("Cruzamientos es un campo obligatorio.");

        //VALIDACION PARA QUE LA EDAD SEA NUMÉRICO
        if (!txtEdad.getText().isEmpty()){
            try{
                Integer.parseInt(txtEdad.getText());
            }
            catch (NumberFormatException e){
                //e.printStackTrace();
                errores.add("El campo edad debe ser numérico");
            }

        }

        //VALIDACIÓN PARA QUE EL NUMERO DE CONTACTO SEA NUMÉRICO
        if (!txtNumeroContacto.getText().isEmpty()){
            try{
                Long.parseLong(txtNumeroContacto.getText());
            }
            catch (NumberFormatException e){
                //e.printStackTrace();
                errores.add("El campo número de contacto debe ser numérico");
            }

        }

        //VALIDACIÓN PARA QUE EL NUMERO DE CLIENTE AMIGO SEA NUMÉRICO
        if (!txtNumeroClienteAmigo.getText().isEmpty()){
            try{
                Long.parseLong(txtNumeroClienteAmigo.getText());
            }
            catch (NumberFormatException e){
                //e.printStackTrace();
                errores.add("El campo número de cliente amigo debe ser numérico");
            }

        }

        //VALIDACIÓN PARA QUE EL CODIGO POSTAL SEA NUMÉRICO
        if (!txtCP.getText().isEmpty()){
            try{
                Integer.parseInt(txtCP.getText());
            }
            catch (NumberFormatException e){
                //e.printStackTrace();
                errores.add("El campo codigo postal debe ser numérico");
            }

        }

        //VALIDACION PARA QUE EL NÚMERO DE TELEFONO TENGA 10 DIGITOS
        String patronNumeroCliente = "[0-9]{10}";
        if (!Pattern.matches(patronNumeroCliente, txtNumeroContacto.getText()))
            errores.add("Número de contacto inválido");

        //VALIDACION PARA QUE EL NÚMERO DE CLIENTE AMIGO TENGA 5 DIGITOS
        String patronClienteAmigo = "[0-9]{5}";
        if (!Pattern.matches(patronClienteAmigo, txtNumeroClienteAmigo.getText()))
            errores.add("Número de Cliente amigo inválido");



        //VALIDACION PARA QUE CP TENGA 5 DIGITOS
        String patronCP= "[0-9]{5}";
        if (!Pattern.matches(patronCP, txtCP.getText()))
            errores.add("Número de contacto inválido");
    }
}