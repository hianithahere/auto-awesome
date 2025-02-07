package com.example.demo1;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.junit.Assert;

public class AssignmentPage {
    public static void main(String[] args) throws InterruptedException {

        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.plexusworldwide.com");
        Thread.sleep(200);

        WebDriverWait wait = new WebDriverWait(driver,10);

        try {

            WebElement acceptAll =
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Accept All')]")));
            acceptAll.click();
        } catch (Exception e) {
            System.out.println("Exception Message" + e.getMessage());
        }

        //Navigates to Shop Page and looks for Women's Health and validates if it is present

        WebElement shopNow = driver.findElement(By.xpath("//div[@id='Shop']/a/span/div/div/span"));
        shopNow.click();
        System.out.println("Shop Page Complete");
        Thread.sleep(2000);

        WebElement category= driver.findElement(By.xpath("//*[contains(text(),\"Women's Health\")]"));
        Assert.assertTrue("Women's Health",category.isDisplayed());


        //Navigates to Women's Health page and asserts if the checkbox for Women's Health is not checked which lets it show all options.

        WebElement womenHealth = driver.findElement(By.xpath("//div[@id='__next']/div/main/div[4]/div[2]/div/div/section/div/a/div/div/div/div/span/img"));
        womenHealth.click();
        System.out.println("Inside Women Health Page");
        Thread.sleep(2000);
        WebElement womenCheckBox = driver.findElement(By.xpath("//div[@id='productsArea']/div[2]/div[3]/div/div/div/div[2]/div/div/div/div/label/span[2]"));
        try {
           Assert.assertFalse(womenCheckBox.isSelected());
            System.out.println("Checkbox - Default Selection should be  " + womenCheckBox.isSelected());

        } catch (AssertionError e) {
            System.out.println("Category - Women's Health - Not Selected");
        }

        //Validating the current page is in the HealthPage
        String currentURL = driver.getCurrentUrl();
        try{
            Assert.assertTrue(currentURL.contains("womans-health"));
            System.out.println("Correct Page " + currentURL);
        }
        catch (AssertionError e) {
            System.out.println("Not in the Correct Navigation Page");
        }

        System.out.println("End of Code");

        driver.close();

    }
}