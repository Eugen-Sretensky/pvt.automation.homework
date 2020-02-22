import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.LoginPage;
import pages.MailboxPage;

public class LoginPageTest {
    private static LoginPage loginPage;
    private static ChromeDriver driver;
    private static MailboxPage mailboxHomePage;

    @BeforeAll
    public static void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\Study\\Java\\Java Automation (PVT)\\chromedriver_win32\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.get("http://mail.ru");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        loginPage.login("wane-bruce", "pvtautomation");
        mailboxHomePage = new MailboxPage(driver);
        Assert.assertEquals("wane-bruce@mail.ru",mailboxHomePage.receiveAccountName());
    }



    @AfterAll
    public static void after() {
        driver.close();
    }
}
