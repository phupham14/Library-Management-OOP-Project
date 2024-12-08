package com.example.library.util;

public class ValidationUtil {
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
