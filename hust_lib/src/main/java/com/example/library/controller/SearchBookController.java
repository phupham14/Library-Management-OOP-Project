package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.searchBookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class SearchBookController {

    @FXML
    private ListView<String> searchBook_listView; // Hiển thị danh sách kết quả (String cho thông tin sách)

    @FXML
    private Button searchBook_searchBtn;

    @FXML
    private TextField searchBook_textfield;

    private final searchBookService bookService = new searchBookService();

    @FXML
    private void initialize() {
        searchBook_searchBtn.setOnAction(event -> handleSearch());
    }

    private void handleSearch() {
        String keyword = searchBook_textfield.getText().trim();

        if (!keyword.isEmpty()) {
            List<Book> books = bookService.searchBooksByTitle(keyword); // Search by title

            ObservableList<String> bookDetails = FXCollections.observableArrayList();
            if (books.isEmpty()) {
                bookDetails.add("No books found!");
            } else {
                for (Book book : books) {
                    String details = String.format(
                            "Title: %s, Publisher: %d, Year: %d, Quantity: %d, Worth: %s",
                            book.getTitle(),
                            book.getPublisherId(),
                            book.getPublishYear(),
                            book.getQuantity(),
                            book.getWorth()
                    );
                    bookDetails.add(details);
                }
            }

            searchBook_listView.setItems(bookDetails);
        } else {
            searchBook_listView.setItems(FXCollections.observableArrayList("Please enter a keyword to search!"));
        }
    }
}
