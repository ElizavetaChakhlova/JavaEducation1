
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseRunner {

    private Logger logger = Logger.getLogger(BaseRunner.class);
    protected WebDriver webDriver;

    @Before
    public void init() {
        setProperties();
        logger.info("Инициализация веб драйвера");
        webDriver = new ChromeDriver(getOptions());
    }

    private ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //добавляем ключ в хром для того чтобы отключить нотификации браузера которые блокируют работу с контентом
        chromeOptions.addArguments("--disable-notifications");
        return chromeOptions;
    }

    private void setProperties() {
        String os = System.getProperty("os.name").toLowerCase();
        logger.info("Текущая ОС: " + os);
            System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/win/chromedriver.exe");

    }

    @After
    public void quit() {
        logger.info("Закрытие браузера");
        webDriver.quit();
    }
}
