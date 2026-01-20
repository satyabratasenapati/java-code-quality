package com.example.badcode;

import org.junit.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Data;
import javax.persistence.Entity;

@Entity
@Data // Violation: @Data on Entity causes performance issues
public class BadAnnotations {

    // Violation: Field injection is discouraged
    @Autowired
    private UserService userService;

    // Violation: Suppressing ALL warnings is dangerous
    @SuppressWarnings("all")
    public void sloppyMethod() {
        int x = 5; 
    }

    // Violation: Old JUnit 4 Annotation
    @Before
    public void setup() {
        // setup logic
    }

    // Violation: Old JUnit 4 Annotation
    @Ignore("Not working yet") 
    @Test
    public void testSomething() {
        // test logic
    }
}