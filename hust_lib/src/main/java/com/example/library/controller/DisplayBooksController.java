package com.example.library.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class DisplayBooksController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="displayBooks_bookID"
    private TableColumn<?, ?> displayBooks_bookID; // Value injected by FXMLLoader

    @FXML // fx:id="displayBooks_publishYear"
    private TableColumn<?, ?> displayBooks_publishYear; // Value injected by FXMLLoader

    @FXML // fx:id="displayBooks_publisherID"
    private TableColumn<?, ?> displayBooks_publisherID; // Value injected by FXMLLoader

    @FXML // fx:id="displayBooks_quantity"
    private TableColumn<?, ?> displayBooks_quantity; // Value injected by FXMLLoader

    @FXML // fx:id="displayBooks_title"
    private TableColumn<?, ?> displayBooks_title; // Value injected by FXMLLoader

    @FXML // fx:id="displayBooks_worth"
    private TableColumn<?, ?> displayBooks_worth; // Value injected by FXMLLoader

    @FXML // fx:id="rootPane"
    private AnchorPane rootPane; // Value injected by FXMLLoader

    @FXML // fx:id="tableView"
    private TableView<?> tableView; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert displayBooks_bookID != null : "fx:id=\"displayBooks_bookID\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_publishYear != null : "fx:id=\"displayBooks_publishYear\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_publisherID != null : "fx:id=\"displayBooks_publisherID\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_quantity != null : "fx:id=\"displayBooks_quantity\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_title != null : "fx:id=\"displayBooks_title\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_worth != null : "fx:id=\"displayBooks_worth\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'displayBooks.fxml'.";

    }

}
