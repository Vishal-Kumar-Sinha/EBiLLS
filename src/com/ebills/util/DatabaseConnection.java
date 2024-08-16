package com.ebills.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private final String URL="jdbc:mysql://localhost:3306/ebills";
    private final String USER="root";
    private final String PASSWORD="";

    private Connection connection;
    
    public Connection getConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully");
        }
        catch(SQLException sqe) {
            System.out.println("Error in database connection :: "+sqe.getMessage());
        }
        catch(ClassNotFoundException cnfe) {
            System.out.println("Error in loading driver :: "+cnfe.getMessage());
        }
        return connection;
    }

    public boolean closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed successfully");
            return true;
        }
        catch(SQLException sqe) {
            System.out.println("Error in closing connection :: "+sqe.getMessage());
            return false;
        }
    }
}
