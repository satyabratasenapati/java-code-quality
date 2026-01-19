package com.example.goodcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Service
public class GoodCode {

    // 1. Use Logger instead of System.out.println
    private static final Logger logger = LoggerFactory.getLogger(GoodCode.class);

    private final UserService userService;
    private final String dbUrl;

    // 2. Use Constructor Injection instead of @Autowired on fields
    public GoodCode(UserService userService, 
                    @Value("${database.url}") String dbUrl) { // 3. Inject properties, don't hardcode
        this.userService = userService;
        this.dbUrl = dbUrl;
    }

    public void processData(String input) {
        // 4. Use Objects.isNull or Optional instead of '== null' checks
        if (Objects.isNull(input)) {
            logger.warn("Input is null, skipping processing.");
            return;
        }

        try {
            logger.info("Connecting to DB at: {}", dbUrl); // Secure logging
            
            // 5. Use Path/Paths instead of hardcoded strings like "C:\\Users"
            Path configPath = Paths.get("config", "app.properties");
            logger.debug("Reading config from: {}", configPath.toAbsolutePath());

            performTask();

        } catch (RuntimeException e) {
            // 6. Log the exception properly instead of printStackTrace()
            logger.error("An error occurred during processing", e);
        }
    }

    private void performTask() {
        // Task logic here...
    }

    // --- Tests Section ---

    // 7. Use JUnit 5 @BeforeEach instead of @Before
    @BeforeEach
    void setup() {
        // Setup logic
    }

    // 8. Use @Disabled instead of @Ignore
    @Disabled("Feature pending implementation") 
    @Test
    void testFutureFeature() {
        // Test logic
    }
}