package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.displayBooksService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayBooksController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Book, Integer> displayBooks_bookID;

    @FXML
    private TableColumn<Book, String> displayBooks_title;

    @FXML
    private TableColumn<Book, Integer> displayBooks_publishYear;

    @FXML
    private TableColumn<Book, Integer> displayBooks_publisherID;

    @FXML
    private TableColumn<Book, Integer> displayBooks_quantity;

    @FXML
    private TableColumn<Book, Double> displayBooks_worth;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Book> tableView;

    private final displayBooksService bookService = new displayBooksService();

    @FXML
    void initialize() {
        assert displayBooks_bookID != null : "fx:id=\"displayBooks_bookID\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_title != null : "fx:id=\"displayBooks_title\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_publishYear != null : "fx:id=\"displayBooks_publishYear\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_publisherID != null : "fx:id=\"displayBooks_publisherID\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_quantity != null : "fx:id=\"displayBooks_quantity\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert displayBooks_worth != null : "fx:id=\"displayBooks_worth\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'displayBooks.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'displayBooks.fxml'.";

        setupTable();
        loadBooks();
    }

    private void setupTable() {
        displayBooks_bookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        displayBooks_title.setCellValueFactory(new PropertyValueFactory<>("title"));
<<<<<<< HEAD
=======
        displayBooks_publishYear.setCellValueFactory(new PropertyValueFactory<>("publishYear"));
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
        displayBooks_publisherID.setCellValueFactory(new PropertyValueFactory<>("publisherId"));
        displayBooks_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        displayBooks_worth.setCellValueFactory(new PropertyValueFactory<>("worth"));
    }

    private void loadBooks() {
        ObservableList<Book> bookList = FXCollections.observableArrayList(bookService.getBooks());
        tableView.setItems(bookList);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
