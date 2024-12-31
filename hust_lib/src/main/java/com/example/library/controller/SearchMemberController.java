package com.example.library.controller;

import com.example.library.model.Person;
import com.example.library.service.SearchMemberService;
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

/**
 * Controller for managing member search functionality in the library system.
 */
public class SearchMemberController {

    @FXML
    private TextField searchMem_textfield;
    @FXML
    private TableView<Person> searchMem_tableView;
    @FXML
    private TableColumn<Person, String> searchMem_firstNameTable;
    @FXML
    private TableColumn<Person, String> searchMem_lastNameTable;
    @FXML
    private TableColumn<Person, String> searchMem_addressTable;
    @FXML
    private TableColumn<Person, String> searchMem_phoneNumberTable;
    @FXML
    private TableColumn<Person, String> searchMem_emailTable;
    @FXML
    private TableColumn<Person, String> searchMem_roleTable;
    @FXML
    private TextField searchMem_firstName;
    @FXML
    private TextField searchMem_lastName;
    @FXML
    private TextField searchMem_address;
    @FXML
    private TextField searchMem_phoneNumber;
    @FXML
    private TextField searchMem_email;
    @FXML
    private TextField searchMem_password;
    @FXML
    private ImageView searchMem_image;
    @FXML
    private Button searchMem_searchBtn;
    @FXML
    private Button searchMem_cancelBtn;
    @FXML
    private Button searchMem_saveBtn;
    @FXML
    private Button searchMem_delBtn;
    @FXML
    private Button searchMem_newUserBtn;

    private final SearchMemberService memberService = new SearchMemberService();

    @FXML
    private void initialize() {
        // Configure table columns
        searchMem_firstNameTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        searchMem_lastNameTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        searchMem_addressTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        searchMem_phoneNumberTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        searchMem_emailTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        searchMem_roleTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));

        // Initialize buttons and actions
        searchMem_searchBtn.setOnAction(event -> handleSearch());
        searchMem_cancelBtn.setOnAction(event -> clearFields());
        searchMem_saveBtn.setOnAction(event -> saveMember());
        searchMem_delBtn.setOnAction(event -> deleteMember());
        searchMem_image.setOnMouseClicked(event -> uploadImage());

        // Load all members at startup
        loadAllMembers();

        // Add TableView row selection event
        searchMem_tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Person selectedPerson = searchMem_tableView.getSelectionModel().getSelectedItem();
                if (selectedPerson != null) {
                    populateTextFields(selectedPerson);
                }
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
            ObservableList<Person> memberDetails = FXCollections.observableArrayList(members);
            searchMem_tableView.setItems(memberDetails);
        } else {
            searchMem_tableView.setItems(FXCollections.observableArrayList());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Please enter a keyword to search!");
            alert.showAndWait();
        }
    }

    private void populateTextFields(Person selectedPerson) {
        searchMem_firstName.setText(selectedPerson.getFirstName());
        searchMem_lastName.setText(selectedPerson.getLastName());
        searchMem_address.setText(selectedPerson.getAddress());
        searchMem_phoneNumber.setText(selectedPerson.getPhoneNumber());
        searchMem_email.setText(selectedPerson.getEmail());
        searchMem_password.setText("Password not displayed");
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
        searchMem_password.clear();
        searchMem_image.setImage(null);
        searchMem_tableView.getSelectionModel().clearSelection();
    }

    public void onOpenNewUser() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/member-add.fxml")));
        Stage addMemberStage = new Stage();
        addMemberStage.setScene(new Scene(root));
        addMemberStage.setTitle("Add Member Page");
        addMemberStage.setResizable(true);
        addMemberStage.show();
    }

    private void saveMember() {
        String firstName = searchMem_firstName.getText().trim();
        String lastName = searchMem_lastName.getText().trim();
        String address = searchMem_address.getText().trim();
        String phoneNumber = searchMem_phoneNumber.getText().trim();
        String email = searchMem_email.getText().trim();
        String password = searchMem_password.getText().trim();

        Person existingMember = memberService.findMemberByPhoneNumberOrEmail(phoneNumber, email);
        if (existingMember != null) {
            existingMember.setFirstName(firstName);
            existingMember.setLastName(lastName);
            existingMember.setAddress(address);
            existingMember.setEmail(email);
            existingMember.setPassword(password);
            memberService.updateMember(existingMember);
        } else {
            Person newMember = new Person(firstName, lastName, address, phoneNumber, email, password);
            memberService.addMember(newMember);
        }

        clearFields();
        loadAllMembers();
    }

    private void deleteMember() {
        Person selectedPerson = searchMem_tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            memberService.deleteMember(selectedPerson.getPhoneNumber());
            clearFields();
            loadAllMembers();
        }
    }
}
