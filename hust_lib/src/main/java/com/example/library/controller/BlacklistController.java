package com.example.library.controller;

import com.example.library.model.Blacklist;
import com.example.library.service.BlacklistService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class BlacklistController implements Initializable {

    @FXML
    private TableColumn<Blacklist, String> blacklist_fineAmount;
    @FXML
    private TableColumn<Blacklist, String> blacklist_reason;

    @FXML
    private Button blacklist_payForFine; // This should be javafx.scene.control.Button

    @FXML
    private TableColumn<Blacklist, String> blacklist_rentId;
    @FXML
    private TableView<Blacklist> blacklist_tableView;

    private final BlacklistService service = new BlacklistService();
    private ObservableList<Blacklist> blacklistItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (blacklistItems == null) {
            blacklistItems = FXCollections.observableArrayList();
        }

        try {
            blacklistItems.addAll(service.fetchAllBlacklists());
        } catch (SQLException e) {
            System.err.println("Error fetching blacklist data: " + e.getMessage());
        }

        blacklist_rentId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getRentId())));
        blacklist_reason.setCellValueFactory(cellData -> {
            String reason = cellData.getValue().getReason();
            return new SimpleStringProperty(reason != null ? reason : "null"); // Display "null" if reason is null
        });
        blacklist_fineAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFineAmount())));

        blacklist_tableView.setItems(blacklistItems);

        // Make the table and the reason column editable
        blacklist_tableView.setEditable(true);
        blacklist_reason.setEditable(true);

        // Set the cell factory for editing
        blacklist_reason.setCellFactory(TextFieldTableCell.forTableColumn());

        // Handle the edit commit event
        blacklist_reason.setOnEditCommit(event -> {
            try {
                Blacklist blacklist = event.getRowValue();
                String newReason = event.getNewValue();
                blacklist.setReason(newReason);
                service.updateReason(blacklist.getBlackListId(), newReason); // Update in the database
            } catch (SQLException e) {
                System.err.println("Error updating reason: " + e.getMessage());
                showErrorAlert("Error", "Failed to update reason.");
            }
        });
    }

    @FXML
    private void payForFine() {
        Blacklist selectedBlacklist = blacklist_tableView.getSelectionModel().getSelectedItem();

        if (selectedBlacklist != null) {
            // Create a dialog to get the paid amount
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Pay Fine");
            dialog.setHeaderText("Enter Paid Amount");
            dialog.setContentText("Amount:");

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                try {
                    double paidAmount = Double.parseDouble(result.get());

                    // Get the rentId from the selectedBlacklist
                    int rentId = selectedBlacklist.getRentId();

                    // Get the customerId using the service
                    int customerId = service.getCustomerIdFromRentId(rentId);

                    // Call the PayForFine function
                    service.payForFine(rentId, paidAmount, customerId);

                    // Refresh the TableView
                    refreshTableView();

                } catch (NumberFormatException e) {
                    showErrorAlert("Error", "Invalid amount entered.");
                } catch (SQLException e) {
                    System.err.println("Error paying for fine: " + e.getMessage());
                    showErrorAlert("Error", "Failed to pay for fine.");
                }
            }
        } else {
            // Handle case where no row is selected
            System.out.println("No row selected!");
        }
    }

    // Method to refresh the TableView
    private void refreshTableView() {
        try {
            // Clear existing items
            blacklistItems.clear();

            // Fetch updated data from the database
            blacklistItems.addAll(service.fetchAllBlacklists());

        } catch (SQLException e) {
            System.err.println("Error refreshing blacklist data: " + e.getMessage());
            showErrorAlert("Error", "Failed to refresh data.");
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}