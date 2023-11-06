module com.example.sistemafarmacia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sistemafarmacia to javafx.fxml;
    exports com.example.sistemafarmacia;
}