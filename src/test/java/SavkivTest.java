import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class SavkivTest {

    // TC_1_1  - Тест кейс:
    // 1. Открыть страницу https://openweathermap.org/
    // 2. Набрать в строке поиска город Paris
    // 3. Нажать пункт меню Search
    // 4. Из выпадающего списка выбрать Paris, FR
    // 5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver_\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(4000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        Thread.sleep(4000);
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type = 'submit']")
        );
        //   //button[@type = 'submit']  == //div[@id='weather-widget']//button[@type = 'submit']
        searchButton.click();

        Thread.sleep(5000);
        WebElement parisFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//li/span[text() = 'Paris, FR ']")
        );
        parisFRChoiceInDropdownMenu.click();


        WebElement h2CityNameHeader = driver.findElement(
                By.xpath("//div[@id=\"weather-widget\"]//h2")
        );

        Thread.sleep(5000);
        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


    //TC_11_01
    //1.  Открыть базовую ссылку
    //2.  Нажать на пункт меню Guide
    //3.  Подтвердить, что вы перешли на страницу со ссылкой
    // https://openweathermap.org/guide и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testGuideMenue_WhenOpenGuideMenu() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver_\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResult = "https://openweathermap.org/guide";

        driver.get(url);
        Thread.sleep(5000);

        WebElement clickGuideButton = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//li/a[text() = 'Guide']")
        );
        clickGuideButton.click();

        driver.get("https://openweathermap.org/guide");

        Thread.sleep(5000);
        WebElement headerTitle = driver.findElement(
                By.xpath("//html/head/title")
        );
        String actualResult = driver.getTitle();
        String actualResult1 = driver.getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult1);
        Assert.assertEquals(actualResult1, expectedResult);

        driver.quit();
    }

//            TC_11_02
//            1.  Открыть базовую ссылку
//            2.  Нажать на единицы измерения Imperial: °F, mph
//            3.  Подтвердить, что температура для города показана в Фарингейтах

    @Test
    public void testFahrenheitTemperature_AfterChosingImperial() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver_\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String cityName = "London";
        String url = "https://openweathermap.org/";
        String expectedResult = "°F";

        driver.get(url);

        Thread.sleep(5000);
        WebElement typeOfTemperature = driver.findElement(
                By.xpath("//div[@class='switch-container']//div[3]")
        );

        typeOfTemperature.click();

        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );
        Thread.sleep(4000);
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id='weather-widget']//button[@type = 'submit']")
        );
        //   //button[@type = 'submit']  == //div[@id='weather-widget']//button[@type = 'submit']
        searchButton.click();

        Thread.sleep(5000);
        WebElement londonFRChoiceInDropdownMenu = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']//li/span[text() = 'London, GB ']")
        );
        londonFRChoiceInDropdownMenu.click();


        WebElement londonCityHeading = driver.findElement(
                By.xpath("//div[@class='grid-container grid-4-5']//span[@class='heading']")
        );

        //"//ul[@class='weather-items text-container orange-side standard-padding']//li[4]"
        String tempInF = londonCityHeading.getText();

        Thread.sleep(4000);
        String actualResult = tempInF.substring((tempInF.length()-2));


        Assert.assertEquals(actualResult, expectedResult);


        driver.quit();
    }


//      TC_11_03
//      1. Открыть базовую ссылку
//      2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
//         We also use non-essential cookies to help us improve our services. A
//         ny data collected is anonymised. You can allow all cookies or manage them individually.”
//      3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testCookiesMessage_FooterPage() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver_\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResultAllow = "Allow all";
        String expectedResultManageCookies = "Manage cookies";

        driver.get(url);

        WebElement verifyCookiesMessage = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__container']/p")
        );

        String actualResult = verifyCookiesMessage.getText();

        Assert.assertEquals(actualResult, expectedResult);

        WebElement verifyAllowAllButton = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__btn-container']//button[text()='Allow all']")
        );

        String actualResultAllow = verifyAllowAllButton.getText();

        Assert.assertEquals(actualResultAllow, expectedResultAllow);

        WebElement verifyManageCookiesButton = driver.findElement(
                By.xpath("//div[@class='stick-footer-panel__btn-container']//a[text()=' Manage cookies ']")
        );

        String actualResultManageCookies = verifyManageCookiesButton.getText();

        Assert.assertEquals(actualResultManageCookies, expectedResultManageCookies);

        driver.quit();
    }

