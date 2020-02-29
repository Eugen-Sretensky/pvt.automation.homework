package junitcucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GeneralPrecondition {
     LoginPage loginPage;
     MailBoxPage mailBoxPage;
     WebDriver webDriver;

    public GeneralPrecondition(){
        System.setProperty("webdriver.chrome.driver", "C:\\Study\\Java\\Java Automation (PVT)\\chromedriver_win32\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().window().maximize();
        loginPage = new LoginPage(webDriver);
        mailBoxPage = new MailBoxPage(webDriver);
    }
}
