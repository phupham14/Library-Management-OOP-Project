package com.example.library.controller;

<<<<<<< Updated upstream
=======
import com.example.library.model.Person;
import com.example.library.service.searchMemberService;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
public class SearchMemberController {

    @FXML
    private ListView<?> searchMem_listView;
=======
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

    @FXML
    private Button searchMem_searchBtn;

    @FXML
    private TableView<Person> searchMem_tableView;

    @FXML
    private TextField searchMem_textfield;

<<<<<<< Updated upstream
=======
    private final searchMemberService memberService = new searchMemberService();

    @FXML
    private void initialize() {
        // Set up the cell value factories for each column
        searchMem_firstNameTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        searchMem_lastNameTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        searchMem_addressTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        searchMem_phoneNumberTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        searchMem_emailTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        searchMem_roleTable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole()));

        searchMem_searchBtn.setOnAction(event -> handleSearch());

        // Populate all members on initialization
        loadAllMembers();

        // Add mouse click event to the TableView
        searchMem_tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                Person selectedItem = searchMem_tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    populateTextFields(selectedItem);
                }
            }
        });

        // Add click event to the ImageView for uploading an image
        searchMem_image.setOnMouseClicked(event -> uploadImage());

        // Add click event to the Cancel button
        searchMem_cancelBtn.setOnAction(event -> clearFields());

        // Add click event to the Save button
        searchMem_saveBtn.setOnAction(event -> saveMember());

        // Add click event to the Delete button
        searchMem_delBtn.setOnAction(event -> deleteMember());
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

    private void populateTextFields(Person selectedItem) {
        searchMem_firstName.setText(selectedItem.getFirstName());
        searchMem_lastName.setText(selectedItem.getLastName());
        searchMem_address.setText(selectedItem.getAddress());
        searchMem_phoneNumber.setText(selectedItem.getPhoneNumber());
        searchMem_email.setText(selectedItem.getEmail());
        searchMem_password.setText("Password not displayed"); // Placeholder
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
        searchMem_email.clear(); // Clear email field
        searchMem_password.clear();
        searchMem_image.setImage(null); // Clear the image
        searchMem_tableView.getSelectionModel().clearSelection(); // Clear selection
    }

    public void onOpenNewUser() throws IOException {
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/member-add.fxml")));

        // Create a new Stage for the new window
        Stage addMember = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        addMember.setScene(scene);
        addMember.setResizable(true);
        addMember.setTitle("Add Member Page");
        addMember.show();
    }

    private void saveMember() {
        String firstName = searchMem_firstName.getText().trim();
        String lastName = searchMem_lastName.getText().trim();
        String address = searchMem_address.getText().trim();
        String phoneNumber = searchMem_phoneNumber.getText().trim();
        String email = searchMem_email.getText().trim(); // Get email from the new text field
        String password = searchMem_password.getText().trim();

        // Check if phone number or email exists in the database
        Person existingMemberByPhone = memberService.findMemberByPhoneNumber(phoneNumber);
        Person existingMemberByEmail = memberService.findMemberByEmail(email); // New method to find by email

        if (existingMemberByPhone != null || existingMemberByEmail != null) {
            // Update existing member
            Person existingMember = existingMemberByPhone != null ? existingMemberByPhone : existingMemberByEmail;
            existingMember.setFirstName(firstName);
            existingMember.setLastName(lastName);
            existingMember.setAddress(address);
            existingMember.setEmail(email); // Update email
            existingMember.setPassword(password); // Adjust as necessary
            memberService.updateMember(existingMember);
        } else {
            // Create a new member
            Person newMember = new Person(firstName, lastName, address, phoneNumber, email, password);
            memberService.addMember(newMember);
        }

        clearFields(); // Clear fields after saving
    }

    private void deleteMember() {
        Person selectedItem = searchMem_tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String phoneNumber = selectedItem.getPhoneNumber(); // Extract phone number

            // Delete the member
            memberService.deleteMember(phoneNumber);
            clearFields(); // Clear fields after deletion
            loadAllMembers(); // Refresh the table view
        }
    }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}