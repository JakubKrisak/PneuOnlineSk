package tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasicFormTest extends Main {

        // Vytiahnutie údaju o počte pneumatík "Zobraziť X produktov" - to be parsed
        public String productsCount() {
                return driver.findElement(By.cssSelector(".btn.btn.btn-default")).getText();
        }

        @Test
        public void BasicDefaultFillInputsBasicTest(){
                // Overenie výsledku s prednastavenou hodnotou polí
                // Overenie default hodnoty v poliach Šírky, Výšky, Priemeru, Výrobcu, Sezóny
                Assert.assertFalse(driver.findElement(By.xpath("//div[@class='form-basic']//a/span")).getText().isEmpty());
                // Overenie default hodnoty v poli Výrobcovia
                Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"id_makers_chosen\"]/ul/li/input")).getText().isEmpty());
                // Tlačidlo „Zobraziť pneumatiky“ - Prístup k výsledku vyhľadávania
                button().click();
        }

        @Test
        public void BasicSuccessFillInputsTest(){
                // Overenie vstupných hodnôt na základe výberu z ponuky
                // Pole Šírky pneumatiky
                filterWidth("265");
                // Pole Šírky - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("265", actualValueWidth());
                // Pole Výšky pmeumatiky
                filterHeight("35");
                // Pole Výšky - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("35", actualValueHeight());
                // Pole Priemeru pneumatiky
                filterAverage("18");
                // Pole Priemeru - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("18", actualValueAverage());
                // Pole Výrobcu pneumatiky
                // Segmenty
                filterProducer("Stredný");
                // Pole Výrobcu - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("Stredný", actualValueProduct());
                // Výrobcovia
                filterProducer("AVON");
                // Pole Výrobcu - Porovnanie výberu z možností a hodnotou v poli
                /*
                2s wait na nacitanie elementu
                TODO: Spraviť porovnanie
                */
//                Assert.assertEquals("MICHELIN", actualValueProduct());
                // Pole Sezóny
                filterSeason("Letná");
                Assert.assertEquals("Letná", actualValueSeason());
                // Tlačidlo „Zobraziť pneumatiky“ - Prístup k výsledku vyhľadávania
                button().click();
        }

        @Test
        @Ignore
        public void BasicCombinationSuccessFillInputsTest(){
                // Overenie výsledku na základe kombinácie výberu z ponuky s prednastavenou hodnotou polí
                // Pole Šírky pneumatiky
                filterWidth("225");
                // Pole Šírky - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("225", actualValueWidth());
                // Pole Výšky pmeumatiky
                filterHeight("45");
                // Pole Výšky - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("45", actualValueHeight());
                // Pole Priemeu pneumatiky
                filterAverage("17");
                // Pole Priemeru - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("17", actualValueAverage());
                // Pole Výrobcu pneumatiky
                filterProducer("MICHELIN");
                // Pole Výrobcu - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("MICHELIN", actualValueProduct());
                // Pole Sezóny
                filterSeason("Letná");
                // Pole Sezóny - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("Letná", actualValueSeason());
                // Tlačidlo „Zobraziť pneumatiky“ - Prístup k výsledku vyhľadávania
                button().click();

                // Čakanie na tlačidlo „Zobraziť X pruduktov“ v okne „FILTER“
                new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector(".btn.btn.btn-default")));

                // Porovnanie hodnôt počtu produktov/pneumatík
                // Očakávaná hodnota X nemusí stále sedieť, kedže DATA neustále pracujú, Druhý atribút je sparsovaný počet produktov
                Assert.assertEquals(527, Integer.parseInt(productsCount().replaceAll("\\D", "")));
                System.out.println("Počet produktov sa mení, keďže DATA neustále pracujú!!!");
        }

        @Test
        public void BasicSuccessBadFillInputsTest(){
                // Overenie výsledku v poliach so zlou hodnotou
                // Pole Šírky pneumatiky
                filterWidth("111");
                // Pravdivosť, že vstupná hodnota je neplatná
                Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"id_width_chosen\"]/div/ul/li[contains(text(), \"Neboli nájdené žiadne položky.\")]"))
                        .getAttribute("class").contains("no-result"));
                driver.findElement(By.xpath("//*[@id=\"slider\"]")).click();
                // Pole Výšky pmeumatiky
                filterHeight("1000");
                // Pravdivosť, že vstupná hodnota je neplatná
                Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"id_width_chosen\"]/div/ul/li[contains(text(), \"Neboli nájdené žiadne položky.\")]"))
                        .getAttribute("class").contains("no-result"));
                driver.findElement(By.xpath("//*[@id=\"slider\"]")).click();
                // Pole Priemeru pneumatiky
                filterAverage("999");
                // Pravdivosť, že vstupná hodnota je neplatná
                Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"id_width_chosen\"]/div/ul/li[contains(text(), \"Neboli nájdené žiadne položky.\")]"))
                        .getAttribute("class").contains("no-result"));
                driver.findElement(By.xpath("//*[@id=\"slider\"]")).click();
                // Pole Výrobcu pneumatiky
                filterProducer("MICHELIN");
                // Pravdivosť, že vstupná hodnota je neplatná
                Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"id_width_chosen\"]/div/ul/li[contains(text(), \"Neboli nájdené žiadne položky.\")]"))
                        .getAttribute("class").contains("no-result"));
                driver.findElement(By.xpath("//*[@id=\"slider\"]")).click();
                // Pole Sezóny nemá možnosť iného výberu
                filterSeason("Celoročná");
                // Pole Sezóny - Porovnanie výberu z možností a hodnotou v poli
                Assert.assertEquals("Celoročná", actualValueSeason());
        }


        // Helpers
        private String actualValueWidth(){
                return driver.findElement(By.xpath("//div[@id='id_width_chosen']//span")).getText();
        }

        private String actualValueHeight(){
                return driver.findElement(By.xpath("//div[@id='id_aspect_ratio_chosen']//span")).getText();
        }

        private String actualValueAverage(){
                return driver.findElement(By.xpath("//div[@id='id_diameter_chosen']//span")).getText();
        }

        private String actualValueProduct(){
                return driver.findElement(By.xpath("//div[@id='id_makers_chosen']//span")).getText();
        }

        private String actualValueSeason(){
                return driver.findElement(By.xpath("//div[@id='id_season_chosen']//span")).getText();
        }

        // Metóda pre tlačidlo zobrazenia Rozšíreného filtra
        private WebElement button(){
                return driver.findElement(By.xpath("//div/button[@value='basic']"));
        }

        // Vstupná metóda pre Pole Šírky
        private void filterWidth(String expectedValue) {
                driver.findElement(By.id("div_id_width")).click();
                WebElement searchWidth = driver.findElement(By.xpath("//*[@id='id_width_chosen']/div/div/input"));
                searchWidth.sendKeys(expectedValue);
                searchWidth.sendKeys(Keys.ENTER);
        }

        // Vstupná metóda pre Pole Výšky
        private void filterHeight(String expectedValue) {
                driver.findElement(By.id("div_id_aspect_ratio")).click();
                WebElement searchWidth = driver.findElement(By.xpath("//*[@id=\"id_aspect_ratio_chosen\"]/div/div/input"));
                searchWidth.sendKeys(expectedValue);
                searchWidth.sendKeys(Keys.ENTER);
        }

        // Vstupná metóda pre Pole Priemer
        private void filterAverage(String expectedValue) {
                driver.findElement(By.id("div_id_diameter")).click();
                WebElement searchWidth = driver.findElement(By.xpath("//*[@id=\"id_diameter_chosen\"]/div/div/input"));
                searchWidth.sendKeys(expectedValue);
                searchWidth.sendKeys(Keys.ENTER);
        }

        // Vstupná metóda pre Pole Výrobcu
        private void filterProducer(String expectedValue) {
                driver.findElement(By.id("div_id_makers")).click();
                WebElement searchWidth = driver.findElement(By.xpath("//*[@id=\"id_makers_chosen\"]/ul/li/input"));
                searchWidth.sendKeys(expectedValue);
                searchWidth.sendKeys(Keys.ENTER);
        }

        // Vstupná metóda pre Pole Sezóny
        private void filterSeason(String expectedValue) {
                driver.findElement(By.id("div_id_season")).click();
                driver.findElement(By.xpath("//li[text()='" + expectedValue + "']")).click();
        }

    }

