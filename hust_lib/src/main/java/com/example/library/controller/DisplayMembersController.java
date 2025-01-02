package com.example.library.controller;

import com.example.library.model.Person;
import com.example.library.service.displayMemberService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class DisplayMembersController {

    @FXML
    private TableColumn<Person, String> displayMembers_firstname;

    @FXML
    private TableColumn<Person, String> displayMembers_lastname;

    @FXML
    private TableColumn<Person, String> displayMembers_address;

    @FXML
    private TableColumn<Person, String> displayMembers_email;

    @FXML
    private TableColumn<Person, String> displayMembers_password;

    @FXML
    private TableColumn<Person, String> displayMembers_phoneNumber;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Person> tableView;

    private final displayMemberService memberService = new displayMemberService();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadMembers();
    }

    private void setupTableColumns() {
        displayMembers_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        displayMembers_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        displayMembers_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        displayMembers_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        displayMembers_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        displayMembers_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    private void loadMembers() {
        ObservableList<Person> membersList = displayMemberService.getAllMembers();
        tableView.setItems(membersList);
    }
}