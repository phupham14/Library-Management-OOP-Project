package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Customer;
import com.example.library.model.Person;
import com.example.library.service.cartService;
import com.example.library.service.customerService;
import com.example.library.service.searchBookService;
import com.example.library.util.Session;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static com.example.library.model.Person.getPersonId;

public class UserPageController {

    @FXML
    private TextField user_authorFind;

    @FXML
    private TextField user_bookNameFind;

    @FXML
    private Label user_username;

    @FXML
    private TableColumn<Book, String> user_bookTitle;

    @FXML
    private TableColumn<Book, String> user_bookPublisher;

    @FXML
    private TableColumn<Book, Integer> user_bookQuantity;

    @FXML
    private TableColumn<Book, Double> user_bookPrice;

    @FXML
    private TableColumn<Book, String> user_author;

    @FXML
    private TableView<Book> user_tableView;

    @FXML
    private Button user_findBookBtn;

    @FXML
    private Button user_checkCart;

    @FXML
    private Button user_issueBookBtn;

    private final searchBookService bookService = new searchBookService();
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        user_username.setText(Person.getFirstName());

        user_bookTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        user_bookPublisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        user_bookQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        user_author.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        user_bookPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWorth() != null ? cellData.getValue().getWorth().doubleValue() : 0.0).asObject());

        loadAllBooks();

        user_findBookBtn.setOnAction(event -> handleSearch());
        user_issueBookBtn.setOnAction(event -> handleIssueBook());
        user_checkCart.setOnAction(event -> {
            try {
                onOpenCart();
            } catch (IOException e) {
                System.err.println("Error opening cart: " + e.getMessage());
            }
        });
    }

    public void onOpenCart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/library/view/Cart.fxml"));
        Parent root = loader.load();
        Stage openCart = new Stage();
        openCart.setScene(new Scene(root));
        openCart.setResizable(true);
        openCart.setTitle("Cart Page");
        openCart.show();
    }

    private void handleIssueBook() {
        // Lấy cuốn sách được chọn từ giao diện
        Book selectedBook = user_tableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Kiểm tra nếu sách đã hết hàng
            if (selectedBook.getQuantity() <= 0) {
                System.out.println("Book is out of stock.");
                return;
            }

            // Lấy thông tin customer từ session
            Customer currentCustomer = getCurrentCustomer();
            if (currentCustomer == null) {
                System.out.println("Customer not found");
                return;
            }

            // Thực hiện mượn sách và thêm vào giỏ hàng
            try {
                bookService.issueBookById(selectedBook.getBookId());
                cartService.getInstance().addBookToCart(selectedBook, currentCustomer); // Sử dụng đối tượng Customer
                selectedBook.setQuantity(selectedBook.getQuantity() - 1);
                loadAllBooks();

                System.out.println("Book issued and added to the cart successfully.");
            } catch (Exception e) {
                System.err.println("Error issuing book: " + e.getMessage());
            }
        } else {
            System.out.println("No book selected. Please select a book to issue.");
        }
    }

    private Customer getCurrentCustomer() {
        int customerId = getCurrentCustomerId();
        if (customerId == -1) {
            return null;
        }
        return customerService.getCustomerById(customerId);
    }

    private int getCurrentCustomerId() {
        int currentCustomerId = Session.getInstance().getCustomerId();
        System.out.println("Current Customer ID: " + currentCustomerId);
        return currentCustomerId;
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
            loadAllBooks();
        }
    }
}
