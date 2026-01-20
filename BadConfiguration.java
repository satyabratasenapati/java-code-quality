package com.example.badcode;

public class BadConfiguration {

    public void connectToDatabase() {
        // Violation: Hardcoded IP Address
        String dbUrl = "jdbc:mysql://192.168.1.55:3306/db";
        
        // Violation: Hardcoded Password
        String password = "SuperSecretPassword123!";
        
        // Violation: Hardcoded Absolute Path (Windows)
        String logPath = "C:\\Users\\Admin\\logs\\app.log";
        
        // Violation: Insecure HTTP
        String callbackUrl = "http://my-unsafe-site.com/api";
        
        System.out.println("Connecting to " + dbUrl);
    }
}