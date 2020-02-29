package junitcucumber;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;


public class MailBoxPageSteps {
    private MailBoxPage mailBoxPage;
    private WebDriver webDriver;

    public MailBoxPageSteps(GeneralPrecondition generalPrecondition) {
        webDriver = generalPrecondition.webDriver;
        mailBoxPage = generalPrecondition.mailBoxPage;
    }

    @And("^I open the first inbox letter context menu$")
    public void openFirstInboxLetterContextMenu() {
        mailBoxPage.firstLetterContextMenu();
    }

    @And("^I open the first spam letter context menu$")
    public void openFirstSpamLetterContextMenu() {
        mailBoxPage.spamTabFirstLetterContextMenu();
    }

    @When("^I send it to spam$")
    public void sendToSpam() {
        mailBoxPage.firstLetterSendToSpam();
    }

    @Then("^I see notification message that it was sent to spam$")
    public void sendToSpamNotification() {
        Assert.assertEquals("Перемещено в спам", mailBoxPage.firstLetterSendToSpamNotification());
    }

    @And("^I am in Spam folder$")
    public void navigateToSpamFolder() {
        Assert.assertEquals("Письма, перемещённые в Спам более месяца назад, будут автоматически удаляться. Подробнее", mailBoxPage.navigateToSpamTab());
    }

    @When("^I send it to inbox$")
    public void sendToInbox() {
        mailBoxPage.spamTabSendFirstLetterToInbox();
    }

    @Then("^I see notification message that it was sent to inbox$")
    public void sendToInboxNotification() {
        Assert.assertEquals("Перемещено в папку «Входящие»", mailBoxPage.spamTabSendFirstLetterToInboxNotification());
    }

//    Scenario: Send spam message to Inbox
//    Given I am on main application page
//    And   I login as correct user
//    And   I am in Spam folder
//    And   I open the first letter context menu
//    When  I send it to inbox
//    Then  I see notification message that it was sent to inbox

    @After
    public void afterClass() {
        webDriver.quit();
    }
}
