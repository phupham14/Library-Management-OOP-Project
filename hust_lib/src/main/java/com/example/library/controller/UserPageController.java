package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.searchBookService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class UserPageController {

    @FXML
    private TextField user_authorFind;

    @FXML
    private TextField user_bookNameFind;

    @FXML
    private TableColumn<Book, String> user_bookTitle;

    @FXML
    private TableColumn<Book, String> user_bookPublisher;

    @FXML
    private TableColumn<Book, Integer> user_bookQuantity;

    @FXML
    private TableColumn<Book, Integer> user_publishYear;

    @FXML
    private TableColumn<Book, Double> user_bookPrice;

    @FXML
    private TableView<Book> user_tableView;

    @FXML
    private Button user_findBookBtn;

    private final searchBookService bookService = new searchBookService();
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Set up table columns to display book details
        user_bookTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        user_bookPublisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        user_bookQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        user_publishYear.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPublishYear()).asObject());
        user_bookPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWorth()).asObject());

        // Load all books on page open
        loadAllBooks();

        // Set up search button action
        user_findBookBtn.setOnAction(event -> handleSearch());
    }

    private void loadAllBooks() {
        List<Book> books = bookService.getAllBooks();
        bookList.clear();
        bookList.addAll(books);
        user_tableView.setItems(bookList);
    }

    private void handleSearch() {
        String bookName = user_bookNameFind.getText().trim();

        if (!bookName.isEmpty()) {
            List<Book> books = bookService.searchBooksByTitle(bookName);
            bookList.clear();
            bookList.addAll(books);
            user_tableView.setItems(bookList);
        } else {
            // If the text field is empty, reload all books
            loadAllBooks();
        }
    }
}