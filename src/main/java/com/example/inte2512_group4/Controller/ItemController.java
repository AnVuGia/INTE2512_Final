package com.example.inte2512_group4.Controller;



import com.example.inte2512_group4.Model.Customer;
import com.example.inte2512_group4.Model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.example.inte2512_group4.Controller.ErrorHandlerController.*;


public class ItemController{
    @FXML private TextField titleField;
    @FXML private ChoiceBox<String> rentTypeField;
    @FXML private ChoiceBox<String> loanTypeField;
    @FXML private TextField quantityField;
    @FXML private TextField feeField;
    @FXML private ChoiceBox<String> status;
    @FXML private ChoiceBox<String> genreChoice;
    @FXML
    private TextField searchField;
    @FXML
    private TextField itemID;
    @FXML
    private TextField title;

    @FXML
    private TextField quantity;
    @FXML
    private TextField fee;
    @FXML
    private Label updateSuc;
    @FXML
    private ChoiceBox choice;
    @FXML private TextField yearField;


    @FXML
    public void initialize(){
        //onload fxml file
        if(genreChoice != null){
            loadStatusChoiceBox();
            loadGenreChoiceBox();
            loadLoanType();
            loadRentTypeChoiceBox();
        }
        if(title != null){
            loadStatusChoiceBox();
            setItemFieldDetails(selectedItem);
        }
    }
    public static ArrayList<Item> items = new ArrayList<>();
    public static ArrayList<Item> itemList = items;
    private ArrayList<Item> itemListSortedID;
    private ArrayList<Item> itemListSortedName;
    public static Item selectedItem;

    ObservableList list = FXCollections.observableArrayList();
    ObservableList list1 = FXCollections.observableArrayList();
    ObservableList list2 = FXCollections.observableArrayList();
    ObservableList list3 = FXCollections.observableArrayList();
    private void loadStatusChoiceBox(){
        list.removeAll(list);
        list.addAll("True", "False");
        status.getItems().addAll(list);
        status.setValue("True");
    }
    //ALLOW ADMIN TO CHOOSE GENRE
    @FXML private void loadGenreChoiceBox(){
        String[] genre = {"None","Action", "Horror", "Drama", "Comedy"};
        list1.removeAll(list1);
        list1.addAll(genre);
        genreChoice.getItems().addAll(list1);
        genreChoice.setValue(genre[0]);
    }
    //ALLOW ADMIN TO CHOOSE RENT TYPE
    @FXML private void loadRentTypeChoiceBox(){
        String[] rentType = {"Record", "DVD", "Game"};
        list2.removeAll(list2);
        list2.addAll(rentType);
        rentTypeField.getItems().addAll(list2);
        rentTypeField.setValue(rentType[1]);
    }
    //ALLOW ADMIN TO CHOOSE LOAN TYPE
    @FXML private void loadLoanType(){
        String[] loanType = {"2-day", "1-week"};
        list3.removeAll(list3);
        list3.addAll(loanType);
        loanTypeField.getItems().addAll(list3);
        loanTypeField.setValue(loanType[0]);
    }

    Comparator<Item> c = new Comparator<Item>() {
        public int compare(Item I1, Item I2){
            return I1.getItemID().compareTo(I2.getItemID());
        }
    };

    Comparator<Item> c1 = new Comparator<Item>() {
        public int compare(Item I1, Item I2){
            return I1.getItemTitle().compareTo(I2.getItemTitle());
        }
    };
//GET THE INTERESTED ITEM'S ATTRIBUTES AND SHOW IT TO THE ADMIN
    private void setItemFieldDetails(Item foundItem){
        itemID.setText(foundItem.getItemID());
        title.setText(foundItem.getItemTitle());
        rentTypeField.setValue(foundItem.getRentType());
        loanTypeField.setValue(foundItem.getLoanType());
        genreChoice.setValue(foundItem.getGenre());
        quantity.setText(String.valueOf(foundItem.getQuantity()));
        fee.setText(String.valueOf( foundItem.getFee()));
        status.setValue(String.valueOf(foundItem.getStatus()));

    }
    //ALLOW ADMIN TO DELETE ITEM
    @FXML private void onDelete(ActionEvent e) throws IOException {
        for(Customer cus: CustomerController.customers){
            cus.removeItem(selectedItem);
        }
        items.remove(selectedItem);
        Router.toAdminUI(e);
    }
    //ERROR HANDLER FOR ADDING ITEM
    @FXML private void addItemHandler(ActionEvent e) throws IOException {
        String title = titleField.getText();
        String rentType = rentTypeField.getValue();
        String loanType = loanTypeField.getValue();
        String genre = genreChoice.getValue();
        boolean stat = status.getValue().toLowerCase().equals("true") ? true : false;
        String year = yearField.getText();
        String ID = generateNewID() + "-"+year;
        if(rentType.equals("Game")){
            genre = "None";
        }if (!checkItemFee(feeField.getText())){
            errorPopup("Fee must be a double");
        }if (!checkItemQuantity(quantityField.getText())){
            errorPopup("Quantity must be an integer");
        }if (!checkItemYear(yearField.getText())){
            errorPopup("Year can only contains number");
        }if (checkItemQuantity(quantityField.getText()) && checkItemFee(feeField.getText()) && checkItemYear(yearField.getText())) {
            items.add(Item.createNewItem(ID, title, rentType, loanType, genre, Integer.parseInt(quantityField.getText()), Double.parseDouble(feeField.getText()),
                    stat));
            Router.toAdminItem(e);
        }
    }


//AUTO ID GENERATOR
    private String generateNewID(){
        String ID="";
        String lastID = items.get(items.size()-1).getItemID();

        String[] arr = lastID.split("-");
        System.out.println(arr[0]);
        String numberOnly= arr[0].replaceAll("[^0-9]", "");
        int temp_num = Integer.parseInt(numberOnly) +1;
        if(temp_num < 10){
            ID += "I00" + temp_num;
        } else if (temp_num < 100) {
            ID += "I0" + temp_num;
        }else if (temp_num <= 999) {
            ID += "I" + temp_num;
        }
        System.out.println(ID);
        return ID;
    }



