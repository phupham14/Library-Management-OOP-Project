package com.example.library.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminPageController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="admin_addBook"
    private Button admin_addBook; // Value injected by FXMLLoader

    @FXML // fx:id="admin_addMember"
    private Button admin_addMember; // Value injected by FXMLLoader

    @FXML // fx:id="admin_bookAuthor"
    private Label admin_bookAuthor; // Value injected by FXMLLoader

    @FXML // fx:id="admin_bookCheck"
    private Button admin_bookCheck; // Value injected by FXMLLoader

    @FXML // fx:id="admin_bookName"
    private Label admin_bookName; // Value injected by FXMLLoader

    @FXML // fx:id="admin_issueBtn"
    private Button admin_issueBtn; // Value injected by FXMLLoader

    @FXML // fx:id="admin_issuedBooks"
    private Button admin_issuedBooks; // Value injected by FXMLLoader

    @FXML // fx:id="admin_memberCheck"
    private Button admin_memberCheck; // Value injected by FXMLLoader

    @FXML // fx:id="admin_memberContact"
    private Label admin_memberContact; // Value injected by FXMLLoader

    @FXML // fx:id="admin_memberNameText"
    private Label admin_memberNameText; // Value injected by FXMLLoader

    @FXML // fx:id="admin_renewBtn"
    private Button admin_renewBtn; // Value injected by FXMLLoader

    @FXML // fx:id="admin_searchBook"
    private Button admin_searchBook; // Value injected by FXMLLoader

    @FXML // fx:id="admin_searchMember"
    private Button admin_searchMember; // Value injected by FXMLLoader

    @FXML // fx:id="admin_submissionBtn"
    private Button admin_submissionBtn; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert admin_addBook != null : "fx:id=\"admin_addBook\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_addMember != null : "fx:id=\"admin_addMember\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_bookAuthor != null : "fx:id=\"admin_bookAuthor\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_bookCheck != null : "fx:id=\"admin_bookCheck\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_bookName != null : "fx:id=\"admin_bookName\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_issueBtn != null : "fx:id=\"admin_issueBtn\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_issuedBooks != null : "fx:id=\"admin_issuedBooks\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_memberCheck != null : "fx:id=\"admin_memberCheck\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_memberContact != null : "fx:id=\"admin_memberContact\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_memberNameText != null : "fx:id=\"admin_memberNameText\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_renewBtn != null : "fx:id=\"admin_renewBtn\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_searchBook != null : "fx:id=\"admin_searchBook\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_searchMember != null : "fx:id=\"admin_searchMember\" was not injected: check your FXML file 'admin-page.fxml'.";
        assert admin_submissionBtn != null : "fx:id=\"admin_submissionBtn\" was not injected: check your FXML file 'admin-page.fxml'.";

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

        // Optionally, you can set the window to be resizable
        addBook.setResizable(true);

        // Set a title for the new window (optional)
        addBook.setTitle("Add Book Page");

        // Show the new window
        addBook.show();
    }

    public void onOpenAddMember() throws IOException {
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/member-add.fxml")));

        // Create a new Stage for the new window
        Stage addMember = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        addMember.setScene(scene);

        // Optionally, you can set the window to be resizable
        addMember.setResizable(true);

        // Set a title for the new window (optional)
        addMember.setTitle("Add Member Page");

        // Show the new window
        addMember.show();
    }

    public void onOpenSearchMember() throws IOException {
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/searchMember.fxml")));

        // Create a new Stage for the new window
        Stage searchMember = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        searchMember.setScene(scene);

        // Optionally, you can set the window to be resizable
        searchMember.setResizable(true);

        // Set a title for the new window (optional)
        searchMember.setTitle("Search Member Page");

        // Show the new window
        searchMember.show();
    }

    public void onOpenSearchBook() throws IOException {
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/searchBook.fxml")));

        // Create a new Stage for the new window
        Stage searchBook = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        searchBook.setScene(scene);

        // Optionally, you can set the window to be resizable
        searchBook.setResizable(true);

        // Set a title for the new window (optional)
        searchBook.setTitle("Search Book Page");

        // Show the new window
        searchBook.show();
    }

    public void onOpenAllBooks() throws IOException {
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/displayBooks.fxml")));

        // Create a new Stage for the new window
        Stage allBooks = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        allBooks.setScene(scene);

        // Optionally, you can set the window to be resizable
        allBooks.setResizable(true);

        // Set a title for the new window (optional)
        allBooks.setTitle("All Books Page");

        // Show the new window
        allBooks.show();
    }

    public void onOpenAllMembers() throws IOException {
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/displayMembers.fxml")));

        // Create a new Stage for the new window
        Stage allMembers = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        allMembers.setScene(scene);

        // Optionally, you can set the window to be resizable
        allMembers.setResizable(true);

        // Set a title for the new window (optional)
        allMembers.setTitle("All Members Page");

        // Show the new window
        allMembers.show();
    }

    public void onOpenIssuedBooks() throws IOException {
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/issued-books.fxml")));

        // Create a new Stage for the new window
        Stage issuedBooks = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        issuedBooks.setScene(scene);

        // Optionally, you can set the window to be resizable
        issuedBooks.setResizable(true);

        // Set a title for the new window (optional)
        issuedBooks.setTitle("Issued Books Page");

        // Show the new window
        issuedBooks.show();
    }

}