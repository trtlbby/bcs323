package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to load environment variables from .env file.
 * Loads .env file from the working directory or parent directories.
 */
public final class EnvLoader {
    private static boolean loaded = false;

    private EnvLoader() {}

    /**
     * Load environment variables from .env file.
     * First checks working directory, then parent directories up to 3 levels.
     */
    public static void load() {
        if (loaded) return;
        
        File[] searchPaths = {
            new File(".env"),
            new File("../.env"),
            new File("../../.env"),
            new File("../../../.env")
        };

        for (File envFile : searchPaths) {
            if (envFile.exists()) {
                loadFromFile(envFile);
                loaded = true;
                return;
            }
        }
        
        // If no .env file found, use system environment variables
        loaded = true;
    }

    /**
     * Load environment variables from a specific .env file.
     *
     * @param envFile the .env file to load
     */
    public static void loadFromFile(File envFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(envFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                // Skip comments and empty lines
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                // Parse KEY=VALUE
                int eqIndex = line.indexOf('=');
                if (eqIndex > 0) {
                    String key = line.substring(0, eqIndex).trim();
                    String value = line.substring(eqIndex + 1).trim();
                    
                    // Remove quotes if present
                    if ((value.startsWith("\"") && value.endsWith("\"")) ||
                        (value.startsWith("'") && value.endsWith("'"))) {
                        value = value.substring(1, value.length() - 1);
                    }
                    
                    // Set as system property for access via System.getenv()
                    System.setProperty(key, value);
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not read .env file: " + e.getMessage());
        }
    }

    /**
     * Get an environment variable, preferring .env file values.
     *
     * @param key the variable name
     * @return the value, or null if not found
     */
    public static String get(String key) {
        load();
        
        // First check system properties (which includes .env values if loaded)
        String value = System.getProperty(key);
        if (value != null) {
            return value;
        }
        
        // Fall back to system environment variables
        return System.getenv(key);
    }
}
