import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Booking {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:\\И такое бывает\\PVT_Automation\\chromedriver_win32\\chromedriver.exe");//your own path
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.booking.com");
        WebElement searchLocation = driver.findElement(By.xpath(".//input[@type=\"search\" and @name=\"ss\"]"));
        searchLocation.sendKeys("Москва");
        searchLocation.submit();
        WebElement otherFilterOptions = driver.findElement(By.xpath(".//button[@id=\"sortbar_dropdown_button\" and @class=\"sort_more_options__button sort_option\"]"));
        otherFilterOptions.click();
        WebElement sortByRating = driver.findElement(By.xpath(".//a[@data-category=\"bayesian_review_score\" and @role=\"menuitem\"]"));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sortByRating.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement firstApartmentsLink = driver.findElement(By.xpath("(.//a[@class=\"hotel_name_link url\"])[1]"));
        firstApartmentsLink.click();
        WebElement overallRatingScore = driver.findElement(By.xpath("(.//div[@class=\"bui-review-score__badge\"])[1]"));
        String overallRating = overallRatingScore.getAttribute("aria-label");
        String overallRatingValueString = overallRating.substring(overallRating.indexOf(" "));
        overallRatingValueString = overallRatingValueString.replace(" ", "");
        overallRatingValueString = overallRatingValueString.replace(",", ".");
        double overallRatingValueDouble = Double.parseDouble(overallRatingValueString);
        if(overallRatingValueDouble >= 9){
            System.out.println("АПАРТАМЕНТЫ ПОДХОДЯТ! (ИХ РЕЙТИНГ ВЫШЕ 9)");
        }else{
            System.out.println("АПАРТАМЕНТЫ НЕ ПОДХОДЯТ!(СЛИШКОМ НИЗКИЙ РЕЙТИНГ)");
        }
        /*driver.quit();*/
    }
}
