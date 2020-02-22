package pages;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MailboxPageTest {
    private static LoginPage loginPage;
    private static ChromeDriver driver;
    private static MailboxPage mailboxPage;

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
        loginPage.login("wane-bruce", "pvtautomation1");
        mailboxPage = new MailboxPage(driver);
    }

    @Order(1)@Test
    void firstInboxLetterSendToSpam() {
        Assert.assertEquals("Спам", mailboxPage.firstLetterContextMenu());
        Assert.assertEquals("Перемещено в спам", mailboxPage.firstLetterSendToSpam());
    }

    @Order(2)@Test
    void firstSpamLetterSendToInbox() {
        Assert.assertEquals("Письма, перемещённые в Спам более месяца назад, будут автоматически удаляться. Подробнее", mailboxPage.navigateToSpamTab());
        Assert.assertEquals("Не спам", mailboxPage.spamTabfirstLetterContextMenu());
        Assert.assertEquals("Перемещено в папку «Входящие»",mailboxPage.spamTabSendFirstLetterToInbox());
    }

    @Order(3)@Test
    void sendNewLetter() {
        Assert.assertEquals("отправлено",mailboxPage.sendNewLetter());
    }

    @Order(4)@Test
    void flagThreeLetters() {
        Assert.assertTrue("Письма не были отмечены флажком!",mailboxPage.flagThreeLetters());
    }

    @Order(4)@Test
    void removeAllFlags() {
        Assert.assertFalse("Письма отмечены флажком!",mailboxPage.removeAllFlags());
    }
}