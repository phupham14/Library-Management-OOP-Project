// HistoryController.java
package com.example.library.controller;

import com.example.library.model.Rent;
import com.example.library.service.HistoryService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.example.library.util.Session.getInstance;

public class HistoryController {

    @FXML
    private TableColumn<Rent, LocalDate> history_borrowDate;

    @FXML
    private Button history_cancelRent;

    @FXML
    private TableColumn<Rent, Integer> history_customerId;

    @FXML
    private TableColumn<Rent, LocalDate> history_dueDate;

    @FXML
    private TableColumn<Rent, Boolean> history_isCollected;

    @FXML
    private TableColumn<Rent, Integer> history_rentId;

    @FXML
    private TableView<Rent> history_tableView;

    private final HistoryService historyService = new HistoryService();
    private ObservableList<Rent> rentList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        history_customerId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCustomerId()).asObject());
        history_dueDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDueDate()));
        history_isCollected.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getIsCollected()));
        history_rentId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getRentId()).asObject());
        history_borrowDate.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getBorrowDate()));

        try {
            loadRentData();
        } catch (SQLException e) {
            System.err.println("Error loading rent data: " + e.getMessage());
        }
    }

    private void loadRentData() throws SQLException {
        int customerId = getCurrentCustomerId();
        List<Rent> rents = historyService.getRentHistory(customerId);

        System.out.println("Number of rents fetched: " + rents.size());
        for (Rent rent : rents) {
            System.out.println("Rent ID: " + rent.getRentId());
            System.out.println("Customer ID: " + rent.getCustomerId());
            System.out.println("Borrow Date: " + rent.getBorrowDate());
            System.out.println("Due Date: " + rent.getDueDate());
            System.out.println("Is Collected: " + rent.getIsCollected());
            System.out.println("--------------------");
        }

        rentList.clear();
        rentList.addAll(rents);
        history_tableView.setItems(rentList);
    }

    @FXML
    private void handleRowDoubleClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Rent selectedRent = history_tableView.getSelectionModel().getSelectedItem();
            if (selectedRent != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/library/view/rentDetailsUserSide.fxml"));
                    Parent root = loader.load();

                    RentDetailsUserSideController rentDetailsController = loader.getController();
                    rentDetailsController.setRent(selectedRent);

                    // Debug: Print selected Rent ID
                    System.out.println("Selected Rent ID: " + selectedRent.getRentId());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    System.err.println("Error loading rent details view: " + e.getMessage());
                }
            }
        }
    }

    private int getCurrentCustomerId() {
        int currentCustomerId = getInstance().getCustomerId();
        System.out.println("Current Customer ID: " + currentCustomerId);
        return currentCustomerId;
    }

    @FXML
    private void onHistoryCancelRent() {
        try {
            int customerId = getCurrentCustomerId();
            historyService.cancelRent(customerId);

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rent Canceled");
            alert.setHeaderText(null);
            alert.setContentText("Rent canceled successfully!");
            alert.showAndWait();

            // Refresh the TableView to reflect the changes
            loadRentData();
        } catch (SQLException e) {
            // Check for the specific exception raised by the function
            if (e.getMessage().contains("Không tồn tại giao dịch Rent nào đang chờ")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage()); // Show the specific error message
                alert.showAndWait();
            } else {
                System.err.println("Error canceling rent: " + e.getMessage());
                // Handle other exceptions appropriately
            }
        }
    }
}