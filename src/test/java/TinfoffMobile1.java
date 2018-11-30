import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TinfoffMobile1 extends TestCase {
    public WebDriver driver;

    @Override
    protected void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testForm() throws InterruptedException {

        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        Thread.sleep(5000);

        //выбираем, что регион у нас Москва и область
        WebElement regionMoscow = driver.findElement(By.xpath("//span[text()=\"Да\"]"));
        regionMoscow.click();
        Thread.sleep(5000);

        //сохраняем текущее значение региона и проверяем, что оно равно Москва и область
        WebElement region = driver.findElement(By.xpath("//div[contains(@class,\"MvnoRegionConfirmation\")][2]"));
        String actual = region.getText();
        Assert.assertEquals("Москва и Московская область", actual);

        //проверяем, что после обновления страницы регион все еще Москва и область
        driver.navigate().refresh();
        Thread.sleep(5000);
        Assert.assertEquals("Москва и Московская область", actual);

        //сохраняем стоимость пакета по умолчанию для Москвы и области
        WebElement priceMoscow = driver.findElement(By.xpath("(//h3[contains(@class,\"obileOperatorFormFieldTitle__title\")])[1]"));
        region = driver.findElement(By.xpath("//div[contains(@class,\"MvnoRegionConfirmation\")][2]"));
        String priceMoscowActual = priceMoscow.getText();

        //сохраняем стоимость максимального пакета
        WebElement internet = driver.findElement(By.xpath("(//div[@class=\"ui-dropdown-field-list\"])[1]/.."));
        List<WebElement> options = internet.findElements(By.className("ui-dropdown-field-list__item-text"));
        String searchText = "Безлимитный интернет";
        internet.click();
        for (WebElement option : options)
        {
            System.out.println(option.getText());
            if (option.getText().equals(searchText))
            {

                option.click();
                Thread.sleep(5000);
                break;
            }
        }

        WebElement call = driver.findElement(By.xpath("(//div[@class=\"ui-dropdown-field-list\"])[2]/.."));
        List<WebElement> optionsCall = call.findElements(By.className("ui-dropdown-field-list__item-text"));
        String searchCall= "Безлимитные минуты";
        call.click();
        for (WebElement option : optionsCall)
        {
            System.out.println(option.getText());
            if (option.getText().equals(searchCall))
            {
                option.click();
                Thread.sleep(5000);
                break;
            }
        }
        Thread.sleep(5000);
        WebElement modem = driver.findElement(By.xpath("(//div[@class=\"ui-form__row ui-form__row_checkbox ui-form__row_mobile-operator-checkbox\"])[1]"));
        modem.click();
        WebElement sms = driver.findElement(By.xpath("(//div[@class=\"ui-checkbox__check\"])[1]"));
        sms.click();

        priceMoscow = driver.findElement(By.xpath("(//h3[contains(@class,\"obileOperatorFormFieldTitle__title\")])[1]"));
        String MaxpriceMoscow= priceMoscow.getText();

        //меняем регион на Краснодар
        region.click();
        WebElement regionKrasnodar = driver.findElement(By.xpath("//div[text()=\"Краснодарский к.\"]"));
        regionKrasnodar.click();

        //Проверяем, что регион изменился на Краснодар
        region = driver.findElement(By.xpath("//div[contains(@class,\"MvnoRegionConfirmation\")][2]"));
        actual = region.getText();
        Assert.assertEquals("Краснодарский к.", actual);

        //сохраняем стоимость максимального пакета
        WebElement priceKrasnodar = driver.findElement(By.xpath("(//h3[contains(@class,\"obileOperatorFormFieldTitle__title\")])[1]"));
        String MaxpriceKrasnodar= priceKrasnodar.getText();

        driver.navigate().refresh();
        Thread.sleep(5000);

        //сохраняем стоимость пакета по умолчанию для Краснодара
        priceKrasnodar = driver.findElement(By.xpath("(//h3[contains(@class,\"obileOperatorFormFieldTitle__title\")])[1]"));
        String priceKrasnodarActual = priceKrasnodar.getText();

        //Провряем, что стоимости по умолчанию для Москвы и Краснодара разные
        Assert.assertNotSame(priceMoscowActual,priceKrasnodarActual);

        //Провряем, что максимальные стоимости для Москвы и Краснодара разные
        Assert.assertEquals(MaxpriceKrasnodar,MaxpriceMoscow);


    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
    }
}
