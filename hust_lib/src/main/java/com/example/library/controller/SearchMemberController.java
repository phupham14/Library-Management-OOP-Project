package com.example.library.controller;

import com.example.library.model.Person;
import com.example.library.service.searchMemberService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class SearchMemberController {

    @FXML
    private ListView<String> searchMem_listView; // Use String for displaying details

    @FXML
    private Button searchMem_searchBtn;

    @FXML
    private TextField searchMem_textfield;

    private final searchMemberService memberService = new searchMemberService();

    @FXML
    private void initialize() {
        searchMem_searchBtn.setOnAction(event -> handleSearch());
    }

    private void handleSearch() {
        String keyword = searchMem_textfield.getText().trim();

        if (!keyword.isEmpty()) {
            List<Person> members = memberService.searchMembersByName(keyword);

            ObservableList<String> memberDetails = FXCollections.observableArrayList();
            if (members.isEmpty()) {
                memberDetails.add("No members found!");
            } else {
                for (Person member : members) {
                    String details = String.format(
                            "FirstName: %s, LastName: %s, Address: %s, PhoneNumber: %s, Email: %s, Role: %s",
                            member.getFirstName(),
                            member.getLastName(),
                            member.getAddress(),
                            member.getPhoneNumber(),
                            member.getEmail(),
                            member.getRole()
                    );
                    memberDetails.add(details);
                }
            }

            searchMem_listView.setItems(memberDetails);
        } else {
            searchMem_listView.setItems(FXCollections.observableArrayList("Please enter a keyword to search!"));
        }
    }
}
