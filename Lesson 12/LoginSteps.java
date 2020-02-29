package junitcucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class LoginSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "wane-bruce";
    private static final String PASSWORD = "pvtautomation1";
    private LoginPage loginPage;
    private WebDriver webDriver;
    private GeneralPrecondition generalPrecondition;

    public LoginSteps(GeneralPrecondition generalPrecondition) {
        webDriver = generalPrecondition.webDriver;
        loginPage = generalPrecondition.loginPage;
    }

    @Given("^I am on main application page$")
    public void loadMainPage() {
        webDriver.get(MAIN_URL);
    }

    @When("^I login as correct user$")
    public void loginAsCorrectUser() {
        loginPage.enterLogin(LOGIN);
        loginPage.enterPassword(PASSWORD);
        //loginPage.clickEnterButton();
    }

    @When("^I login as user with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginAsUserWithCredentials(String name, String password) {
        loginPage.enterLogin(name);
        loginPage.enterPassword(password);
        //loginPage.enterLoginAndPass(name, password);
        //loginPage.clickEnterButton();
    }

    @Then("I see logout link")
    public void seeLogoutLink() {
        Assert.assertTrue(loginPage.logoutLinkPresents());
    }

    @Then("I see error message")
    public void seeErrorMessage() {
        Assert.assertTrue(loginPage.logoutLinkErrorPresents());
    }

    @After
    public void afterClass() {
        webDriver.quit();
    }
}
