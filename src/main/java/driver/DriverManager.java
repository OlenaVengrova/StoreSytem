package driver;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static WiniumDriver driver;

    public static WiniumDriver getDriver() {
        if(driver == null){
            driver = createWiniumDriver();
        }

        return driver;
    }

    public static WiniumDriver createWiniumDriver() {
        DesktopOptions options = new DesktopOptions();
        options.setApplicationPath("C:\\Windows\\System32\\calc.exe");

        try {
            return new WiniumDriver(new URL("http://localhost:9999"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
