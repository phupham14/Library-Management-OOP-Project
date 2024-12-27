package com.example.library.controller;

<<<<<<< Updated upstream
=======
import com.example.library.model.Book;
import com.example.library.service.searchBookService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
public class SearchBookController {

    @FXML
    private ListView<?> searchBook_listView;
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SearchBookController {

    @FXML
    private Button searchBook_cancelBtn;

    @FXML
    private Button searchBook_delBtn;

    @FXML
    private ImageView searchBook_image;

    @FXML
    private TextField searchBook_publisherId;

    @FXML
    private Button searchBook_addBookBtn;

    @FXML
    private TextField searchBook_quantity;

    @FXML
    private Button searchBook_saveAddBtn;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

    @FXML
    private Button searchBook_searchBtn;

    @FXML
    private TableView<Book> searchBook_tableView;

    @FXML
    private TableColumn<Book, String> searchBook_tableViewTitle;

    @FXML
    private TableColumn<Book, Integer> searchBook_tableViewPublisherID;

    @FXML
    private TableColumn<Book, Integer> searchBook_tableViewPublishYear;

    @FXML
    private TableColumn<Book, Integer> searchBook_tableViewQuantity;

    @FXML
    private TableColumn<Book, Double> searchBook_tableViewWorth;

    @FXML
    private TextField searchBook_textfield;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    @FXML
    private TextField searchBook_title;

    @FXML
    private TextField searchBook_worth;

    @FXML
    private TextField searchBook_year;

    private final searchBookService bookService = new searchBookService();
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Load all books on page open
        loadAllBooks();

        // Set up the search button action
        searchBook_searchBtn.setOnAction(event -> handleSearch());

        // Setup table columns to display book details
        searchBook_tableViewTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        searchBook_tableViewPublisherID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPublisherId()).asObject());
        searchBook_tableViewPublishYear.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPublishYear()).asObject());
        searchBook_tableViewQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        searchBook_tableViewWorth.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWorth()).asObject());

        // Add double-click event to the table view
        searchBook_tableView.setOnMouseClicked(this::handleMouseClick);

        // Add click event to the image view for image upload
        searchBook_image.setOnMouseClicked(this::handleImageClick);

        // Add action for cancel button
        searchBook_cancelBtn.setOnAction(event -> clearFields());

        // Add action for delete button
        searchBook_delBtn.setOnAction(event -> handleDelete());

        // Add action for save button
        searchBook_saveAddBtn.setOnAction(event -> handleSaveOrUpdate());
    }

    private void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Book selectedBook = searchBook_tableView.getSelectionModel().getSelectedItem();
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
        searchBook_publisherId.clear();
        searchBook_year.clear();
        searchBook_quantity.clear();
        searchBook_worth.clear();
        searchBook_image.setImage(null); // Optionally clear the image
    }

    private void populateFields(Book book) {
        searchBook_title.setText(book.getTitle());
        searchBook_publisherId.setText(String.valueOf(book.getPublisherId()));
        searchBook_year.setText(String.valueOf(book.getPublishYear()));
        searchBook_quantity.setText(String.valueOf(book.getQuantity()));
        searchBook_worth.setText(String.valueOf(book.getWorth()));
        // Optionally set the image if available in the Book model
        // If your Book model has an image path, load it here
    }

    private void loadAllBooks() {
        List<Book> books = bookService.getAllBooks();
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
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/book-add.fxml")));

        // Create a new Stage for the new window
        Stage addBook = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        addBook.setScene(scene);
        addBook.setResizable(true);
        addBook.setTitle("Add Member Page");
        addBook.show();
    }

    private void handleDelete() {
        Book selectedBook = searchBook_tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            // Confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText("Are you sure you want to delete this book?");
            alert.setContentText(selectedBook.getTitle());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                bookService.deleteBook(selectedBook); // Assuming this method exists in your service
                bookList.remove(selectedBook); // Remove from ObservableList
            }
        } else {
            // Optionally show a warning if no book is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book to delete.");
            alert.showAndWait();
        }
    }

    private void handleSaveOrUpdate() {
        String title = searchBook_title.getText().trim();
        String publisherInput = searchBook_publisherId.getText().trim();
        String yearInput = searchBook_year.getText().trim();
        String quantityInput = searchBook_quantity.getText().trim();
        String worthInput = searchBook_worth.getText().trim();

        // Check if any fields are empty
        if (title.isEmpty() || publisherInput.isEmpty() || yearInput.isEmpty() || quantityInput.isEmpty() || worthInput.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        int publisherId;
        try {
            publisherId = Integer.parseInt(publisherInput);
        } catch (NumberFormatException e) {
            System.err.println("Publisher ID must be a valid integer.");
            return;
        }

        int publishYear;
        try {
            publishYear = Integer.parseInt(yearInput);
        } catch (NumberFormatException e) {
            System.err.println("Publish Year must be a valid integer.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityInput);
        } catch (NumberFormatException e) {
            System.err.println("Quantity must be a valid integer.");
            return;
        }

        double worth;
        try {
            worth = Double.parseDouble(worthInput);
        } catch (NumberFormatException e) {
            System.err.println("Worth must be a valid number.");
            return;
        }

        Book existingBook = bookService.findBookByTitle(title);
        if (existingBook != null) {
            // Update existing book
            existingBook.setPublisherId(publisherId);
            existingBook.setPublishYear(publishYear);
            existingBook.setQuantity(quantity);
            existingBook.setWorth(worth);
            bookService.updateBook(existingBook);
            System.out.println("Book updated successfully!");
        } else {
            System.err.println("No book found with the title: " + title);
            return;
        }

        loadAllBooks(); // Refresh the table
        clearFields(); // Clear fields after update
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}