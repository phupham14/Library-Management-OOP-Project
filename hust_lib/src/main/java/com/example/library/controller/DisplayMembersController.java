package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DisplayMembersController {

    @FXML
    private TableColumn<?, ?> displayMembers_DOB;

    @FXML
    private TableColumn<?, ?> displayMembers_address;

    @FXML
    private TableColumn<?, ?> displayMembers_email;

    @FXML
    private TableColumn<?, ?> displayMembers_name;

    @FXML
    private TableColumn<?, ?> displayMembers_password;

    @FXML
    private TableColumn<?, ?> displayMembers_phoneNumber;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<?> tableView;

}