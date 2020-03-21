package junitcucumber.steps;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.selenium.Eyes;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junitcucumber.environmentsetup.DBconnection;
import junitcucumber.environmentsetup.GeneralPrecondition;
import junitcucumber.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.SQLException;

public class MainPageSteps {
    //private static final String MAIN_URL = "http://mail.ru";
    private MainPage mainPage;
    private WebDriver webDriver;
    private EyesRunner runner;
    private Eyes eyes;
    private DBconnection dBconnection;

    public MainPageSteps(GeneralPrecondition generalPrecondition) {
        webDriver = generalPrecondition.webDriver;
        mainPage = generalPrecondition.mainPage;
        dBconnection = generalPrecondition.dBconnection;
        eyes = generalPrecondition.eyes;
        eyes.setForceFullPageScreenshot(true);
    }

    @Given("^I am on main application page$")
    public void loadMainPage() throws SQLException {
        //webDriver.get(MAIN_URL)
        webDriver.get(dBconnection.getMainPageUrl());

    }

    @Then("^I see Login form$")
    public void loginFormUI() {
        mainPage.sleepAlittle();
        eyes.open(webDriver, "Mail.ru", "Scenario: Open the Registration form (Create New Mailbox)");
        eyes.checkElement(By.xpath(".//div[@id=\"mailbox-container\"]"), "Login Form");
    }

    @When("^I click \"Создать почту\" in Login form$")
    public void navigateToRegistration() {
        mainPage.openRegistrationForm();
    }

    @Then("^I see Registration form$")
    public void registrationFormUIempty() {
        mainPage.sleepAlittle();
        eyes.checkWindow("Login Form");
    }

    @After
    public void afterClass() {
        webDriver.quit();
    }
}
