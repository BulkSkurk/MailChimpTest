import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertNotEquals;

import java.time.Duration;
import java.util.Objects;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class MyStepdefs {

    private WebDriver driver;
    private String userLength = "";


    @Given("I have opened {string}")
    public void iHaveOpened(String browser)  {

        if (Objects.equals(browser, "edge")) {
            System.setProperty("webdriver.edge.driver", "C:/Selenium/msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (Objects.equals(browser, "chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.get("https://login.mailchimp.com/signup/");
    }


    @Given("I have written my {string}")
    public void iHaveWrittenMy(String email) {
        sendKeys(driver, By.id("email"), email);
    }

    @Given("my Random username is {string} long")
    public void myRandomUsernameIsLong(String length) {
        int lengthNumber = Integer.parseInt(length);
        Random r = new Random();

        if (lengthNumber == 0) {
            userLength = "AubergineLord_69420";
        } else {


            for (int i = 0; i < lengthNumber; i++) {

                char c = (char) (r.nextInt(26) + 'a');
                userLength = userLength + c;

            }
        }
        sendKeys(driver, By.id("new_username"), userLength);

    }

    @Given("I have written a {string}")
    public void iHaveWrittenA(String password) {
        sendKeys(driver, By.id("new_password"), password);
    }

    @When("I click the sign-up button")
    public void iClickTheSignUpButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("create-account")));
        button.click();

    }

    @Then("We {string} to create an account")
    public void weHaveCreatedAnAccount(String expectedResult) {

        String actual = driver.getTitle();
        String expected = "Success | Mailchimp";

        if(Objects.equals(expectedResult, "Success")){
            assertEquals(actual,expected);

        } else if(Objects.equals(expectedResult,"Fail")){
            assertNotEquals(actual,expected);
        }

        driver.quit();
    }

    private static void sendKeys(WebDriver driver, By by, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));


        element.sendKeys(text);


    }
}
