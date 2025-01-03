package com.example.library.controller;

import com.example.library.model.Person;
import com.example.library.service.searchMemberService;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SearchMemberController {

    @FXML
    private TextField searchMem_address;

    @FXML
    private TableColumn<Person, String> searchMem_addressTable;

    @FXML
    private Button searchMem_cancelBtn;

    @FXML
    private Button searchMem_delBtn;

    @FXML
    private TextField searchMem_email;

    @FXML
    private TableColumn<Person, String> searchMem_emailTable;

    @FXML
    private TextField searchMem_firstName;

    @FXML
    private TableColumn<Person, String> searchMem_firstNameTable;

    @FXML
    private ImageView searchMem_image;

    @FXML
    private TextField searchMem_lastName;

    @FXML
    private TableColumn<Person, String> searchMem_lastNameTable;

    @FXML
    private Button searchMem_newUserBtn;

    @FXML
    private TextField searchMem_password;

    @FXML
    private TableColumn<Person, String> searchMem_passwordTable;

    @FXML
    private TextField searchMem_phoneNumber;

    @FXML
    private TableColumn<Person, String> searchMem_phoneNumberTable;

    @FXML
    private TableColumn<Person, String> searchMem_roleTable;

    @FXML
    private Button searchMem_saveBtn;

    @FXML
    private ListView<String> searchMem_listView;

    @FXML
    private Button searchMem_searchBtn;

    @FXML
    private TableView<Person> searchMem_tableView;

    @FXML
    private TextField searchMem_textfield;

    private final searchMemberService memberService = new searchMemberService();
    private Person selectedMember;

    @FXML
    private void initialize() {
        // Set up the cell value factories for each column
        searchMem_firstNameTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        searchMem_lastNameTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        searchMem_addressTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        searchMem_phoneNumberTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        searchMem_emailTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        searchMem_roleTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));
        searchMem_passwordTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword())); // New line for password

        searchMem_searchBtn.setOnAction(event -> handleSearch());
        loadAllMembers();

        searchMem_tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                selectedMember = searchMem_tableView.getSelectionModel().getSelectedItem();
                if (selectedMember != null) {
                    populateTextFields(selectedMember);
                }
            }
        });

        searchMem_image.setOnMouseClicked(event -> uploadImage());
        searchMem_cancelBtn.setOnAction(event -> clearFields());
        searchMem_saveBtn.setOnAction(event -> saveMember());
        searchMem_delBtn.setOnAction(event -> deleteMember());
        searchMem_newUserBtn.setOnAction(event -> {
            try {
                onOpenNewUser();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadAllMembers() {
        List<Person> members = memberService.getAllMembers();
        ObservableList<Person> memberDetails = FXCollections.observableArrayList(members);
        searchMem_tableView.setItems(memberDetails);
    }

    private void handleSearch() {
        String keyword = searchMem_textfield.getText().trim();
        if (!keyword.isEmpty()) {
            List<Person> members = memberService.searchMembersByName(keyword);
            System.out.println("Search keyword: " + keyword + ", Members found: " + members.size());
            ObservableList<Person> memberDetails = FXCollections.observableArrayList(members);
            searchMem_tableView.setItems(memberDetails);
        } else {
            searchMem_tableView.setItems(FXCollections.observableArrayList());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter a keyword to search!");
            alert.showAndWait();
        }
    }

    private void populateTextFields(Person selectedItem) {
        searchMem_firstName.setText(selectedItem.getFirstName());
        searchMem_lastName.setText(selectedItem.getLastName());
        searchMem_address.setText(selectedItem.getAddress());
        searchMem_phoneNumber.setText(selectedItem.getPhoneNumber());
        searchMem_email.setText(selectedItem.getEmail());
        searchMem_password.setText(selectedItem.getPassword()); // Populate password field
    }

    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(searchMem_image.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            searchMem_image.setImage(image);
        }
    }

    private void clearFields() {
        searchMem_firstName.clear();
        searchMem_lastName.clear();
        searchMem_address.clear();
        searchMem_phoneNumber.clear();
        searchMem_email.clear();
        searchMem_password.clear(); // Clear password
        searchMem_image.setImage(null);
        searchMem_tableView.getSelectionModel().clearSelection();
        selectedMember = null;
    }

    @FXML
    private void saveMember() {
        if (selectedMember == null) {
            System.out.println("No member selected for update. Please select a member.");
            return;
        }

        String firstName = searchMem_firstName.getText().trim();
        String lastName = searchMem_lastName.getText().trim();
        String address = searchMem_address.getText().trim();
        String phoneNumber = searchMem_phoneNumber.getText().trim();
        String email = searchMem_email.getText().trim();
        String password = searchMem_password.getText().trim();

        selectedMember.setFirstName(firstName);
        selectedMember.setLastName(lastName);
        selectedMember.setAddress(address);
        selectedMember.setPhoneNumber(phoneNumber);
        selectedMember.setEmail(email);
        selectedMember.setPassword(password);

        memberService.updateMember(selectedMember);
        System.out.println("Updated existing member: " + selectedMember.getFirstName() + " " + selectedMember.getLastName());

        clearFields();
        loadAllMembers();
    }

    private void deleteMember() {
        Person selectedItem = searchMem_tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String phoneNumber = selectedItem.getPhoneNumber();
            memberService.deleteMember(phoneNumber);
            System.out.println("Deleted member with phone number: " + phoneNumber);
            clearFields();
            loadAllMembers();
        } else {
            System.out.println("No member selected for deletion.");
        }
    }

    public void onOpenNewUser() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/member-add.fxml")));

        Stage addMember = new Stage();
        Scene scene = new Scene(root);
        addMember.setScene(scene);
        addMember.setResizable(true);
        addMember.setTitle("Add Member Page");
        addMember.show();
    }
}