package com.example.library.controller;

import com.example.library.model.Blacklist;
import com.example.library.service.BlacklistService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BlacklistController implements Initializable {

    @FXML
    private TableColumn<Blacklist, String> blacklist_fineAmount;
    @FXML
    private TableColumn<Blacklist, String> blacklist_reason;
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

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}