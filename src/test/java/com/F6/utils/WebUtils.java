package com.F6.utils;


import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WebUtils {

    /*
     * This method is used to wait for a given time in seconds before proceeding to the next step
     */

    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }

    /*
     * This method is used to switch to a new window and verify the URL and title of the new window
     */

    public static void switchWindowAndVerify(String expectedInUrl, String expectedInTitle) {

        Set<String> allWindowsHandles = Driver.getDriver().getWindowHandles();

        for (String each : allWindowsHandles) {

            Driver.getDriver().switchTo().window(each);

            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)) {
                break;
            }
        }


        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    /*
     * This method is used to verify the title of the current page
     */

    public static void verifyTitle(String expectedTitle) {

        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);

    }

    /*
     * This method is used to verify the URL of the current page
     */


    public static void verifyURLContains(String expectedInURL) {

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInURL));

    }

    /*
     * This method is used to get the options of a dropdown as a list of strings
     */

    public static List<String> dropdownOptionsAsString(WebElement dropdownElements) {

        Select select = new Select(dropdownElements);

        List<WebElement> actualOptionsAsWebElement = select.getOptions();

        List<String> actualOptionsAsString = new ArrayList<>();

        for (WebElement each : actualOptionsAsWebElement) {

            actualOptionsAsString.add(each.getText());

        }

        return actualOptionsAsString;
    }

    /*
     * This method is used to click on a radio button based on the value of the attribute
     */
    public static void clickRadioButton(List<WebElement> radioButtons, String attributeValue) {

        for (WebElement radioButton : radioButtons) {

            if (radioButton.getAttribute("value").equals(attributeValue)) {
                radioButton.click();
            }

        }

    }

    /*
     * This method is used to get the current date in the format of dd/MM/yyyy
     */

    public static String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dtf.format(java.time.LocalDate.now());
    }

}