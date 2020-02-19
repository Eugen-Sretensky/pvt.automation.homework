import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

public class Task3 {
    public static void main(String[] args) throws ParseException {
        System.setProperty("webdriver.chrome.driver", "C:\\Study\\Java\\Java Automation (PVT)\\chromedriver_win32\\chromedriver.exe");//your own path
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
        Actions builder = new Actions(driver);
        driver.manage().window().maximize();
        driver.get("http://www.booking.com");
        WebElement checkInOutCalendar = driver.findElement(By.xpath("(.//span[@class=\"sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb\"])[1]"));
        checkInOutCalendar.click();

        //текущая дата + 3 дня, на 7 дней
        WebElement todayDate = driver.findElement(By.xpath(".//td[@class=\"bui-calendar__date bui-calendar__date--today\"]"));
        String todayDateValue = todayDate.getAttribute("data-date");
        SimpleDateFormat bookingDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bookingDateFormat.parse(todayDateValue));
        calendar.add(Calendar.DATE, 3);
        String checkInDate = bookingDateFormat.format(calendar.getTime());
        WebElement checkIn = driver.findElement(By.xpath(".//td[@class=\"bui-calendar__date\" and @data-date=\"" + checkInDate + "\"]"));
        checkIn.click();
        calendar.add(Calendar.DATE, 6);
        String checkOutDate = bookingDateFormat.format(calendar.getTime());
        WebElement checkOut = driver.findElement(By.xpath(".//td[@class=\"bui-calendar__date\" and @data-date=\"" + checkOutDate + "\"]"));
        checkOut.click();

        //4 взрослых, 2 номера
        WebElement guestAndRoomsSelection = driver.findElement(By.xpath(".//label[@aria-controls=\"xp__guests__inputs-container\"]"));
        guestAndRoomsSelection.click();
        WebElement increaseAdultGuestCount = driver.findElement(By.xpath(".//button[@aria-label=\"Increase number of Adults\"]"));
        increaseAdultGuestCount.click();
        increaseAdultGuestCount.click();
        WebElement adultGuestCount = driver.findElement(By.xpath("(.//span[@class=\"bui-stepper__display\"])[1]"));
        if (Integer.parseInt(adultGuestCount.getText()) == 4) {
            System.out.println("Кол-во взрослых гостей увеличено до 4");
        } else {
            System.out.println("Не удалось добавить необходимое кол-во взрослых гостей");
            driver.quit();
        }
        WebElement increaseRoomsCount = driver.findElement(By.xpath(".//button[@aria-label=\"Increase number of Rooms\"]"));
        increaseRoomsCount.click();
        WebElement roomsCount = driver.findElement(By.xpath("(.//span[@class=\"bui-stepper__display\"])[3]"));
        if (Integer.parseInt(roomsCount.getText()) == 2) {
            System.out.println("Кол-во комнат увеличено до 4");
        } else {
            System.out.println("Не удалось добавить необходимое кол-во комнат");
            driver.quit();
        }
        //Париж
        WebElement searchLocation = driver.findElement(By.xpath(".//input[@type=\"search\" and @name=\"ss\"]"));
        searchLocation.sendKeys("Paris");
        searchLocation.submit();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Отфильтровать самые дорогие
        WebElement filterHighestPrice = driver.findElement(By.xpath(".//a[@data-id=\"pri-5\"]"));
        int highestPriceOptionsCount = Integer.parseInt(filterHighestPrice.getAttribute("data-count"));
        WebElement highestPriceLowerLimit = driver.findElement(By.xpath(".//a[@data-id=\"pri-4\"]"));
        double minPricePerNight = Double.parseDouble(highestPriceLowerLimit.getAttribute("data-value"));
        filterHighestPrice.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement removeOutOfStock = driver.findElement(By.xpath(".//div[@id=\"filter_out_of_stock\"]//a"));
        removeOutOfStock.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Выбрать самый дешевый из самых дорогих
        WebElement sortByLowestPrice = driver.findElement(By.xpath(".//a[@class=\"sort_option \" and @data-category=\"price\"]"));
        sortByLowestPrice.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement cheapestApartment = driver.findElement(By.xpath("(.//div[@class=\"sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout         \"])[1]"));
        String cheapestApartmentHotelId = cheapestApartment.getAttribute("data-hotelid");
        System.out.println("Выбраны апартаменты с наименьшей ценой " + "(их ID = " + cheapestApartmentHotelId + ")");

        //и проверить, что цена не ниже заданного диапазона
        WebElement cheapestApartmentsPrice = driver.findElement(By.xpath(".//div[@class=\"sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout         \" and @data-hotelid=" + cheapestApartmentHotelId + "]//*//div[@class=\"bui-price-display__value prco-inline-block-maker-helper\"]"));
        String cheapestApartmentsPriceValueString = cheapestApartmentsPrice.getText();
        cheapestApartmentsPriceValueString = cheapestApartmentsPriceValueString.substring(cheapestApartmentsPriceValueString.indexOf(" "));
        cheapestApartmentsPriceValueString = cheapestApartmentsPriceValueString.replaceAll("", "");
        cheapestApartmentsPriceValueString = cheapestApartmentsPriceValueString.replaceAll(",", "");
        WebElement cheapestApartmentReserveButton = driver.findElement(By.xpath("(.//a[@class=\"txp-cta bui-button bui-button--primary sr_cta_button\"])[1]"));
        if ((Double.parseDouble(cheapestApartmentsPriceValueString)) / 6 > minPricePerNight) {
            System.out.println("Апартаменты действительно подходят!(их цена не ниже заявленной)");
            System.out.println("Переходим на их страницу...");
            cheapestApartmentReserveButton.click();
        } else {
            System.out.println("Цена апартаментов не соответствует критериям поиска, тест провален!");
        }

