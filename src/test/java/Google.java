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
        Thread.sleep(120000);
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

        driver.get("https://www.google.com/search?source=hp&ei=wKz6W7zlO4qgjgav6qGADw&q=%D1%82%D0%B8%D0%BD%D1%8C%D0%BA%D0%BE%D1%84%D1%84+%D0%BC%D0%BE%D0%B1%D0%B0%D0%B9%D0%BB+%D1%82%D0%B0%D1%80%D0%B8%D1%84%D1%8B&oq=%D0%BC%D0%BE%D0%B1%D0%B0%D0%B9%D0%BB+%D1%82%D0%B8%D0%BD%D1%8C%D0%BA%D0%BE%D1%84%D1%84&gs_l=psy-ab.1.1.35i39l6.0.0..4349...1.0..0.0.0.......0....2..gws-wiz.....6.tKeBBnDIqbc");
        String searchSite = "https://www.tinkoff.ru/mobile-operator/tariffs/";
        WebElement googleList = driver.findElement(By.className("bkWMgd"));
        List<WebElement> optionsList = googleList.findElements(By.className("iUh30"));
        for (WebElement option : optionsList)
        {
            System.out.println(option.getText());
            if (option.getText().equals(searchSite))
            {
               //Так как у меня ссылка открыватся в этой же квладке, я вместо команды: option.click();
                //открываю новую вкладку и перехожу по урлу
                driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
                driver.navigate().to("https://www.tinkoff.ru/mobile-operator/tariffs/");
                Thread.sleep(5000);
                break;
            }
        }
        //Проверяем, что заголовок страницы равен Тарифы Тинькофф Мобайл
        String actual = driver.getTitle();
        Assert.assertEquals("Тарифы Тинькофф Мобайл", actual);

        // Закрываем предыдущую вкладку
        driver.switchTo().window(number);
        driver.quit();


    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
    }
}
