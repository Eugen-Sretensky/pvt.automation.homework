import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Task1 {
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
        //Париж
        WebElement searchLocation = driver.findElement(By.xpath(".//input[@type=\"search\" and @name=\"ss\"]"));
        searchLocation.sendKeys("Paris");
        searchLocation.submit();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Отфильтровать самые дешевые
        WebElement filterLowestPrice = driver.findElement(By.xpath(".//a[@data-id=\"pri-1\"]"));
        int lowestPriceOptionsCount = Integer.parseInt(filterLowestPrice.getAttribute("data-count"));
        filterLowestPrice.click();
        double maxPricePerNight = Double.parseDouble(filterLowestPrice.getAttribute("data-value"));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //проверить, что такие отели есть
        WebElement recordsFound = driver.findElement(By.xpath(".//h2[contains(text(), \"Paris:\")]"));
        String recordsFoundCounter = recordsFound.getText();
        recordsFoundCounter = recordsFoundCounter.replace("Paris: ", "");
        recordsFoundCounter = recordsFoundCounter.replace(" properties found", "");
        recordsFoundCounter = recordsFoundCounter.replace(",", "");
        int recordsFoundCounterValue = Integer.parseInt(recordsFoundCounter);
        if (recordsFoundCounterValue == lowestPriceOptionsCount) {
            System.out.println("Варианты найдены!");
        } else {
            System.out.println("Варианты не найдены!");
            driver.quit();
        }
        WebElement removeOutOfStock = driver.findElement(By.xpath(".//div[@id=\"filter_out_of_stock\"]//a"));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        removeOutOfStock.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Выбрать с самым высоким рейтингом
        List<WebElement> foundApartmentsList = driver.findElements(By.xpath(".//div[@class=\"sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout         \"]"));
        String searchedApartmentsHotelId = "0";
        double maxRating = 0;
        for (int i = 0; i < foundApartmentsList.size(); i++) {
            if (foundApartmentsList.get(i).getAttribute("data-score").equals("")) {
                continue;
            }
            if (maxRating < Double.parseDouble(foundApartmentsList.get(i).getAttribute("data-score"))) {
                maxRating = Double.parseDouble(foundApartmentsList.get(i).getAttribute("data-score"));
                searchedApartmentsHotelId = foundApartmentsList.get(i).getAttribute("data-hotelid");
            }
        }
        System.out.println("Выбраны апартаменты с наибольшим рейтингом " + "(их ID = " + searchedApartmentsHotelId +")");
        WebElement searchedApartments = driver.findElement(By.xpath(".//div[@class=\"sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout         \" and @data-hotelid=" + searchedApartmentsHotelId + "]//a[@class=\" sr_item_photo_link sr_hotel_preview_track  \"]"));
        WebElement searchedApartmentsPrice = driver.findElement(By.xpath(".//div[@class=\"sr_item  sr_item_new sr_item_default sr_property_block  sr_flex_layout         \" and @data-hotelid=" + searchedApartmentsHotelId + "]//*//div[@class=\"bui-price-display__value prco-inline-block-maker-helper\"]"));
        //и проверить, что цена не превышает заданный диапазон.
        String searchedApartmentsPriceValueString = searchedApartmentsPrice.getText();
        searchedApartmentsPriceValueString = searchedApartmentsPriceValueString.substring(searchedApartmentsPriceValueString.indexOf(" "));
        searchedApartmentsPriceValueString = searchedApartmentsPriceValueString.replaceAll("", "");
        searchedApartmentsPriceValueString = searchedApartmentsPriceValueString.replaceAll(",","");
        if ((Double.parseDouble(searchedApartmentsPriceValueString)/6) < maxPricePerNight){
            System.out.println("Апартаменты действительно подходят!(их цена соответствует заявленной)");
            System.out.println("ТЕСТ ПРОЙДЕН!");
            System.out.println("Переходим на их страницу...");
            searchedApartments.click();
        }else {
            System.out.println("Цена апартаментов не соответствует критериям поиска, тест провален!");
        }
    }
}