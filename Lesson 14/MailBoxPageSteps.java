package junitcucumber;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Eyes;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;


public class MailBoxPageSteps {
    private MailBoxPage mailBoxPage;
    private WebDriver webDriver;
    private EyesRunner runner;
    private Eyes eyes;


    public MailBoxPageSteps(GeneralPrecondition generalPrecondition) {
        webDriver = generalPrecondition.webDriver;
        mailBoxPage = generalPrecondition.mailBoxPage;
        runner = generalPrecondition.runner;
        eyes = generalPrecondition.eyes;
        eyes.setForceFullPageScreenshot(true);
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

    @When("^I press Create New Message button$")
    public void sendNewLetterOpenForm() {
        mailBoxPage.sendNewLetterOpenForm();
    }

    @Then("^I see New Message creation form$")
    public void sendNewLetterFormEmptyUI() {
        eyes.open(webDriver, "Mail.ru", "Scenario: Create New message and send it to 3 receivers");
        eyes.checkWindow("\"Send New Letter\" form: Empty");
    }

    @When("^I click \"To:\" link$")
    public void sendNewLetterOpenContacts() {
        mailBoxPage.sendNewLetterOpenContacts();
    }

    @Then("^I see Contacts window$")
    public void contactsFormEmptyUI() {
        mailBoxPage.sleepAlittle();
        eyes.checkWindow("\"Contacts\" form: Empty");
    }

    @When("^I select 3 contacts$")
    public void sendNewLetterSelectReceivers() {
        mailBoxPage.sendNewLetterSelectReceivers();
    }

    @Then("^I see the selected contacts are highligted by checkbox icons$")
    public void contactsFormFilledUI() {
        mailBoxPage.sleepAlittle();
        eyes.checkWindow("\"Contacts\" form: 3 receivers selected");
    }

    @When("^I press Add button$")
    public void sendNewLetterAddContacts() {
        mailBoxPage.sendNewLetterAddContacts();
    }

    @Then("^I see New Message creation form with added receivers$")
    public void sendNewLetterFormWithReceiversUI() {
        mailBoxPage.sleepAlittle();
        eyes.checkWindow("\"Send New Letter\" form: Receivers are specified");
    }

    @When("^I fill Subject field$")
    public void sendNewLetterAddSubject() {
        mailBoxPage.sendNewLetterAddSubject();
    }

    @And("^I fill Message body$")
    public void sendNewLetterAddBody() {
        mailBoxPage.sendNewLetterAddBody();
    }

    @Then("^I see added info in the form$")
    public void sendNewLetterFormWithReceiversSubjectBodyUI() {
        mailBoxPage.sleepAlittle();
        eyes.checkWindow("\"Send New Letter\" form: Receivers, Subject and Body are specified");
    }

    @When("^I press send button$")
    public void sendNewLetterPressSend() {
        mailBoxPage.sendNewLetterPressSend();
    }

    @Then("^I see popup with success message$")
    public void SendMessageSuccessPopup() {
        mailBoxPage.sleepAlittle();
        eyes.checkWindow("\"Mail sent successfully\" popup");
    }

    @And("^I check all screenshots$")
    public  void checkAllScreenshots(){
        Assert.assertTrue(screenshotCheckResults());
    }

    @After
    public void afterClass() {
        webDriver.quit();
        eyes.abortIfNotClosed();
    }

    public boolean screenshotCheckResults() {
        eyes.closeAsync();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults.getAllResults()[0].getTestResults().isPassed();
    }
}
