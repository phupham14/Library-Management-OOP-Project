package com.example.loginpage.Database;

import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args){
        ConnectionUtil db = new ConnectionUtil();
        db.connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
    }
}