    /*@FXML
    public void searchItem(ActionEvent event) {
        String idNameChoice = (String) choice.getSelectionModel().getSelectedItem();
        String key = searchField.getText();
        if (idNameChoice.toLowerCase(Locale.ROOT).equals("id")) {

            Collections.sort(itemList, c);
            itemListSortedID = itemList;
            try{
                Item foundItem = itemListSortedID.get(Collections.binarySearch(itemListSortedID, new Item(key,null,null,null,null,0,0,false),c));
                setItemFieldDetails(foundItem);}
            catch(Exception e){
                errorLabel.setText("Sorry, we don't have that item");
            }

        }
        //customerListSortedID.get(Collections.binarySearch(customerListSortedID, new Customer(key,null,null,null,null),c));
        else if (idNameChoice.toLowerCase(Locale.ROOT).equals("name")) {

            Collections.sort(itemList, c1);
            itemListSortedName = itemList;
            try{
                Item foundItem = itemListSortedName.get(Collections.binarySearch(itemListSortedName, new Item(null,key,null,null,null,0,0,false),c1));
                setItemFieldDetails(foundItem);}
            catch(Exception e){
                errorLabel.setText("Sorry, we don't have that item");
            }

        }
    }*/
    //UPDATE BUTTON HANDLER AND APPROPIATE ERROR HANDLING
    @FXML private void updateItemHandler(ActionEvent event) throws IOException {
        String title_value = title.getText();
        String rentType_value = rentTypeField.getValue();
        String loanType_value = loanTypeField.getValue();
        String genre_value = genreChoice.getValue() ;
        boolean stat = status.getValue().toLowerCase().equals("true")  ? true : false;
        if (!checkItemFee(fee.getText())){
            errorPopup("Fee must be a double");
        }if (!checkItemQuantity(quantity.getText())) {
            errorPopup("Quantity must be an integer");
        }if (checkItemQuantity(quantity.getText()) && checkItemFee(fee.getText())) {
            selectedItem.updateItem(selectedItem.getItemID(), title_value, rentType_value, loanType_value, genre_value, Integer.parseInt(quantity.getText()),
                    Double.parseDouble(fee.getText()), stat);
            updateSuc.setVisible(true);
        }
    }
    //SUPPORTING FUNCTION FOR TITLE SORTING (IN TERMS OF ASCENDING) IN ADMIN_ITEM CONTROLLER
    public static ArrayList<Item> titleAsc(){
        ArrayList<Item> temp = new ArrayList<>();
        temp = ItemController.items;
        Collections.sort(temp, Item.sortTitle);
        return temp;
    }
    //SUPPORTING FUNCTION FOR TITLE SORTING (IN TERMS OF DESCENDING) IN ADMIN_ITEM CONTROLLER
    public static ArrayList<Item> titleDesc(){
        ArrayList<Item> temp = new ArrayList<>();
        temp = ItemController.items;
        Collections.sort(temp, Collections.reverseOrder(Item.sortTitle));
        return temp;
    }
    //SUPPORTING FUNCTION FOR ID SORTING (IN TERMS OF DESCENDING) IN ADMIN_ITEM CONTROLLER
    public static ArrayList<Item> IdDesc(){
        ArrayList<Item> temp = new ArrayList<>();
        temp = ItemController.items;
        Collections.sort(temp, Collections.reverseOrder(Item.sortID));
        return temp;
    }
    //SUPPORTING FUNCTION FOR ID SORTING (IN TERMS OF ASCENDING) IN ADMIN_ITEM CONTROLLER
    public static ArrayList<Item> IdAsc(){
        ArrayList<Item> temp = new ArrayList<>();
        temp = ItemController.items;
        Collections.sort(temp, Item.sortID);
        return temp;
    }
    @FXML private void onBack(ActionEvent e) throws IOException {
        if(LogIn_Controller.isAdmin){
            Router.toAdminItem(e);
        } else {
            Router.toCustomerUI(e);
        }
    }
}
