package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class displayBooksService {
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("bookid"),
                        resultSet.getString("title"),
<<<<<<< HEAD
=======
                        resultSet.getInt("publishYear"),
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
                        resultSet.getInt("publisherid"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("worth")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching books: " + e.getMessage());
        }

        return books;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
