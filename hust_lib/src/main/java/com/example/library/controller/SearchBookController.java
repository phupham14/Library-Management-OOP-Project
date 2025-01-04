package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.searchBookService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SearchBookController {

    @FXML
    private Button searchBook_addBookBtn;
    @FXML
    private Button searchBook_cancelBtn;
    @FXML
    private Button searchBook_delBtn;
    @FXML
    private Button searchBook_saveAddBtn; // Button to save changes
    @FXML
    private ImageView searchBook_image;
    @FXML
    private TextField searchBook_publisher;
    @FXML
    private TextField searchBook_quantity;
    @FXML
    private Button searchBook_searchBtn;
    @FXML
    private TableView<Book> searchBook_tableView;
    @FXML
    private TableColumn<Book, String> searchBook_tableViewTitle;
    @FXML
    private TableColumn<Book, String> searchBook_tableViewPublisher;
    @FXML
    private TableColumn<Book, String> searchBook_tableViewAuthor;
    @FXML
    private TableColumn<Book, Integer> searchBook_tableViewQuantity;
    @FXML
    private TableColumn<Book, Double> searchBook_tableViewWorth;
    @FXML
    private TextField searchBook_textfield;
    @FXML
    private TextField searchBook_title;
    @FXML
    private TextField searchBook_worth;
    @FXML
    private TextField searchBook_author;

    private final searchBookService bookService = new searchBookService();
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    private Book selectedBook; // Store the currently selected book

    @FXML
    private void initialize() {
        loadAllBooks();
        searchBook_searchBtn.setOnAction(event -> handleSearch());

        // Bind TableView columns
        searchBook_tableViewTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        searchBook_tableViewPublisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        searchBook_tableViewAuthor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        searchBook_tableViewQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        searchBook_tableViewWorth.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWorth() != null ? cellData.getValue().getWorth().doubleValue() : 0.0).asObject());

        searchBook_tableView.setOnMouseClicked(this::handleMouseClick);
        searchBook_image.setOnMouseClicked(this::handleImageClick);
        searchBook_cancelBtn.setOnAction(event -> clearFields());
        searchBook_delBtn.setOnAction(event -> handleDelete());
        searchBook_saveAddBtn.setOnAction(event -> handleSaveUpdate());
    }

    private void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            selectedBook = searchBook_tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                populateFields(selectedBook);
            }
        }
    }

    private void handleImageClick(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(searchBook_image.getScene().getWindow());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            searchBook_image.setImage(image);
        }
    }

    private void clearFields() {
        searchBook_title.clear();
        searchBook_publisher.clear();
        searchBook_author.clear();
        searchBook_quantity.clear();
        searchBook_worth.clear();
        searchBook_image.setImage(null);
        selectedBook = null; // Reset selected book
    }

    private void populateFields(Book book) {
        searchBook_title.setText(book.getTitle());
        searchBook_publisher.setText(book.getPublisher());
        searchBook_author.setText(book.getAuthor());
        searchBook_quantity.setText(String.valueOf(book.getQuantity()));
        searchBook_worth.setText(String.valueOf(book.getWorth()));
    }

    private void loadAllBooks() {
        List<Book> books = bookService.getAllBooks();
        System.out.println("Loaded books: " + books.size()); // Debug line
        bookList.clear();
        bookList.addAll(books);
        searchBook_tableView.setItems(bookList);
    }

    private void handleSearch() {
        String keyword = searchBook_textfield.getText().trim();

        if (!keyword.isEmpty()) {
            List<Book> books = bookService.searchBooksByTitle(keyword);
            bookList.clear();
            bookList.addAll(books);
            searchBook_tableView.setItems(bookList);
        } else {
            loadAllBooks();
        }
    }

    public void onOpenAddBook() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/book-add.fxml")));
        Stage addBook = new Stage();
        Scene scene = new Scene(root);
        addBook.setScene(scene);
        addBook.setResizable(true);
        addBook.setTitle("Add Book Page");
        addBook.show();
    }

    private void handleDelete() {
        Book selectedBook = (Book) searchBook_tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete this book?");
            alert.setContentText(selectedBook.getTitle());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                bookService.deleteBook(selectedBook);
                bookList.remove(selectedBook);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book to delete.");
            alert.showAndWait();
        }
    }

    private void handleSaveUpdate() {
        if (selectedBook != null) {
            selectedBook.setTitle(searchBook_title.getText());
            selectedBook.setPublisher(searchBook_publisher.getText());
            selectedBook.setAuthor(searchBook_author.getText());
            selectedBook.setQuantity(Integer.parseInt(searchBook_quantity.getText()));
            selectedBook.setWorth(BigDecimal.valueOf(Double.parseDouble(searchBook_worth.getText())));

            bookService.updateBook(selectedBook);
            loadAllBooks();
            clearFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book to update.");
            alert.showAndWait();
        }
    }
}