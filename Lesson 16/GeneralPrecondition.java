package junitcucumber.environmentsetup;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import junitcucumber.pages.MainPage;
import junitcucumber.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.sql.SQLException;

public class GeneralPrecondition {
    public MainPage mainPage;
    public RegistrationPage registrationPage;
    public WebDriver webDriver;
    public EyesRunner runner;
    public Eyes eyes;
    public DBconnection dBconnection;
    final String APIKEY = "h9qjswuvIndgIMQK3mC3JsHfsKovwoQfCk3YxVr65no110";

    public GeneralPrecondition() throws SQLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Study\\Java\\Java Automation (PVT)\\chromedriver_win32\\chromedriver.exe");
        runner = new ClassicRunner();
        dBconnection = new DBconnection();
        eyes = new Eyes(runner);
        eyes.setApiKey(APIKEY);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().window().maximize();
        mainPage = new MainPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
    }
}
