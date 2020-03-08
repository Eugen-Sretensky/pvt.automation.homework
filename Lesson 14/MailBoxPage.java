package junitcucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MailBoxPage {
    @FindBy(xpath = ".//i[@class=\"x-ph__menu__button__text x-ph__menu__button__text_auth\" and @id=\"PH_user-email\"]")
    private WebElement accountName;

    @FindBy(xpath = "(.//a[contains(@class,\"js-letter-list-item\")])[1]")
    private WebElement firstLetter;

    @FindBy(xpath = "(.//a[contains(@class,\"js-letter-list-item\")])[2]")
    private WebElement secondLetter;

    @FindBy(xpath = "(.//a[contains(@class,\"js-letter-list-item\")])[3]")
    private WebElement thirdLetter;

    @FindBy(xpath = "(.//button[@title=\"Снять флажок\"])[3]")
    private WebElement removeFlagButton;

    @FindBy(xpath = ".//span[@class=\"list-item__text\" and contains(text(),\"Спам\")]")
    private WebElement contextMenuLetterToSpam;

    @FindBy(xpath = ".//span[@class=\"list-item__text\" and contains(text(),\"Не спам\")]")
    private WebElement contextMenuLetterFromSpam;

    @FindBy(xpath = ".//span[@class=\"list-item__text\" and contains(text(),\"Пометить флажком\")]")
    private WebElement contextMenuFlag;

    @FindBy(xpath = "(.//span[@class=\"notify__message__text\"])[1]")
    private WebElement notifyMessageToSpam;

    @FindBy(xpath = "(.//span[@class=\"notify__message__text\"])[1]")
    private WebElement notifyMessageToInbox;

    @FindBy(xpath = ".//a[@href=\"/spam/\"]")
    private WebElement spamTab;

    @FindBy(xpath = ".//a[@href=\"/inbox/\"]")
    private WebElement inboxTab;

    @FindBy(xpath = ".//div[@class=\"list-letter-tip__message\"]")
    private WebElement spamTabTitle;

    @FindBy(xpath = ".//span[@class=\"compose-button__wrapper\"]")
    private WebElement createNewLetterButton;

    @FindBy(xpath = ".//button[@data-promo-id=\"extend\"]")
    private WebElement createNewLetterExtendButton;

    @FindBy(xpath = ".//button[@data-promo-id=\"decrease\"]")
    private WebElement createNewLetterDecreaseButton;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div/div[1]/div/div[2]/div/div/label/div/div/input")
    private WebElement createNewLetterReceiverInput;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div/div[1]/div/div[1]/button")
    private WebElement createNewLetterReceiverListButton;

    @FindBy(xpath = "(.//input[@name=\"Subject\" and @type=\"text\"])")
    private WebElement createNewLetterSubjectInput;

    @FindBy(xpath = "(.//div[@role=\"textbox\"])")
    private WebElement createNewLetterBodyInput;

    @FindBy(xpath = ".//span[@class=\"button2 button2_base button2_primary button2_compact button2_hover-support js-shortcut\"]")
    private WebElement createNewLetterSendButton;

    @FindBy(xpath = ".//span[text()=\"отправлено\"]")
    private WebElement createNewLetterSendSuccess;

    @FindBy(xpath = ".//span[@title=\"Закрыть\"]")
    private WebElement createNewLetterSendSuccessCloseButton;

    @FindBy(xpath = "(.//div[@class=\"b-filelabel__item_title__text\"])[1]")
    private WebElement receiversList1;

    @FindBy(xpath = "(.//div[@class=\"b-filelabel__item_title__text\"])[2]")
    private WebElement receiversList2;

    @FindBy(xpath = "(.//div[@class=\"b-filelabel__item_title__text\"])[3]")
    private WebElement receiversList3;

    @FindBy(xpath = ".//span[@class=\"button2 button2_base button2_primary button2_hover-support\"]")
    private WebElement receiversListAddButton;

    @FindBy(xpath = ".//span[@title=\"Выделить все\"]")
    private WebElement selectAllButton;

    @FindBy(xpath = ".//span[@title=\"Ещё\"]")
    private WebElement moreOptionsButton;

    @FindBy(xpath = ".//span[@class=\"list-item__text\" and contains(text(),\"Снять флажок\")]")
    private WebElement moreOptionsRemoveFlag;

    private WebDriver driver;
    private Actions actions;

    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(this.driver);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }

    public String receiveAccountName() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(accountName));
        return accountName.getText();
    }

    public String firstLetterContextMenu() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//a[contains(@class,\"js-letter-list-item\")])[1]")));
        moveToElementAndContexClick(firstLetter);
        return contextMenuLetterToSpam.getText();
    }

    public void firstLetterSendToSpam() {
        moveToElementAndClick(contextMenuLetterToSpam);
    }

    public String firstLetterSendToSpamNotification() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(notifyMessageToSpam));
        return notifyMessageToSpam.getText();
    }

    public String navigateToSpamTab() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(spamTab));
        moveToElementAndClick(spamTab);
        return spamTabTitle.getText();
    }

    public void spamTabSendFirstLetterToInbox() {
        moveToElementAndClick(contextMenuLetterFromSpam);
    }

    public String spamTabSendFirstLetterToInboxNotification() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(notifyMessageToInbox));
        return notifyMessageToInbox.getText();
    }

    public String spamTabFirstLetterContextMenu() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(firstLetter));
        moveToElementAndContexClick(firstLetter);
        return contextMenuLetterFromSpam.getText();
    }

    public void sendNewLetterOpenForm() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//span[@class=\"compose-button__wrapper\"]")));
        moveToElementAndClick(createNewLetterButton);
        explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[@data-promo-id=\"extend\"]")));
        moveToElementAndClick(createNewLetterExtendButton);
    }

    public void sendNewLetterOpenContacts() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(createNewLetterReceiverInput));
        moveToElementAndClick(createNewLetterReceiverListButton);
    }

    public void sendNewLetterSelectReceivers() {
        moveToElementAndClick(receiversList1);
        moveToElementAndClick(receiversList2);
        moveToElementAndClick(receiversList3);
    }

    public void sendNewLetterAddContacts() {
        moveToElementAndClick(receiversListAddButton);
        moveToElementAndClick(createNewLetterSubjectInput);
    }

    public void sendNewLetterAddSubject() {
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(createNewLetterSubjectInput));
        moveToElementAndClick(createNewLetterSubjectInput);
        createNewLetterSubjectInput.sendKeys("Test Message: to 3 receivers");
    }

    public void sendNewLetterAddBody() {
        moveToElementAndClick(createNewLetterBodyInput);
        createNewLetterBodyInput.sendKeys("But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.\n" +
                "\n" +
                "No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful.\n" +
                "\n" +
                "Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure.\n" +
                "\n" +
                "To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it?\n" +
                "\n" +
                "But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?\n" +
                "\n" +
                "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain.\n" +
                "\n" +
                "These cases are perfectly simple and easy to distinguish.\n" +
                "\n" +
                "In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided.\n" +
                "\n" +
                "But in certain circumstances and owing to the claims of duty or the obligations of business it will");
    }

    public void sendNewLetterPressSend() {
        moveToElementAndClick(createNewLetterSendButton);
        //explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(createNewLetterSendSuccess));
    }

    public boolean flagThreeLetters() {
        // May be needed if executed in scenario (one-by-one
        //actions.tick().moveToElement(createNewLetterSendSuccessCloseButton).tick().click(createNewLetterSendSuccessCloseButton).build().perform();
        WebElement explicitWait = (new WebDriverWait(driver, 10)).pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(.//a[contains(@class,\"js-letter-list-item\")])[1]")));
        moveToElementAndClick(inboxTab);
        moveToElementAndContexClick(firstLetter);
        moveToElementAndClick(contextMenuFlag);
        moveToElementAndContexClick(secondLetter);
        moveToElementAndClick(contextMenuFlag);
        moveToElementAndContexClick(thirdLetter);
        moveToElementAndClick(contextMenuFlag);
        return removeFlagButton.isDisplayed();
    }

    public boolean removeAllFlags() {
        WebElement explicitWait = (new WebDriverWait(driver, 12)).pollingEvery(3, TimeUnit.SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//span[@title=\"Выделить все\"]")));
        moveToElementAndClick(selectAllButton);
        moveToElementAndClick(moreOptionsButton);
        moveToElementAndClick(moreOptionsButton);
        return false;
    }

    public void moveToElementAndClick(WebElement element) {
        actions.tick().moveToElement(element).tick().click(element).build().perform();
    }

    public void moveToElementAndContexClick(WebElement element) {
        actions.tick().moveToElement(element).tick().contextClick(element).build().perform();
    }

    public void sleepAlittle() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}