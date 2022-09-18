package com.example.inte2512_group4;

import com.example.inte2512_group4.Controller.CustomerController;
import com.example.inte2512_group4.Controller.ItemController;
import com.example.inte2512_group4.Controller.ScreenController;
import com.example.inte2512_group4.Model.*;
import com.example.inte2512_group4.Model.Customer;
import com.example.inte2512_group4.Model.Item;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    File_IO fileEditor = new File_IO();
    public void mapping(){
        System.out.println("Start");
        for(Customer cus: CustomerController.customers){
            for(String rentID : cus.getRentalList_String()){
                for(Item item : ItemController.items){
                    if(rentID.equals(item.getItemID())){
                        cus.addItem(item);
                        System.out.println("Mapped");
                    }
                }
            }
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        //set up, do not touch
        CustomerController.customers.addAll(fileEditor.readFileCustomerTxtM("customers.txt"));
        ItemController.items.addAll(fileEditor.readFileItemTxtM("items.txt"));
        mapping();
        //
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Genie's Video Store");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() throws IOException {
        fileEditor.writeFileCustomerTxtM(CustomerController.customers);
        fileEditor.writeFileItemTxtM(ItemController.items);
        System.out.println("Saved!");
    }
    public static void main(String args[]){

        launch(args);
    }
}
