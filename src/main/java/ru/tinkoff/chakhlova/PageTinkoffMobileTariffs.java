package ru.tinkoff.chakhlova;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageTinkoffMobileTariffs {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(PageTinkoffMobileTariffs.class);
    private static final String URL = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    private static final By region = By.xpath("//div[contains(@class,\"MvnoRegionConfirmation\")][2]");
    private static final By defaultRegion = By.xpath("//span[text()=\"Да\"]");
    private static final By defaultPrice = By.xpath("(//h3[contains(@class,\"obileOperatorFormFieldTitle__title\")])[1]");
    private static final By selectInternet = By.xpath("(//div[@class=\"ui-dropdown-field-list\"])[1]/..");


    public PageTinkoffMobileTariffs(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public PageTinkoffMobileTariffs open() {
        logger.info("Открытие страницы: " + URL);
        webDriver.get(URL);
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.equals(selectInternet);
        return this;
    }

    //появляется всплывающее сообщение "Ваш регион Москва?" - нажимаем, что да
    public PageTinkoffMobileTariffs defaultRegion() {
        logger.info("Устанавливаем дефолтны регион Москва и область");
        WebElement regionMoscow = webDriver.findElement(defaultRegion);
        regionMoscow.click();
        return this;
    }

    //метод возвращает текущий регион
    public String getRegion() {
        logger.info("Определение региона");
        WebElement webElement = webDriver.findElement(region);
        String actual = webElement.getText();
        logger.info("Текущий регион" + actual);
        return actual;
    }

    // выполняем нажатие на текущий регион
    public PageTinkoffMobileTariffs clickRegion (){
        logger.info("Поиск элемента регион");
        WebElement webElement = webDriver.findElement(region);
        webElement.click();
        logger.info("Выполнинено нажатие на регион");
        return this;
    }

    //выбираем регион
    public void setRegion(String region) throws InterruptedException {
        logger.info("Поиск наличия региона - " + region);
        WebElement webElement = webDriver.findElement(By.xpath("//div[text()=\"" + region + "\"]"));
        logger.info("Выбор региона");
        webElement.click();
        Thread.sleep(5000);
    }

    //метод обновляет страницу
    public PageTinkoffMobileTariffs refreshPage() throws InterruptedException {
        logger.info("Обновляем страницу");
        webDriver.navigate().refresh();
        Thread.sleep(5000);
        return this;
    }
    //метод возвращает стоимость пакета услуг
    public String getPrice() {
        logger.info("Поиск стоимости пакета");
        WebElement webElement = webDriver.findElement(defaultPrice);
        String price = webElement.getText();
        return price;
    }

    //метод для возвращения значения выбран чекбокс или не выбран
    public boolean valueCheckBox (String nameCheckBox) {
        logger.info("Поиск чекбокса...");
        WebElement webElement = webDriver.findElement(By.xpath("//span[contains(text(),'" + nameCheckBox + "')]//ancestor::label[contains(@class,'ui-checkbox  ui-checkbox_checked') or contains(@class,'ui-checkbox')]"));
        String checkBox = webElement.getAttribute("class");

        String stroka = "ui-checkbox  ui-checkbox_checked";

        if (checkBox.equals(stroka)) {
            return true;
        }
        else {
            return false;
        }

    }

    //метод выполняет нажатие на чекбокс
    public void clickCheckBox(String locator) {
        logger.info("Поиск чекбокса...");
        WebElement webElement = webDriver.findElement(By.xpath(locator));
        logger.info("Нажатие чекбокс");
        webElement.click();
        logger.info("Выполнили нажатие");
    }

    //метод возвращает значение селекта
    public void getSelect (String locator) {
        logger.info("Поиск селекта...");
        WebElement select = webDriver.findElement(By.xpath(locator));
        String actualText = select.getText();
        logger.info("Текущее значение селекта " + actualText);

    }


    //выбираем значение выпадающего списка
    public void setSelect (String tarif, String locator, String selectItem){
        logger.info("Поиск селекта...");
        WebElement select = webDriver.findElement(By.xpath(locator));
        logger.info("Нажатие на селект");
        select.click();

        logger.info("Поиск элементов коллекции выпадающего списка...");
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(webDriver1 -> select.findElements(By.className(selectItem)).size() > 0);
        List<WebElement> dropDownList = select.findElements(By.className(selectItem));
        logger.info("Найдено: " + dropDownList.size() + " элемента");
        boolean founded = false;
        for (WebElement item : dropDownList) {
            String actualText = item.getText();
            logger.info("Поиск элемента коллекции: '" + tarif + "', текущий элемент: '" + actualText + "'");
            if (actualText.equals(tarif)) {
                logger.info("Нажатие на элемент коллекции: '" + actualText + "'");
                item.click();
                founded = true;
            }
        }
        if (!founded) throw new NoSuchElementException("Не найден элемент коллеции с значением: '" + tarif + "'");

    }

    }





