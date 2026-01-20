package com.example.badcode;

public class BadQuality {

    public void processData() {
        try {
            // Violation: Thread.sleep is bad practice
            Thread.sleep(5000);
            
            // Violation: Redundant String constructor
            String name = new String("User"); 
            
            if (name == null) {
                // Violation: System.err usage
                System.err.println("Name is empty");
            }

        } catch (InterruptedException e) {
            // Violation: printStackTrace swallows logs
            e.printStackTrace();
        } catch (Exception e) {
            // Violation: Empty catch block (swallowing exceptions)
        }
        
        // Violation: System.out usage
        System.out.println("Processing complete");
    }
}