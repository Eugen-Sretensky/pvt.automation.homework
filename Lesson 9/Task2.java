import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Task2 {
    public static void main(String[] args) throws ParseException {
        System.setProperty("webdriver.chrome.driver", "C:\\Study\\Java\\Java Automation (PVT)\\chromedriver_win32\\chromedriver.exe");//your own path
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
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
        if ((Double.parseDouble(cheapestApartmentsPriceValueString)) / 6 > minPricePerNight) {
            System.out.println("Апартаменты действительно подходят!(их цена не ниже заявленной)");
            System.out.println("Переходим на их страницу...");
            cheapestApartment.click();
            System.out.println("ТЕСТ ПРОЙДЕН!");
        } else {
            System.out.println("Цена апартаментов не соответствует критериям поиска, тест провален!");
        }

    }
}