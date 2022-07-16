package com.F6.step_definitions;


import com.F6.utils.DBUtils;
import com.F6.utils.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Hooks class will be used to execute cucumber annotations like @Before, @After, @AfterStep, @BeforeStep etc.
 * @Before("db") and @After("db") will be used to create and close the database connection.
 * @BeforeStep and @AfterStep will be used to take screenshot of the test.
 * @After will be used to close the driver.
 */


public class Hooks {

    @Before("@db")
    public void dbHook() {
        System.out.println("creating database connection");
        DBUtils.createConnection();
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("closing database connection");
        DBUtils.destroyConnection();

    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        // we put a logic that should apply to every scenario
        byte [] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        // we put a logic that should apply to every scenario
        byte [] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

    }

    @After
    public void teardownScenario(Scenario scenario){


        if (scenario.isFailed()){

            byte [] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

        }


        Driver.closeDriver();


    }
}
