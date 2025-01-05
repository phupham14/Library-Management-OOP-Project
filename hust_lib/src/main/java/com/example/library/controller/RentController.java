package com.example.library.controller;

import com.example.library.model.Rent;
import com.example.library.service.RentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RentController {

    @FXML
    private TableView<Rent> rentTableView;

    @FXML
    private Button rent_checkRent;

    @FXML
    private TableColumn<Rent, Integer> rent_rentId;

    @FXML
    private TableColumn<Rent, String> rent_username;

    @FXML
    private TableColumn<Rent, java.time.LocalDate> rent_borrowDate;

    @FXML
    private TableColumn<Rent, java.time.LocalDate> rent_dueDate;

    @FXML
    private TableColumn<Rent, Boolean> rent_returnAll;

    @FXML
    private TableColumn<Rent, Boolean> rent_isCollected;

    @FXML
    private TextField rent_searchBar;

    @FXML
    private Button rent_searchBtn;

    private RentService rentService;
    private ObservableList<Rent> rentObservableList;

    @FXML
    public void initialize() {
        rentService = new RentService(); // Initialize RentService

        // Set up the TableView columns
        rent_rentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        rent_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        rent_borrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        rent_dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        // Set up editable columns for returnAll and isCollected
        setupEditableColumn(rent_returnAll, "returnAll");
        setupEditableColumn(rent_isCollected, "isCollected");

        // Load data into the TableView
        loadRentData();

        // Set action for search button
        rent_searchBtn.setOnAction(event -> searchRents());

        // Add a mouse click listener to open rent details
        rentTableView.setOnMouseClicked(event -> handleRowClick(event));
    }

    private void handleRowClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Check for double-click
            Rent selectedRent = rentTableView.getSelectionModel().getSelectedItem();
            if (selectedRent != null) {
                openRentDetails(selectedRent.getRentId());
            }
        }
    }

    private void openRentDetails(int rentId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/library/view/rentDetails.fxml")); // Update with the correct path
            Parent root = loader.load();
            RentDetailsController rentDetailsController = loader.getController();
            rentDetailsController.setRentId(rentId);

            Stage stage = new Stage();
            stage.setTitle("Rent Details");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCheckRent(javafx.event.ActionEvent actionEvent) {
        RentService.checkRent();
    }

    private void loadRentData() {
        List<Rent> rents = rentService.fetchAllRents(); // Fetch data from RentService
        rentObservableList = FXCollections.observableArrayList(rents);
        rentTableView.setItems(rentObservableList); // Set items in TableView
    }

    private void searchRents() {
        String searchTerm = rent_searchBar.getText().trim();

        if (searchTerm.isEmpty()) {
            // If the search bar is empty, reload all data
            loadRentData();
            return;
        }

        // Filter based on rentId or username
        ObservableList<Rent> filteredRents = FXCollections.observableArrayList(
                rentObservableList.stream()
                        .filter(rent ->
                                String.valueOf(rent.getRentId()).equalsIgnoreCase(searchTerm) ||
                                        rent.getFirstname().equalsIgnoreCase(searchTerm) ||
                                        rent.getLastname().equalsIgnoreCase(searchTerm)
                        )
                        .collect(Collectors.toList())
        );

        rentTableView.setItems(filteredRents);
    }

    private void setupEditableColumn(TableColumn<Rent, Boolean> column, String propertyName) {
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));

        // Set up a ComboBox for editing
        column.setCellFactory(col -> new TableCell<Rent, Boolean>() {
            private final ComboBox<Boolean> comboBox = new ComboBox<>();

            {
                comboBox.getItems().addAll(true, false);
                comboBox.setOnAction(event -> {
                    Boolean newValue = comboBox.getValue();
                    if (newValue != null) {
                        Rent rent = getTableView().getItems().get(getIndex());
                        if (propertyName.equals("returnAll")) {
                            rent.setReturnAll(newValue);
                        } else if (propertyName.equals("isCollected")) {
                            rent.setIsCollected(newValue);
                        }
                        rentService.updateRent(rent); // Save the updated status to the database
                    }
                });
            }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    comboBox.setValue(item);
                    setGraphic(comboBox);
                }
            }

            @Override
            public void startEdit() {
                super.startEdit();
                if (!isEmpty()) {
                    comboBox.setValue(getItem());
                    setGraphic(comboBox);
                    comboBox.show();
                }
            }

            @Override
            public void cancelEdit() {
                super.cancelEdit();
                setGraphic(null);
                // Revert to the original value
                Rent rent = getTableView().getItems().get(getIndex());
                comboBox.setValue(getItem());
            }
        });
    }
}