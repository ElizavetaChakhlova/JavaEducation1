import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Google extends TestCase {

    public WebDriver driver;

    @Override
    protected void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testForm() throws InterruptedException {

        //открываем гугл и ищем поисковую строку
        driver.get("https://www.google.ru/");
        Thread.sleep(5000);
        WebElement webElement = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));

        //ввоим в поисковую строку "мобайл тинькофф"
        webElement.sendKeys("мобайл тинькофф");
        Thread.sleep(5000);

        //идем в списке вариантов мобайл тинькофф" тарифы
        String searchText = "тинькофф мобайл тарифы";
        String number = driver.getWindowHandle();
        WebElement dropdown = driver.findElement(By.className("erkvQe"));
        List<WebElement> options = dropdown.findElements(By.tagName("li"));
        for (WebElement option : options)
        {
            if (option.getText().equals(searchText))
            {
                option.click();
                Thread.sleep(5000);
                break;
            }
        }

        String searchSite = "https://www.tinkoff.ru/mobile-operator/tariffs/";
        WebElement googleList = driver.findElement(By.className("bkWMgd"));
        List<WebElement> optionsList = googleList.findElements(By.className("iUh30"));
        for (WebElement option : optionsList)
        {
            System.out.println(option.getText());
            if (option.getText().equals(searchSite))
            {

                option.click();
                Thread.sleep(5000);
                break;
            }
        }
        //Проверяем, что заголовок страницы равен Тарифы Тинькофф Мобайл
        String actual = driver.getTitle();
        Assert.assertEquals("Тарифы Тинькофф Мобайл", actual);

        // Закрываем предыдущую вкладку
        driver.switchTo().window(number);
        Thread.sleep(5000);
        //driver.quit();

    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
    }
}
