package com.example.library.service;

<<<<<<< HEAD
import com.example.library.model.Book;
=======
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< HEAD
import java.sql.ResultSet;

public class addBookService {

    // Method to get the next book ID
    public int getNextBookId() {
        String query = "SELECT MAX(bookid) AS maxId FROM book";
        int maxId = 0;

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                maxId = resultSet.getInt("maxId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving next book ID: " + e.getMessage());
        }

        return maxId + 1; // Return the next ID
    }

    // Method to save a book
    public void handleSaveAction(String bookTitle, String publisher, String author, int quantity, double price) {
        String query = "INSERT INTO book (title, publisher, author, quantity, worth) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Gán giá trị từ tham số vào câu lệnh SQL
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setString(2, publisher);
            preparedStatement.setString(3, author);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setDouble(5, price);

            // Thực thi câu lệnh SQL
            preparedStatement.executeUpdate();
            System.out.println("Sách đã được lưu thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu sách: " + e.getMessage());
        }
    }

    // Method to fetch a book by its ID
    public Book fetchBookById(int bookId) {
        String query = "SELECT * FROM book WHERE bookid = ?";
        Book book = null;
=======

public class addBookService {

    public void handleSaveAction(Integer bookId, String title, String author, String publisher) {
        String query = "INSERT INTO book (bookid, title, author, publisher) VALUES (?, ?, ?, ?)";
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

<<<<<<< HEAD
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create a Book object from the result set
                book = new Book(
                        resultSet.getInt("bookid"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("worth"),
                        null, // Assuming image is not needed here
                        resultSet.getString("author")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching book by ID: " + e.getMessage());
        }

        return book; // Return the fetched book or null if not found
    }
}
=======
            // Set the values from the method parameters
            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, publisher);

            // Execute the insert query
            preparedStatement.executeUpdate();
            System.out.println("Book saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving book: " + e.getMessage());
        }
    }
}
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
