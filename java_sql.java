package com.java_sql_connection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import static com.java_sql_connection.URL.*;

public class java_sql {
    static LocalDate cdate = LocalDate.now();
    public static void main(String[] args) {
        Start();
    }
    static void Start(){
        Scanner sc = new Scanner(System.in);
        int key;
        while (true) {
            System.out.println("Enter any key");
            System.out.println("1. Issue Book");
            System.out.println("2. Available Books");
            System.out.println("3. Register Lost Book");
            System.out.println("4. Submit Book");
            //scan key
            key = sc.nextInt();
            switch (key) {
                case 1:
                    IssueBook();
                    break;
                case 2:
                    AvailableBooks();
                    break;
                case 3:
                    RegisterLostBook();
                    break;
                case 4:
                    SubmitBook();
                    break;
                default:
                    System.out.println("Try Again!");
            }
        }
    }
    public static void IssueBook(){
        System.out.println("You want to Issue a Book.");
        System.out.println("Please enter Student id");
        Scanner sc = new Scanner(System.in);
        int student_id = sc.nextInt();
        System.out.println("Please enter Book id");
        //scan book id
        int book_id = sc.nextInt();
        try {
            Class.forName(url1);
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Successful to the database" + url);
            String query = String.format("Insert into Checkout(S_id,B_id, checkout_date) values( %d,%d,'%s')", student_id, book_id, cdate);
            String query1 = "Update Books set available = available - 1 where book_id ="+book_id+";";
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.execute(query1);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    static void AvailableBooks(){
        System.out.println("List of Books: ");
        String books;
        try {
            Class.forName(url1);
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Successful to the database" + url);
            String query = "Select name from Books";
            Statement statement = connection.createStatement();
            books = String.valueOf(statement.execute(query));
            System.out.println(books);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    static void RegisterLostBook(){
        System.out.println("You want to register lost book.");
        System.out.println("Enter Student id who lost it.");
        Scanner sc = new Scanner(System.in);
        //scan student id
        int student_id = sc.nextInt();
        System.out.println("Enter Book id");
        //scan book id
        int book_id = sc.nextInt();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Successful to the database" + url);
            String query = String.format("Insert into Book_Lost(b_id,s_id, checkout_date) values( %d,%d,'%s')", student_id, book_id, cdate);
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    static void SubmitBook(){
        System.out.println("You want to Submit Book to Library");
        System.out.println("Enter book id");
        //scan book id
        Scanner sc = new Scanner(System.in);
        int book_id = sc.nextInt();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is Successful to the database" + url);

            String query = "Update Books set available = available +1 where book_id = "+book_id+";";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