        //На отеле из предыдущего кейса забронировать номер
        Set<String> windowHandles = driver.getWindowHandles();//вернуть все окна
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
        }
        System.out.println("Подтверждаем наш выбор");
        WebElement explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@data-hotel-select-rooms]")));
        WebElement reserveYourSelectionsButton = driver.findElement(By.xpath(".//a[@data-hotel-select-rooms]"));
        reserveYourSelectionsButton.click();
        explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\" and @data-title]")));
        WebElement submitSelectionButton = driver.findElement(By.xpath("//button[@type=\"submit\" and @data-title]"));
        System.out.println("Переходим на страницу резервирования...");
        Action mouseOverSubmitSelectionButton = builder.moveToElement(submitSelectionButton).build();
        mouseOverSubmitSelectionButton.perform();
        explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class=\"hprt-booking-summary-rooms-and-price\"]//strong")));
        builder.doubleClick(submitSelectionButton).perform();

        //ввести все поля
        explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//label[@for=\"bp_travel_purpose_business\"]")));
        WebElement travelPurposeBusiness = driver.findElement(By.xpath(".//label[@for=\"bp_travel_purpose_business\"]"));
        travelPurposeBusiness.click();
        WebElement bookerTitle = driver.findElement(By.xpath(".//select[@name=\"booker_title\"]"));
        Select bookerTitleSelect = new Select(bookerTitle);
        bookerTitleSelect.selectByValue("mr");
        WebElement firstNameInput = driver.findElement(By.xpath(".//input[@id=\"firstname\"]"));
        firstNameInput.sendKeys("FirstName");
        WebElement lastNameInput = driver.findElement(By.xpath(".//input[@id=\"lastname\"]"));
        lastNameInput.sendKeys("LastName");
        WebElement bookingForMainGuest = driver.findElement(By.xpath(".//input[@id=\"notstayer_false\"]"));
        bookingForMainGuest.click();
        WebElement emailInput = driver.findElement(By.xpath(".//input[@id=\"email\"]"));
        emailInput.sendKeys("worka1qawork+BookingTest2@gmail.com");
        WebElement emailInputConfirm = driver.findElement(By.xpath(".//input[@id=\"email_confirm\"]"));
        emailInputConfirm.sendKeys("worka1qawork+BookingTest2@gmail.com");
        emailInputConfirm.submit();
        System.out.println("Переходим на страницу оплаты...");

        //ввести все поля
        explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//input[@id=\"phone\"]")));
       /* if (driver.findElement(By.xpath("\".//input[@id=\\\"address1\\\"]\"")).isDisplayed()) {
            WebElement addressInput1 = driver.findElement(By.xpath(".//input[@id=\"address1\"]"));
            addressInput1.sendKeys("Test Address #1");
        }
        if (driver.findElement(By.xpath(".//input[@id=\"city\"]")).isDisplayed()) {
            WebElement cityInput = driver.findElement(By.xpath(".//input[@id=\"city\"]"));
            cityInput.sendKeys("Minsk");
        }
        if (driver.findElement(By.xpath(".//input[@id=\"zip\"]")).isDisplayed()) {
            WebElement zipCodeInput = driver.findElement(By.xpath(".//input[@id=\"zip\"]"));
            zipCodeInput.sendKeys("22000");
        */
        WebElement phoneInput = driver.findElement(By.xpath(".//input[@id=\"phone\"]"));
        phoneInput.click();
        phoneInput.sendKeys("2911111111");
        WebElement cardType = driver.findElement(By.xpath(".//select[@name=\"cc_type\"]"));
        Select cardTypeSelect = new Select(cardType);
        cardTypeSelect.selectByValue("Visa");
        WebElement expirationDateYear = driver.findElement(By.xpath(".//select[@id=\"ccYear\"]"));
        Select expirationDateYearSelect = new Select(expirationDateYear);
        expirationDateYearSelect.selectByValue("2025");
        WebElement cardNumberInput = driver.findElement(By.xpath(".//input[@id=\"cc_number\"]"));
        cardNumberInput.click();
        //Номер карты – 4242 4242 4242 4242, остальные данные любые
        cardNumberInput.sendKeys("4242 4242 4242 4242");
        WebElement cardCVCInput = driver.findElement(By.xpath(".//input[@id=\"cc_cvc\"]"));
        cardCVCInput.sendKeys("111");
        System.out.println("Подтверждаем ввод");
        cardCVCInput.submit();
        System.out.println("Ожидаем результат резервирования...");

        //проверить, что бронь прошла
        explicitWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@class=\"modal-wrapper\"]")));
        WebElement embededRegistrationCloseIcon = driver.findElement(By.xpath(".//*[@class=\"bk-icon -iconset-close\"]"));
        embededRegistrationCloseIcon.click();
        WebElement reservationStatus = driver.findElement(By.xpath(".//h1[@class=\"reassurance__title\"]"));
        String reservationStatusExpectedResult = "Your booking in Paris is confirmed.";
        if (reservationStatusExpectedResult.equals(reservationStatus.getText())) {
            System.out.println("Бронирование успешно завершено!");
        } else {
            System.out.println("Бронирование отклонено -- ТЕСТ НЕ ПРОЙДЕН!");
            driver.quit();
        }
        try {
            WebElement paymentStatus = driver.findElement(By.xpath(".//span[@class=\"bui-alert__title\"]"));
            String paymentStatusExpectedResult = "Your card was declined";
            if (paymentStatusExpectedResult.equals(paymentStatus.getText())) {
                System.out.println("Карта отклонена -- ТЕСТ ПРОЙДЕН!");
            }
        } catch (Exception e) {
            System.out.println("Карта принята -- ТЕСТ ПРОЙДЕН!");
        }
    }
}