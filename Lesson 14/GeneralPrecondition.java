package junitcucumber;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GeneralPrecondition {
    LoginPage loginPage;
    MailBoxPage mailBoxPage;
    WebDriver webDriver;
    EyesRunner runner;
    Eyes eyes;
    final String APIKEY = "h9qjswuvIndgIMQK3mC3JsHfsKovwoQfCk3YxVr65no110";

    public GeneralPrecondition() {
        System.setProperty("webdriver.chrome.driver", "C:\\Study\\Java\\Java Automation (PVT)\\chromedriver_win32\\chromedriver.exe");
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey(APIKEY);
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
