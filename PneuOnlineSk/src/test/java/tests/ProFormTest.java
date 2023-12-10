package tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProFormTest extends Main {

    // Vytiahnutie údaju o počte pneumatík "Zobraziť X produktov" - to be parsed
    public String productsCount() {
        return driver.findElement(By.cssSelector(".btn.btn.btn-default")).getText();
    }

    @Test
    public void ProDefaultFillInputsBasicTest() throws InterruptedException {
        // Overenie výsledku s prednastavenou hodnotou polí
        // Tlačidlo „Zobraziť rozšírený filter“ - Prístup k rozšírenému filtru
        buttonFilterExtended().click();
        // Pole Rozmer - Čakanie na element
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("id_dimension")));
        // Pole Rozmer - Overenie, že pole neobsahuje default hodnotu
        Assert.assertTrue(driver.findElement(By.id("id_dimension")).getText().isEmpty());
        // Pole Sezóna - Overenie, že pole obsahuje default hodnotu
        Assert.assertFalse(driver.findElement(By.xpath("//div[contains(@class, 'controls col-sm-6')]//div[@id='id_season_chosen']/a"))
                .getText().isEmpty());
        // Tlačidlo „Zobraziť pneumatiky“ - Prístup k výsledku vyhľadávania
        button().click();

        // Podstránka „Pneumatiky“
        // Pole Sezóna - Porovnanie default hodnoty a výsledku vyhľadávania
        Assert.assertEquals("Sezóna: Zimná", driver.findElement(By.
                xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div/div[2]")).getText());
    }

    @Test
    @Ignore
    public void ProSuccessFillInputsTest() throws InterruptedException {
        // Overenie výsledku na základe výberu z ponuky + počet vyhľadaných pneumatík
        // Tlačidlo „Zobraziť rozšírený filter“ - Prístup k rozšírenému filtru
        buttonFilterExtended().click();
        // Pole Rozmer - Vyplnená nami určenou vlastnou hodnotou
        filterDimension("2055516");
        // Pole Sezóna - Vyplnená hodnotou z výberu možností
        filterSeason("Letná");
        // Tlačidlo „Zobraziť pneumatiky“ - Prístup k výsledku vyhľadávania
        button().click();

        // Podstránka „Pneumatiky“
        // Porovnanie našich vstupných očakávaných hodnôt a aktuálnych hodnôt
        // Pole Rozmer - Porovnanie výberu z možností a výsledkom vyhľadávania
        Assert.assertEquals("Rozmer: 2055516", driver.findElement(By.
                xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div/div[1]")).getText());
        // Pole Sezóna - Porovnanie výberu z možností a výsledkom vyhľadávania
        Assert.assertEquals("Sezóna: Letná", driver.findElement(By.
                xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div/div[3]")).getText());

        // Čakanie na tlačidlo „Zobraziť X pruduktov“ v okne „FILTER“
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".btn.btn.btn-default")));

        // Porovnanie hodnôt počtu produktov/pneumatík
        // Očakávaná hodnota X nemusí stále sedieť, kedže DATA neustále pracujú, Druhý atribút je sparsovaný počet produktov
        Assert.assertEquals(527, Integer.parseInt(productsCount().replaceAll("\\D", "")));
        System.out.println("Počet produktov sa mení, keďže DATA neustále pracujú!!!");
    }

    @Test
    public void ProAssertEqualsZeroTest() throws InterruptedException {
        // Overenie výsledku s prednastavenou hodnotou polí, tak aby sa rovnal výsledok vyhľadávaných produktov NULE
        // Tlačidlo „Zobraziť rozšírený filter“ - Prístup k rozšírenému filtru
        buttonFilterExtended().click();
        // Pole Rozmer - Vyplnená nami určenou vlastnou hodnotou
        filterDimension("1010100");
        // Pole Sezóna - Vyplnená hodnotou z výberu možností
        filterSeason("Letná");
        // Tlačidlo „Zobraziť pneumatiky“ - Prístup k výsledku vyhľadávania
        button().click();

        // Podstránka „Pneumatiky“
        // Porovnanie našich vstupných očakávaných hodnôt a aktuálnych hodnôt
        // Pole Rozmer - Porovnanie výberu z možností a výsledkom vyhľadávania
        Assert.assertEquals("Rozmer: 1010100", driver.findElement(By.
                xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div/div[1]")).getText());
        // Pole Sezóna - Porovnanie výberu z možností a výsledkom vyhľadávania
        Assert.assertEquals("Sezóna: Letná", driver.findElement(By.
                xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div/div[3]")).getText());

        // Čakanie na tlačidlo „Zobrazit X pruduktov“ v okne „FILTER“
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector(".btn.btn.btn-default")));

        // Porovnanie hodnôt počtu produktov/pneumatík
        // Očakávaná hodnota 0 ako počet produktov, Druhý atribút je sparsovaný počet produktov
        Assert.assertEquals(0, Integer.parseInt(productsCount().replaceAll("\\D", "")));
    }

    @Test
    public void ProChangeFillInputsTest() throws InterruptedException {
        // Overenie zmeny hodnôt v poliach
        // Tlačidlo „Zobraziť rozšírený filter“ - Prístup k rozšírenému filtru
        buttonFilterExtended().click();

        // Zmena hodnoty pola „Rozmer pneumatík“
        // Pole Rozmer - Vyplnená nami určenou vlastnou hodnotou
        filterDimension("1010100");
        // Pole Rozmer - Vyčistenie pola
        filterDimension("");
        // Pole Rozmer - Vyplnenie novou hodnotou
        filterDimension("2055516");
        // Porovnanie novej hodnoty
//        Assert.assertEquals("2055516", driver.findElement(By.xpath("div_id_dimension")).getText());

        // Zmena hodnoty pola „Rozmer pneumatík“
        // Pole Sezóna - Vyplnená hodnotou z výberu možností
        filterSeason("Letná");
        // Pole Sezóna - Vyplnenie novou hodnotou
        filterSeason("Celoročná");
        // Porovnanie aktuálnej novej hodnoty
        Assert.assertEquals("Celoročná", driver.findElement(By.
                xpath("//div[contains(@class, 'controls col-sm-6')]//div[@id='id_season_chosen']/a")).getText());
    }



    // Metóda pre tlačidlo zobrazenia Rozšíreného filtra
    private WebElement buttonFilterExtended(){
        return driver.findElement(By.className("submit-button"));
    }

    // Metóda pre tlačidlo vyhľadávania produktov
    private WebElement button(){
        return driver.findElement(By.cssSelector(".controls.col-sm-12.text-center"));
    }

    // Vstupná metóda pre Pole Rozmeru
    private void filterDimension(String expectedValue) {
        WebElement searchSizes = driver.findElement(By.id("id_dimension"));
        searchSizes.click();
        searchSizes.sendKeys(expectedValue);

        if (expectedValue == ""){
            searchSizes.clear();
        }
    }

    // Vstupná metóda pre Pole Sezóny
    private void filterSeason(String expectedValue) {
        driver.findElement(By.xpath("//div[contains(@class, 'controls col-sm-6')]//div[@id='id_season_chosen']/a")).click();
        WebElement searchSeason = driver.findElement(By.xpath("//li[text()='" + expectedValue + "']"));
        searchSeason.click();
    }

}

