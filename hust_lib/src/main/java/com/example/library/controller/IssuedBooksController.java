package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class IssuedBooksController {

    @FXML
    private TableColumn<?, ?> issuedBooks_bookID;

    @FXML
    private TableColumn<?, ?> issuedBooks_publishYear;

    @FXML
    private TableColumn<?, ?> issuedBooks_publisherID;

    @FXML
    private TableColumn<?, ?> issuedBooks_quantity;

    @FXML
    private TableColumn<?, ?> issuedBooks_title;

    @FXML
    private TableColumn<?, ?> issuedBooks_worth;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<?> tableView;

}