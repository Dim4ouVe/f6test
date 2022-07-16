package com.F6.utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    /*
    Creating a private constructor, so we are closing
    access to the object of this class from outside the class
     */

    private Driver() {
    }


    // driver class will provide separate WebDriver objects per thread
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();


    public static WebDriver getDriver() {

        //if this thread doesn't have driver - create it and add to pool
        if (driverPool.get() == null) {

            //if we pass the driver from terminal then use that one
            //if we do not pass the driver from terminal then use the one property file
            String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") : ConfigurationReader.get("browser");


            switch (browser) {
                //local browser options
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "headless-chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
                            "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox",
                            "--disable-dev-shm-usage");
                    driverPool.set(new ChromeDriver(options));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;


                //remote server browser options
                case "remote-chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("start-maximized");
                    try {
                        URL url = new URL(ConfigurationReader.get("remote_server"));
                        driverPool.set(new RemoteWebDriver(url, chromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote-firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("start-maximized");
                    try {
                        URL url = new URL(ConfigurationReader.get("remote_server"));
                        driverPool.set(new RemoteWebDriver(url, firefoxOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote-edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    try {
                        URL url = new URL(ConfigurationReader.get("remote_server"));
                        driverPool.set(new RemoteWebDriver(url, edgeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "remote-safari":
                    SafariOptions safariOptions = new SafariOptions();
                    try {
                        URL url = new URL(ConfigurationReader.get("remote_server"));
                        driverPool.set(new RemoteWebDriver(url, safariOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }

        return driverPool.get();

    }


    /*
    This method will make sure our driver value is always null after using quit() method
     */
    public static void closeDriver() {

        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
