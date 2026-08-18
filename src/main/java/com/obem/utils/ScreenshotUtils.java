package com.obem.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static String capture(WebDriver driver, String testName) {
        try {
            Path directory = Path.of("test-output", "screenshots");
            Files.createDirectories(directory);

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path destination = directory.resolve(
                    testName + "_" + System.currentTimeMillis() + ".png");

            Files.copy(source.toPath(), destination,
                    StandardCopyOption.REPLACE_EXISTING);

            return destination.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
