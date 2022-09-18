package com.example.inte2512_group4.Model;

import com.example.inte2512_group4.Controller.CustomerController;
import com.example.inte2512_group4.Controller.ItemController;
import com.example.inte2512_group4.Controller.Router;
import com.example.inte2512_group4.Controller.ScreenController;
import com.example.inte2512_group4.Main;
import com.example.inte2512_group4.Model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TableView {

    private Label createTextField(String value,int width){
        Label text = new Label(value);
        if(value.equals("")){
            text.setText("None");
        }
        text.setPrefWidth(width);
        text.setStyle("-fx-border-color: black;");
        text.setPadding(new Insets(5,5,5,5));
        return text;
    }
    private Label createTitle(String value,int width){
        Label text = new Label(value);
        if(value.equals("")){
            text.setText("None");
        }
        text.setPrefWidth(width);
        text.setStyle("-fx-border-color: black; -fx-font-weight: Bold ;");
        text.setPadding(new Insets(5,5,5,5));
        return text;
    }
    private Button createRentButton(Item item){
        Button btn = new Button("Rent");
        btn.setOnAction(event -> {
            CustomerController.selectedCustomer.addRentItem(item);
            try {
                Router.toCustomerShop(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btn.setAlignment(Pos.CENTER);
        btn.setPrefWidth(55);
        return btn;
    }
    private Button createReturnButton(Item item) {
        Button btn = new Button("Return");
        btn.setOnAction(event -> {
            CustomerController.selectedCustomer.returnItem(item);
            try {
                Router.toCustomerProfile(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Returned");
        });
        btn.setAlignment(Pos.CENTER);
        btn.setPrefWidth(55);
        return btn;
    }
    private Button createItemEditButton(Item item){
        Button btn = new Button("Edit");
        btn.setOnAction(event -> {
            try {
                ItemController.selectedItem = item;
                Router.toUpdateItem(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        btn.setAlignment(Pos.CENTER);
        btn.setPrefWidth(55);
        return btn;
    }
    public HBox createRentItemRow(Item item){
        HBox row = new HBox();
        Button button =createRentButton(item);
        HBox.setMargin(button,new Insets(5,5,0,5));
        row.getChildren().addAll( createTextField(item.getItemID(),70),createTextField(item.getItemTitle(),120),
                createTextField(item.getGenre(),80),
                createTextField(item.getLoanType(),80),createTextField(item.getRentType(),90),
                createTextField(Double.toString(item.getFee()),60));
                if(item.getStatus()){
                    row.getChildren().addAll(createTextField(Integer.toString(item.getQuantity()),70),
                            button);
                } else {
                    row.getChildren().addAll(createTextField(item.getStatusValue(),70),
                            button);
                }

        return row;
    }
    public HBox createReturnItemRow(Item item){
        HBox row = new HBox();
        Button button =createReturnButton(item);
        HBox.setMargin(button,new Insets(5,5,0,5));
        row.getChildren().addAll( createTextField(item.getItemID(),70),createTextField(item.getItemTitle(),120),
                createTextField(item.getGenre(),80),
                createTextField(item.getLoanType(),80),createTextField(item.getRentType(),90),button);
        return row;

    }
    public HBox createItemWithoutButtonRow(Item item){
        HBox row = new HBox();
        row.getChildren().addAll( createTextField(item.getItemID(),70),createTextField(item.getItemTitle(),120),
                createTextField(item.getGenre(),80),
                createTextField(item.getLoanType(),80),createTextField(item.getRentType(),90));
        return row;
    }
    public HBox createEditItemRow(Item item){
        HBox row = new HBox();
        Button button =createItemEditButton(item);
        HBox.setMargin(button,new Insets(5,5,0,5));
        row.getChildren().addAll( createTextField(item.getItemID(),70),createTextField(item.getItemTitle(),120),
                createTextField(item.getGenre(),80),
                createTextField(item.getLoanType(),80),createTextField(item.getRentType(),90),
                createTextField(Double.toString(item.getFee()),60));
        if(item.getStatus()){
            row.getChildren().addAll(createTextField(Integer.toString(item.getQuantity()),70),
                    button);
        } else {
            row.getChildren().addAll(createTextField(item.getStatusValue(),70),
                    button);
        }
        return row;
    }
    private Button createCustomerEditButton(Customer customer){
        Button btn = new Button("Edit");
        btn.setOnAction(event -> {
            try {
                CustomerController.selectedCustomer = customer;
                Router.toUpdateCustomer(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        btn.setAlignment(Pos.CENTER);
        btn.setPrefWidth(55);
        return btn;
    }

    public HBox createCustomerRow(Customer customer){
        HBox row = new HBox();
        Button button =createCustomerEditButton(customer);
        HBox.setMargin(button,new Insets(5,5,0,5));
        row.getChildren().addAll( createTextField(customer.getCustomerID(),70),createTextField(customer.getCustomerName(),120),
                createTextField(customer.getCustomerType(),80),
                createTextField(customer.getPhone(),80),createTextField(customer.getAddress(),130),button);
        return row;
    }


    //Generate Item List (rent,return,edit,none)
    public VBox createItemFromListContainer(ArrayList<Item> list,String type){
        VBox container = new VBox();
        HBox title = new HBox();
        title.getChildren().addAll(createTitle("ID",70),createTitle("Title",120),
                createTitle("Genre",80),
                createTitle("Loan Type",80),createTitle("Rent Type",90)
                );

        if(type.equals("rent")){
            title.getChildren().addAll(createTitle("Fee",60),
                    createTitle("Quantity",70));
            container.getChildren().add(title);
            for(Item item: list){
                container.getChildren().add(createRentItemRow(item));
            }
        }else if(type.equals("return")){
            container.getChildren().add(title);
            for(Item item: list){
                container.getChildren().add(createReturnItemRow(item));
            }
        } else if (type.equals("edit")) {
            title.getChildren().addAll(createTitle("Fee",60),
                    createTitle("Quantity",70));
            container.getChildren().add(title);
            for (Item item:list){
                container.getChildren().add(createEditItemRow(item));
            }
        }
        if(type.equals("none")){
            container.getChildren().add(title);
            for(Item item: list){
                container.getChildren().add(createItemWithoutButtonRow(item));
            }
        }

        return container;
    }
    //GenerateCustomerList
    public VBox createCustomerContainerFromList(ArrayList<Customer> customers){
        VBox container = new VBox();
        HBox title = new HBox();
        title.getChildren().addAll( createTitle("ID",70),createTitle("Name",120),
                createTitle("Type",80),
                createTitle("Phone",80),createTitle("Address",130));
        container.getChildren().add(title);
        for(Customer cus: customers){
            container.getChildren().add(createCustomerRow(cus));
        }
        return container;
    }



}
