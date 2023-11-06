package com.example.sistemafarmacia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * La clase GeneralController representa el controlador de GeneralView e implementa el
 * método Initializable, aqui se le dan las funcionalidades a los elementos de la vista.
 * Esta es la pantalla principal que admistra las demás ventanas.
 */
public class GeneralController implements Initializable {

    @FXML private Button btnCliente;
    @FXML private Button btnInventario;
    @FXML private Button btnReporte;

    /**
     * Este método hace la llamada a la ventada del inventario cuando se realiza la acción
     * en el menú y lo muestra para poder trabajar en ella.
     * @param event abrir InventarioView.fxml
     * @throws IOException
     */
    @FXML
    public void abrirVentanaInventario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InventarioView.fxml"));
        Parent root = loader.load();

        InventarioController controller = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Inventario");
        stage.show();
    }

    /**
     * Este método hace la llamada a la ventada del registro de clientes cuando
     * se solicita la acción en el menú y lo muestra en pantalla para poder trabajar en ella.
     * @param event abrir RegistroClienteView.fxml
     * @throws IOException
     */
    @FXML
    public void abrirVentanaCliente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistroClienteView.fxml"));
        Parent root = loader.load();

        RegistroClienteController controller = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Registro de clientes");
        stage.show();
    }

    /**
     * Metodo initializable
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}




