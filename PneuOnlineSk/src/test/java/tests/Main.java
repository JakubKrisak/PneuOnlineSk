package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

    public WebDriver driver;
    public final String BASE_URL = "https://www.pneuonline.sk/sk/domov/";

    @Before
    public void setUp() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));

        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @After
    public void tearDown(){
//        driver.close();
//        driver.quit();
    }

}
