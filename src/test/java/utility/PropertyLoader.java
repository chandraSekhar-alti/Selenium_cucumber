package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyLoader {
    private static final Logger logger = LogManager.getLogger(PropertyLoader.class);

    /**
     * Loads properties from a file located in the classpath.
     *
     * @param fileName the name of the properties file
     * @return a Properties object containing the loaded properties
     * @throws RuntimeException if the properties file cannot be loaded
     */
    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                logger.error("Unable to find properties file: {}", fileName);
                throw new RuntimeException("Unable to find properties file: " + fileName);
            }
            properties.load(input);
            logger.info("Properties file {} loaded successfully.", fileName);
        } catch (IOException e) {
            logger.error("Failed to load properties from file: {}", fileName, e);
            throw new RuntimeException("Failed to load properties from file: " + fileName, e);
        }
        return properties;
    }
}
