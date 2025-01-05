package com.example.library.controller;

import com.example.library.model.Rent;
import com.example.library.model.RentDetail;
import com.example.library.service.RentDetailsUserSideService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class RentDetailsUserSideController {

    @FXML
    private TableColumn<RentDetail, String> rentDetailsUser_bookTitle;

    @FXML
    private TableColumn<RentDetail, Integer> rentDetailsUser_rentlineId;

    @FXML
    private TableView<RentDetail> rentDetailsUser_tableView;

    private RentDetailsUserSideService rentDetailsUserSideService = new RentDetailsUserSideService();

    private Rent rent; // To store the Rent object passed from HistoryController

    @FXML
    private void initialize() {
        // Set the cell value factories for the TableColumns
        rentDetailsUser_rentlineId.setCellValueFactory(new PropertyValueFactory<>("rentlineId"));
        rentDetailsUser_bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
    }

    public void setRent(Rent rent) {
        this.rent = rent;

        // Debug: Print the received Rent object
        System.out.println("Received Rent in RentDetailsUserSideController: " + rent);

        loadData();
    }

    private void loadData() {
        try {
            List<RentDetail> rentDetails = rentDetailsUserSideService.getRentDetails(rent.getRentId());

            // Debug: Print fetched RentDetail data
            System.out.println("Fetched RentDetails:");
            for (RentDetail detail : rentDetails) {
                System.out.println("Rent Line ID: " + detail.getRentlineId() + ", Book Title: " + detail.getBookTitle());
            }

            ObservableList<RentDetail> rentDetailList = FXCollections.observableArrayList(rentDetails);

            // Debug: Print the size of the ObservableList
            System.out.println("ObservableList size: " + rentDetailList.size());

            rentDetailsUser_tableView.setItems(rentDetailList);
        } catch (SQLException e) {
            System.err.println("Error loading rent details: " + e.getMessage());
        }
    }
}