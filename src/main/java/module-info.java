module com.example.inte2512_group4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.inte2512_group4 to javafx.fxml;
    opens com.example.inte2512_group4.Controller to javafx.fxml;
    exports com.example.inte2512_group4;
}