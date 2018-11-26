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
    private Logger logger = Logger.getLogger(PageTinkoffMobile.class);
    private static final String URL = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    private static final By region = By.xpath("//div[contains(@class,\"MvnoRegionConfirmation\")][2]");
    private static final By defaultRegion = By.xpath("//span[text()=\"Да\"]");
    private static final By defaultPrice = By.xpath("(//h3[contains(@class,\"obileOperatorFormFieldTitle__title\")])[1]");
    private static final By selectInternet = By.xpath("(//div[@class=\"ui-dropdown-field-list\"])[1]/..");
    private static final By selectInternetItem = By.className("ui-dropdown-field-list__item-text");
    private static final By selectCall = By.xpath("(//div[@class=\"ui-dropdown-field-list\"])[2]/..");
    private static final By selectCallItem = By.className("ui-dropdown-field-list__item-text");
    private static final By modem = By.xpath("(//div[@class=\"ui-form__row ui-form__row_checkbox ui-form__row_mobile-operator-checkbox\"])[1]");


    private static final By FORM = By.xpath("//div[@id=\"form-application\"]");
    private static final By BTN_NEXT = By.xpath("//button[string()='Далее']");
    private static final By inputCountry = By.xpath("//div[@data-qa-file=\"FormFieldWrapper\" and contains(string(),'стран')]");
    private static final By inputFIOError = By.xpath("//div[@data-qa-file=\"FormFieldWrapper\" and contains(string(),'Фамилия')]//div[@data-qa-file=\"UIFormRowError\"]");
    private static final By inputNumberError = By.xpath("//div[@data-qa-file=\"FormFieldWrapper\" and contains(string(),'Контактный телефон')]//div[@data-qa-file=\"UIFormRowError\"]");
    private static final By selectCityzenship = By.xpath("//div[@data-qa-file=\"FormFieldWrapper\" and contains(string(),'Гражданство') and .//*[@data-qa-file=\"UIDropdownList\"]]");
    private static final By selectCityzenshipItem = By.xpath("//div[@data-qa-file=\"FormFieldWrapper\" and contains(string(),'Гражданство') and .//*[@data-qa-file=\"UIDropdownList\"]]//div[@class=\"ui-dropdown-field-list ui-dropdown-field-list__opened\"]/div[contains(@class,'ui-dropdown-field-list__item')]");


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
    public PageTinkoffMobileTariffs defaultRegion() {
        logger.info("Устанавливаем дефолтны регион Москва и область");
        WebElement regionMoscow = webDriver.findElement(defaultRegion);
        regionMoscow.click();
        return this;
    }
    public String checkRegion() {
        logger.info("Определение региона");
        WebElement webElement = webDriver.findElement(region);
        String actual = webElement.getText();
        logger.info("Текущий регион " + actual);
        return actual;
    }
    public String getRegion() {
        logger.info("Определение региона");
        WebElement webElement = webDriver.findElement(region);
        String actual = webElement.getText();
        logger.info("Текущий регион" + actual);
        return actual;
    }

    public PageTinkoffMobileTariffs refreshPage() throws InterruptedException {
        logger.info("Обновляем страницу");
        webDriver.navigate().refresh();
        Thread.sleep(5000);
        return this;
    }
    public String getDefaultPrice() {
        logger.info("Поиск стоимости пакета");
        WebElement webElement = webDriver.findElement(defaultPrice);
        String price = webElement.getText();
        return price;
    }

    public PageTinkoffMobileTariffs checkBoxModem() {
        logger.info("Поиск чекбокса Модем");
        WebElement webElement = webDriver.findElement(modem);
        logger.info("Нажатие чекбокс: Модем");
        webElement.click();
        logger.info("Выполнили нажатие");
        return this;
    }


    public void selectInternet (String tarifForInternet) {
        logger.info("Поиск селекта: Интернет");
        WebElement select = webDriver.findElement(selectInternet);
        logger.info("Нажатие на селект: Интернет");
        select.click();

        logger.info("Поиск элементов коллекции выпадающего списка: Интернет");
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(webDriver1 -> select.findElements(selectInternetItem).size() > 0);
        List<WebElement> dropDownList = select.findElements(selectInternetItem);
        logger.info("Найдено: " + dropDownList.size() + " элемента");
        boolean founded = false;
        for (WebElement item : dropDownList) {
            String actualText = item.getText();
            logger.info("Поиск элемента коллекции: '" + tarifForInternet + "', текущий элемент: '" + actualText + "'");
            if (actualText.equals(tarifForInternet)) {
                logger.info("Нажатие на элемент коллекции: '" + actualText + "'");
                item.click();
                founded = true;
            }
        }
        if (!founded) throw new NoSuchElementException("Не найден элемент коллеции: 'Интернет' с значением: '" + tarifForInternet + "'");

    }


    public void selectCall (String tarifForCall) {
        logger.info("Поиск селекта: Звонки");
        WebElement select = webDriver.findElement(selectCall);
        logger.info("Нажатие на селект: Звонки");
        select.click();

        logger.info("Поиск элементов коллекции выпадающего списка: Звонки");
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(webDriver1 -> select.findElements(selectCallItem).size() > 0);
        List<WebElement> dropDownList = select.findElements(selectCallItem);
        logger.info("Найдено: " + dropDownList.size() + " элемента");
        boolean founded = false;
        for (WebElement item : dropDownList) {
            String actualText = item.getText();
            logger.info("Поиск элемента коллекции: '" + tarifForCall + "', текущий элемент: '" + actualText + "'");
            if (actualText.equals(tarifForCall)) {
                logger.info("Нажатие на элемент коллекции: '" + actualText + "'");
                item.click();
                founded = true;
            }
        }
        if (!founded) throw new NoSuchElementException("Не найден элемент коллеции: 'Звонки' с значением: '" + tarifForCall + "'");

    }

    }



