package com.F6.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacsUtils {

    public static void login(String portal, String email, String password) {
        Driver.getDriver().get(Environment.URL);
        Driver.getDriver().findElement(By.xpath("//input[@id='portal']")).sendKeys(portal);
        Driver.getDriver().findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        Driver.getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        Driver.getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    public static void selectProject(String projectName) {
        WebElement project = Driver.getDriver().findElement(By.xpath("//div[@class='custom-select-panel']/div[. ='" + projectName + "']"));

        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(project).perform();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        wait.until(ExpectedConditions.visibilityOf(project));

        project.click();
    }

    public static void selectService(String service) {
        WebElement serviceElement = Driver.getDriver().findElement(By.xpath("//a[.='" + service + "']"));
        serviceElement.click();
    }

    public static void clickButton(String button) {
        WebElement buttonElement = Driver.getDriver().findElement(By.xpath("//span[.='" + button + "']"));
        buttonElement.click();
    }

}
