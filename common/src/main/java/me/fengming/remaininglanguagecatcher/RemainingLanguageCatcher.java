package me.fengming.remaininglanguagecatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemainingLanguageCatcher {
    public static final String MOD_ID = "remaininglanguagecatcher";
    public static final Logger LOGGER = LoggerFactory.getLogger("RemainingLanguageCatcher");
    public static boolean isLoaded = false;
    public static File outputFile;
    public static void init(File gameDir) {
        outputFile = gameDir.toPath().resolve("RemainingLanguageCatcher.txt").toFile();
        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
               throw new IllegalStateException("Failed to create new file: ", e);
            }
        }
        isLoaded = true;
        LOGGER.info("Loaded RemainingLanguageCatcher");
    }

    public static String formattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    public static void foundNullValue(String key) {
        if (!isLoaded) return;
        LOGGER.info("Found a null value associated with a key: {}", key);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
            bw.write(String.format("[%s] Found a null value, \"%s\": null,\n", formattedDate(), key));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write to file: ", e);
        }
    }

    public static void foundDefaultValue(String key, String value) {
        if (!isLoaded || value.isEmpty()) return;
        LOGGER.info("Found a default value associated with a key: {} = {}", key, value);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
            bw.write(String.format("[%s] Found a default value, \"%s\": \"%s\",\n", formattedDate(), key, value));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write to file: ", e);
        }
    }
}