//    TC_11_04
//    1.  Открыть базовую ссылку
//    2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testSuuportMenu_HeaderPage() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver_\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        String expectedResultFAQ = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskQuestion = "Ask a question";


        driver.get(url);
        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement verifySupportCategory = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        verifySupportCategory.click();

        Thread.sleep(6000);

        WebElement verifyFAQbutton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//li[1]")
        );

        verifyFAQbutton.getText();

        String actualResultFAQ = verifyFAQbutton.getText();
        Assert.assertEquals(actualResultFAQ, expectedResultFAQ);

        WebElement verifyHowToStartbutton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//li[2]")
        );

        verifyHowToStartbutton.getText();
        String actualResultHowToStart = verifyHowToStartbutton.getText();
        Assert.assertEquals(actualResultHowToStart, expectedResultHowToStart);

        WebElement verifyAskQuestionButton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//li[3]")
        );

        verifyAskQuestionButton.getText();
        String actualResultAskQuestion = verifyAskQuestionButton.getText();
        Assert.assertEquals(actualResultAskQuestion, expectedResultAskQuestion);

        driver.quit();

    }

//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testCAPTCHA_SuportCategory() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver_\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "tester@test.com";
        String message = "Java for Begginer";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement verifySupportCategory = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        verifySupportCategory.click();

        Thread.sleep(4000);

        WebElement verifyAskQuestionButton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//li[3]")
        );

        verifyAskQuestionButton.click();

        Thread.sleep(5000);

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        Thread.sleep(5000);


        WebElement emailField1 = driver.findElement(By.id("question_form_email"));
        emailField1.click();
        emailField1.sendKeys(email);

        Thread.sleep(4000);

        WebElement subjectField = driver.findElement(By.xpath("//select[@id='question_form_subject']"));
        subjectField.click();
        driver.findElement(By.xpath("//select[@id='question_form_subject']/option[text()='Other']"));

        WebElement messageField = driver.findElement(By.xpath("//div/textarea[@id='question_form_message']"));
        messageField.sendKeys(message);

        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='has-error']/div"));
        String actualResult = errorMessage.getText();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }


//    TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//            4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”

    @Test
    public void testCaptchaSubmit_SuportCategory() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Documents\\chromedriver_\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String message = "Java for Begginer";
        String expectedResult = "can't be blank";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement verifySupportCategory = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
        verifySupportCategory.click();

        Thread.sleep(4000);

        WebElement verifyAskQuestionButton = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//li[3]")
        );

        verifyAskQuestionButton.click();

        Thread.sleep(5000);

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        Thread.sleep(5000);
        WebElement subjectField = driver.findElement(By.xpath("//select[@id='question_form_subject']"));
        subjectField.click();
        driver.findElement(By.xpath("//select[@id='question_form_subject']/option[text()='Other']")).click();

        Thread.sleep(5000);

        WebElement messageField = driver.findElement(By.xpath("//div/textarea[@id='question_form_message']"));
        messageField.sendKeys(message);

        String window2 = driver.getWindowHandle();
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement enterCaptcha =driver.findElement(By.xpath("" +
                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked " + "rc-anchor-checkbox']"));
        enterCaptcha.click();
        Thread.sleep(5000);
        driver.switchTo().window(window2);

        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit']"));
        submitButton.click();

        WebElement errorMessage = driver.findElement(By.xpath("//span[@class='help-block']"));

        String actualResult = errorMessage.getText();
        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();


    }
    }










