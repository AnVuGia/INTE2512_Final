package com.example.inte2512_group4.Controller;

import com.example.inte2512_group4.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//unfinished

public class ScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchScene(String URL, ActionEvent e) throws IOException {
        root = FXMLLoader.load(Main.class.getResource(URL));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchBiggerScene(String URL, ActionEvent e) throws IOException {
        root = FXMLLoader.load(Main.class.getResource(URL));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root,700,800);
        stage.setScene(scene);
        stage.show();
    }

}