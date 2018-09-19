package windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static driver.DriverManager.getDriver;

public class Calculator {
    private WebDriver driver = getDriver();
    public static final String EQUALS = "Equals";
    public static final String MULTIPLY = "Multiply";

    public Calculator(){
    }

    private By result = By.id("150");

    public void clickOn(final String name){
        driver.findElement(By.name(name)).click();
    }

    public String getResult(){
        return driver.findElement(result).getAttribute("Name");
    }
}
