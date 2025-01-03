package com.example.library.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DashboardController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="analytics_chart"
    private LineChart<String, Number> analytics_chart; // Value injected by FXMLLoader

    @FXML // fx:id="analytics_comBox"
    private ComboBox<String> analytics_comBox; // Value injected by FXMLLoader

    @FXML // fx:id="book_btn"
    private HBox book_btn; // Value injected by FXMLLoader

    @FXML // fx:id="books_btn"
    private Button books_btn; // Value injected by FXMLLoader

    @FXML // fx:id="books_chart"
    private PieChart books_chart; // Value injected by FXMLLoader

    @FXML // fx:id="books_dataName"
    private Label books_dataName; // Value injected by FXMLLoader

    @FXML // fx:id="books_date"
    private Label books_date; // Value injected by FXMLLoader

    @FXML // fx:id="books_sortDescBtn"
    private ImageView books_sortDescBtn; // Value injected by FXMLLoader

    @FXML // fx:id="homepage_btn"
    private HBox homepage_btn; // Value injected by FXMLLoader

    @FXML // fx:id="issue_btn"
    private Button issue_btn; // Value injected by FXMLLoader

    @FXML // fx:id="issue_chart"
    private BarChart<String, Number> issue_chart; // Value injected by FXMLLoader

    @FXML // fx:id="issue_dataName"
    private Label issue_dataName; // Value injected by FXMLLoader

    @FXML // fx:id="issue_date"
    private Label issue_date; // Value injected by FXMLLoader

    @FXML // fx:id="issue_sortDescBtn"
    private ImageView issue_sortDescBtn; // Value injected by FXMLLoader

    @FXML // fx:id="newBooks_btn"
    private Pane newBooks_btn; // Value injected by FXMLLoader

    @FXML // fx:id="newBooks_data"
    private Label newBooks_data; // Value injected by FXMLLoader

    @FXML // fx:id="newMembers_btn"
    private Pane newMembers_btn; // Value injected by FXMLLoader

    @FXML // fx:id="newMembers_data"
    private Label newMembers_data; // Value injected by FXMLLoader

    @FXML // fx:id="notif_btn"
    private VBox notif_btn; // Value injected by FXMLLoader

    @FXML // fx:id="reports_btn"
    private Pane reports_btn; // Value injected by FXMLLoader

    @FXML // fx:id="reports_data"
    private Label reports_data; // Value injected by FXMLLoader

    @FXML // fx:id="settings_btn"
    private HBox settings_btn; // Value injected by FXMLLoader

    @FXML // fx:id="user_id"
    private Label user_id; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert analytics_chart != null : "fx:id=\"analytics_chart\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert analytics_comBox != null : "fx:id=\"analytics_comBox\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert book_btn != null : "fx:id=\"book_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert books_chart != null : "fx:id=\"books_chart\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert books_dataName != null : "fx:id=\"books_dataName\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert books_date != null : "fx:id=\"books_date\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert books_sortDescBtn != null : "fx:id=\"books_sortDescBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert homepage_btn != null : "fx:id=\"homepage_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert issue_chart != null : "fx:id=\"issue_chart\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert issue_dataName != null : "fx:id=\"issue_dataName\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert issue_date != null : "fx:id=\"issue_date\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert issue_sortDescBtn != null : "fx:id=\"issue_sortDescBtn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert newBooks_btn != null : "fx:id=\"newBooks_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert newBooks_data != null : "fx:id=\"newBooks_data\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert newMembers_btn != null : "fx:id=\"newMembers_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert newMembers_data != null : "fx:id=\"newMembers_data\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert notif_btn != null : "fx:id=\"notif_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert reports_btn != null : "fx:id=\"reports_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert reports_data != null : "fx:id=\"reports_data\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert settings_btn != null : "fx:id=\"settings_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert user_id != null : "fx:id=\"user_id\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert books_btn != null : "fx:id=\"books_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert issue_btn != null : "fx:id=\"issue_btn\" was not injected: check your FXML file 'Dashboard.fxml'.";

        initializeComboBox(); // Initialize combo box
        initializePieChart(); // Initialize pie chart
        initializeDateLabels(); // Initialize date labels
        initializeLineChart();
        initializeBarChart();

        // Set button actions for books_btn and issue_btn
        books_btn.setOnAction(event -> showDatePicker("Select Book Date", books_date));
        issue_btn.setOnAction(event -> showDatePicker("Select Issue Date", issue_date));
    }

    private String[] questionList = {
            "Week",
            "Month",
            "Year"
    };

    public void initializeComboBox() {
        List<String> listQ = new ArrayList<>(Arrays.asList(questionList)); // Create a list from the array

        ObservableList<String> listData = FXCollections.observableArrayList(listQ); // Create an ObservableList
        analytics_comBox.setItems(listData); // Set items in the ComboBox
    }

    public void onSwitchToAdmin() throws IOException {
        // Load the FXML file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/admin-page.fxml")));

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Get the current window
        Stage window = (Stage) homepage_btn.getScene().getWindow();

        // Set the scene to the window
        window.setScene(scene);

        // Optionally, you can set the window to be resizable
        window.setResizable(true);

        // Show the window with preferred size
        window.sizeToScene();
    }

    private void initializePieChart() {
        // Sample data for the pie chart
        PieChart.Data data1 = new PieChart.Data("Category 1", 30);
        PieChart.Data data2 = new PieChart.Data("Category 2", 45);
        PieChart.Data data3 = new PieChart.Data("Category 3", 25);

        // Adding data to the pie chart
        books_chart.getData().addAll(data1, data2, data3);

        // Set colors for each section
        data1.getNode().setStyle("-fx-pie-color: blue;");
        data2.getNode().setStyle("-fx-pie-color: #f4b457;");
        data3.getNode().setStyle("-fx-pie-color: #41c395;");
    }

    private void initializeDateLabels() {
        // Create a Timeline to update the date every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateDateLabels()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateDateLabels() {
        // Get the current date and format it to a shorter version
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // Change to the desired format
        String currentDate = dateFormat.format(new Date());

        // Update the date labels and align them to the right
        books_date.setText(currentDate);
        issue_date.setText(currentDate);

        // Align text to the right
        books_date.setStyle("-fx-alignment: CENTER_RIGHT; -fx-text-alignment: RIGHT;");
        issue_date.setStyle("-fx-alignment: CENTER_RIGHT; -fx-text-alignment: RIGHT;");
    }

    private void showDatePicker(String title, Label dateLabel) {
        // Create a DatePicker
        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(e -> {
            // Use DateTimeFormatter to format the selected date
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            dateLabel.setText(datePicker.getValue().format(dateFormatter));
        });

        // Create a simple dialog to contain the DatePicker
        Stage dialog = new Stage();
        dialog.setTitle(title);
        VBox dialogPane = new VBox(10, datePicker);
        dialogPane.setPadding(new Insets(20)); // Don't forget to import javafx.geometry.Insets
        Scene dialogScene = new Scene(dialogPane);
        dialog.setScene(dialogScene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait(); // Show the dialog and wait until it's closed
    }

    private void initializeBarChart() {
        // Create a new series for the bar chart data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Most Issued Books");

        // Sample data: Add books and their issue count
        series.getData().add(new XYChart.Data<>("Book A", 45));  // Book A issued 45 times
        series.getData().add(new XYChart.Data<>("Book B", 32));  // Book B issued 32 times
        series.getData().add(new XYChart.Data<>("Book C", 60));  // Book C issued 60 times
        series.getData().add(new XYChart.Data<>("Book D", 25));  // Book D issued 25 times

        // Add the series to the bar chart
        issue_chart.getData().add(series);

        // Style the bars
        for (XYChart.Series<?, ?> s : issue_chart.getData()) {
            for (XYChart.Data<?, ?> data : s.getData()) {
                Node barNode = data.getNode(); // Get the bar node
                if (barNode != null) {
                    barNode.setStyle("-fx-bar-fill: #1f77fa;"); // Set bar color (blue in this case)
                }
            }
        }

        // Optional: Set axis labels (you can adjust these as needed)
        issue_chart.getXAxis().setLabel("Books");
        issue_chart.getYAxis().setLabel("Times Issued");
    }


    private void initializeLineChart() {
        // Create sample data including endpoints
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Issues");

        // Add data points, including endpoints
        series.getData().add(new XYChart.Data<>("17th", 0)); // Start point
        series.getData().add(new XYChart.Data<>("18th", 230));
        series.getData().add(new XYChart.Data<>("19th", 400));
        series.getData().add(new XYChart.Data<>("20th", 350));
        series.getData().add(new XYChart.Data<>("21st", 300));
        series.getData().add(new XYChart.Data<>("22nd", 200));
        series.getData().add(new XYChart.Data<>("23rd", 0)); // End point

        // Add data to the chart
        analytics_chart.getData().add(series);

        // Style the line
        for (XYChart.Series<?, ?> s : analytics_chart.getData()) {
            for (XYChart.Data<?, ?> data : s.getData()) {
                Node lineNode = data.getNode().getParent(); // Get the line node
                if (lineNode != null) {
                    lineNode.setStyle("-fx-stroke: #1f77fa; -fx-stroke-width: 2px;"); // Darker line color
                }
            }
        }

        // Fill the area below the line with a lighter color
        analytics_chart.lookup(".chart-series-line").setStyle("-fx-fill: rgba(31, 119, 250, 0.3); -fx-stroke: transparent;");

        // Set x-axis and y-axis labels
        analytics_chart.getXAxis().setLabel("Week");
        analytics_chart.getYAxis().setLabel("Value");
    }

}