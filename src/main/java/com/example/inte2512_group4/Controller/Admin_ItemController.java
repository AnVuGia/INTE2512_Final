package com.example.inte2512_group4.Controller;


import com.example.inte2512_group4.Model.Item;
import com.example.inte2512_group4.Model.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Admin_ItemController {

    @FXML Button sortButton;
    @FXML
    private TextField findItem_Field;
    @FXML
    private ChoiceBox<String> find_Item_ChoiceBox;
    @FXML
    private ScrollPane item_container;
    @FXML Button ascdButton;
    @FXML Button IDsort;
    private TableView tableView = new TableView();

    @FXML public void initialize(){
        loadFindItemType();
        item_container.setContent(showTable());
    }
    ObservableList list1 = FXCollections.observableArrayList();
    @FXML private void loadFindItemType(){
        String[] type = {"Name", "ID"};
        list1.removeAll(list1);
        list1.addAll(type);
        find_Item_ChoiceBox.getItems().addAll(list1);
        find_Item_ChoiceBox.setValue(type[0]);
    }
    public VBox showTable(){
        return tableView.createItemFromListContainer(ItemController.items,"edit");
    }
    public void findItemButtonHandler(ActionEvent e){
        String search_type = find_Item_ChoiceBox.getValue();
        String search_value = findItem_Field.getText().trim();
        ArrayList<Item> item_list = new ArrayList<>();
        boolean isValid = false;
        if(search_value.equals("")){
            item_container.setContent(showTable());
            return;
        }
        if(search_type.equals("Name")){
            for(Item item: ItemController.items){
                if (item.getItemTitle().trim().toUpperCase().contains(search_value.toUpperCase())){
                    item_list.add(item);
                    isValid = true;
                }
            }
        } else if(search_type.equals("ID")){
            for(Item item: ItemController.items){
                if (item.getItemID().trim().toUpperCase().contains(search_value.toUpperCase())){
                    item_list.add(item);
                    isValid = true;
                }
            }
        }
        if (isValid){
            item_container.setContent(tableView.createItemFromListContainer(item_list,"edit"));
        } else {
            //err handling
            item_container.setContent(new Label("No item match the search condition"));
        }
    }
    private ArrayList<Item> sortedItemAvailable(String status){
        ArrayList<Item> avail = new ArrayList<>();
        ArrayList<Item> not_avail = new ArrayList<>();
        for(Item item: ItemController.items){
            if(item.getStatus()){
                avail.add(item);
            } else{
                not_avail.add(item);
            }
        }
        if(status.equals("available")) return avail;
        else return not_avail;
    }
    private String status = "available";
    @FXML private void onSort(ActionEvent e){
        if(status.equals("available")){
            item_container.setContent(tableView.createItemFromListContainer(sortedItemAvailable("available"),"edit"));
            sortButton.setText("Available");
            status = "not_avail";
        } else if(status.equals("not_avail")){
            sortButton.setText("Not Available");
            item_container.setContent(tableView.createItemFromListContainer(sortedItemAvailable("not_avail"),"edit"));
            status = "none";
        }  else if(status.equals("none")){
            sortButton.setText("Sort By Status");
            item_container.setContent(tableView.createItemFromListContainer(ItemController.items,"edit"));
            status = "available";
        }
    }
    @FXML private void toAddItem(ActionEvent e) throws IOException {
        Router.toAddItem(e);
    }
    @FXML private void onBack(ActionEvent e) throws IOException {
        Router.toAdminUI(e);
    }
    private String state_name = "Ascend";
    private String state_id =  "Ascend";
    @FXML private void onNameSort(ActionEvent e){
        if (state_name.equals("Ascend")){
            item_container.setContent(tableView.createItemFromListContainer(ItemController.titleAsc(),"edit"));
            ascdButton.setText("Ascending");
            state_name = "Descend";
            ItemController.IdAsc();
        } else if (state_name.equals("Descend")){
            item_container.setContent(tableView.createItemFromListContainer(ItemController.titleDesc(),"edit"));
            ascdButton.setText("Descending");
            state_name = "Normal";
            ItemController.IdAsc();
        } else if (state_name.equals("Normal")){
            item_container.setContent(tableView.createItemFromListContainer(ItemController.IdAsc(),"edit"));
            ascdButton.setText("Sort Item By Name");
            state_name = "Ascend";
        }

    }
    @FXML private void onIdSort(ActionEvent e){
        if (state_id.equals("Ascend")){
            item_container.setContent(tableView.createItemFromListContainer(ItemController.IdAsc(),"edit"));
            IDsort.setText("ID Ascending");
            state_id = "Descend";
        } else if (state_id.equals("Descend")){
            item_container.setContent(tableView.createItemFromListContainer(ItemController.IdDesc(),"edit"));
            IDsort.setText("ID Descending");
            state_id = "Ascend";
            ItemController.IdAsc();
        }
    }
}
