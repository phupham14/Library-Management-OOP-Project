package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML // fx:id="addBook_author"
    private TextField addBook_author; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_bookID"
    private TextField addBook_bookID; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_bookTitle"
    private TextField addBook_bookTitle; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_cancelBtn"
    private Button addBook_cancelBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_publisher"
    private TextField addBook_publisher; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_saveBtn"
    private Button addBook_saveBtn; // Value injected by FXMLLoader

}