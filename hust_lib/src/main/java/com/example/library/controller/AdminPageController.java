package com.example.library.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button admin_addBook;

    @FXML
    private Button admin_addMember;

    @FXML
    private Label admin_bookAuthor;

    @FXML
    private Button admin_bookCheck;

    @FXML
    private Label admin_bookName;

    @FXML
    private Button admin_issueBtn;

    @FXML
    private Button admin_issuedBooks;

    @FXML
    private Button admin_memberCheck;

    @FXML
    private Label admin_memberContact;

    @FXML
    private Label admin_memberNameText;

    @FXML
    private Button admin_renewBtn;

    @FXML
    private Button admin_searchBook;

    @FXML
    private Button admin_searchMember;

    @FXML
    private Button admin_submissionBtn;

    @FXML
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
        openNewWindow("/com/example/library/view/book-add.fxml", "Add Book Page");
    }

    public void onOpenAddMember() throws IOException {
        openNewWindow("/com/example/library/view/member-add.fxml", "Add Member Page");
    }

    public void onOpenSearchMember() throws IOException {
        openNewWindow("/com/example/library/view/searchMember.fxml", "Search Member Page");
    }

    public void onOpenSearchBook() throws IOException {
        openNewWindow("/com/example/library/view/searchBook.fxml", "Search Book Page");
    }

    public void onOpenAllBooks() throws IOException {
        openNewWindow("/com/example/library/view/displayBooks.fxml", "All Books Page");
    }

    public void onOpenAllMembers() throws IOException {
        openNewWindow("/com/example/library/view/displayMembers.fxml", "All Members Page");
    }

    public void onOpenIssuedBooks() throws IOException {
        openNewWindow("/com/example/library/view/issued-books.fxml", "Issued Books Page");
    }

    private void openNewWindow(String fxmlPath, String title) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle(title);
        stage.show();
    }
}
