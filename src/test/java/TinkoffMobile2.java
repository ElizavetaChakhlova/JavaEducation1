import org.junit.Assert;
import org.junit.Test;
import ru.tinkoff.chakhlova.PageTinkoffMobileTariffs;

public class TinkoffMobile2 extends BaseRunner{

    @Test
    public void checkRegion() throws InterruptedException {
        PageTinkoffMobileTariffs tinkoffMobile = new PageTinkoffMobileTariffs(webDriver);
        tinkoffMobile
                .open()
                .defaultRegion()
                .checkRegion();

        Assert.assertEquals("Москва и Московская область", tinkoffMobile.getRegion());

        tinkoffMobile.refreshPage();

        Assert.assertEquals("Москва и Московская область", tinkoffMobile.getRegion());

        String defaultPrice = tinkoffMobile.getDefaultPrice();

        tinkoffMobile.selectInternet("Безлимитный интернет");
        tinkoffMobile.selectCall("Безлимитные минуты");
        tinkoffMobile.checkBoxModem();



    }

}
