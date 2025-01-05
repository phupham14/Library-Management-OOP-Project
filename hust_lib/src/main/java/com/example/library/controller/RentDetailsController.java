package com.example.library.controller;

import com.example.library.model.RentDetail;
import com.example.library.service.RentDetailsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;
import java.util.Optional;

public class RentDetailsController {

    @FXML
    private Button rentDetails_checkRent;
    @FXML
    private TableColumn<RentDetail, String> rentDetails_bookTitle;

    @FXML
    private TableColumn<RentDetail, Integer> rentDetails_rentlineId;

    @FXML
    private TableColumn<RentDetail, java.time.LocalDate> rentDetails_returnDate;
    @FXML
    private TableView<RentDetail> rentDetails_tableView;
    @FXML
    private TableColumn<RentDetail, String> rentDetails_rentStatus;
    @FXML
    private Button rentDetails_receiveBook; // Assuming you have this button in your FXML

    private RentDetailsService rentDetailsService;
    private RentDetail selectedRentDetail; // To store the selected row

    public void setRentId(int rentId) {
        loadRentDetails(rentId);
    }

    private void loadRentDetails(int rentId) {
        rentDetailsService = new RentDetailsService();
        List<RentDetail> rentDetails = rentDetailsService.fetchRentDetails(rentId);

        ObservableList<RentDetail> observableList = FXCollections.observableArrayList(rentDetails);
        rentDetails_tableView.setItems(observableList);

        // Set up the TableView columns
        rentDetails_bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        rentDetails_rentlineId.setCellValueFactory(new PropertyValueFactory<>("rentlineId"));
        rentDetails_returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        // Set up the rentDetails_rentStatus column
        rentDetails_rentStatus.setCellValueFactory(new PropertyValueFactory<>("rentStatus"));
        // Set up a custom cell factory to handle empty strings as null
        rentDetails_rentStatus.setCellFactory(column -> new TextFieldTableCell<>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.isEmpty()) {
                    setText(null); // Display null for empty strings or null values
                } else {
                    setText(item);
                }
            }
        });

        // Add listener for row selection
        rentDetails_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedRentDetail = newSelection;
            }
        });

        // Add action event for the receiveBook button with ChoiceDialog
        rentDetails_receiveBook.setOnAction(event -> {
            if (selectedRentDetail != null) {
                // Create a ChoiceDialog
                ChoiceDialog<String> dialog = new ChoiceDialog<>("Not returned", "Not returned", "Returned");
                dialog.setTitle("Receive Book");
                dialog.setHeaderText("Select Rentline Status");
                dialog.setContentText("Choose the status:");

                // Show the dialog and get the result
                Optional<String> result = dialog.showAndWait();

                // Process the result
                result.ifPresent(status -> {
                    int rentlineId = selectedRentDetail.getRentlineId();
                    int rentId1 = rentDetailsService.getRentIdFromRentline(rentlineId); // Get rentId from database
                    rentDetailsService.receiveBook(rentId1, rentlineId, status); // Use the selected status
                });
            } else {
                // Handle case where no row is selected
                System.out.println("No row selected!");
            }
        });
    }

    public Button getRentDetails_checkRent() {
        return rentDetails_checkRent;
    }

    public void setRentDetails_checkRent(Button rentDetails_checkRent) {
        this.rentDetails_checkRent = rentDetails_checkRent;
    }
}