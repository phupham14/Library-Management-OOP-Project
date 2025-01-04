package com.example.library.controller;

import com.example.library.model.RentDetail;
import com.example.library.service.RentDetailsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RentDetailsController {

    @FXML
    private TableColumn<RentDetail, String> rentDetails_bookTitle;

    @FXML
    private TableColumn<RentDetail, String> rentDetails_rentStatus;

    @FXML
    private TableColumn<RentDetail, Integer> rentDetails_rentlineId;

    @FXML
    private TableColumn<RentDetail, java.time.LocalDate> rentDetails_returnDate;

    @FXML
    private TableView<RentDetail> rentDetails_tableView;

    private RentDetailsService rentDetailsService;

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
        rentDetails_rentStatus.setCellValueFactory(new PropertyValueFactory<>("rentStatus"));
        rentDetails_rentlineId.setCellValueFactory(new PropertyValueFactory<>("rentlineId"));
        rentDetails_returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }
}