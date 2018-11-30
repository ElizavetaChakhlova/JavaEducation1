import org.junit.Assert;
import org.junit.Test;
import ru.tinkoff.chakhlova.PageTinkoffMobileTariffs;

public class TinkoffMobile2 extends BaseRunner{

    @Test
    public void checkRegion() throws InterruptedException {
        PageTinkoffMobileTariffs tinkoffMobile = new PageTinkoffMobileTariffs(webDriver);
        tinkoffMobile
                .open()
                .defaultRegion();


        Assert.assertEquals("Москва и Московская область", tinkoffMobile.getRegion());

        tinkoffMobile.refreshPage();

        Assert.assertEquals("Москва и Московская область", tinkoffMobile.getRegion());

        String priceMoscowActual = tinkoffMobile.getPrice();

        tinkoffMobile.setSelect("Безлимитный интернет", "(//div[@class=\"ui-dropdown-field-list\"])[1]/..", "ui-dropdown-field-list__item-text");
        tinkoffMobile.setSelect("Безлимитные минуты", "(//div[@class=\"ui-dropdown-field-list\"])[2]/..", "ui-dropdown-field-list__item-text");
        tinkoffMobile.clickCheckBox("(//div[@class=\"ui-form__row ui-form__row_checkbox ui-form__row_mobile-operator-checkbox\"])[1]");
        tinkoffMobile.clickCheckBox("(//div[@class=\"ui-checkbox__check\"])[1]");

        ////сохраняем стоимость максимального пакета в Москве
        String MaxpriceMoscow = tinkoffMobile.getPrice();

        tinkoffMobile
                .clickRegion()
                .setRegion("Краснодарский к.");

        Assert.assertEquals("Краснодарский край", tinkoffMobile.getRegion());

        String MaxpriceKrasnodar= tinkoffMobile.getPrice();

        tinkoffMobile.refreshPage();

        String priceKrasnodarActual = tinkoffMobile.getPrice();

        Assert.assertEquals(priceMoscowActual, priceKrasnodarActual);

        Assert.assertEquals(MaxpriceKrasnodar,MaxpriceMoscow);



    }

}
