package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class UserPageController {

    @FXML
    private TextField user_authorFind;

    @FXML
    private TextField user_bookNameFind;

    @FXML
    private TableColumn<?, ?> user_bookPrice;

    @FXML
    private TableColumn<?, ?> user_bookPublisher;

    @FXML
    private TableColumn<?, ?> user_bookQuantity;

    @FXML
    private TableColumn<?, ?> user_bookTitle;

    @FXML
    private Button user_findBookBtn;

    @FXML
    private Button user_issueBookBtn;

    @FXML
    private TableColumn<?, ?> user_publishYear;

}