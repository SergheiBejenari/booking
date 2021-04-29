package com.restfullBooker.driver;

import com.restfullBooker.api.BookingApi;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    WebDriver driver;

    public WebDriver getDriver() {
        BookingApi bookingApi = new BookingApi();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://restful-booker.herokuapp.com/apidoc/index.html");
        return driver;
    }

    public void quitDriver() {
        driver.close();
        driver.quit();
    }
}
