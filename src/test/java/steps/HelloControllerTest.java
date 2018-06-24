package steps;

import com.codeborne.selenide.WebDriverRunner;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

/**
 * Created by admin
 * on 23.06.2018.
 */
public class HelloControllerTest {

//    @Test
//    public void userShouldSeeHisName(){
//        System.setProperty("webdriver.chrome.driver", "f:\\spring_mvc\\spring3-mvc-maven-xml-hello-world-master\\chromedriver.exe");
//        System.setProperty("selenide.browser", "Chrome");
//        open("http://localhost:8080/spring3/hello/Almas");
//        $(By.id("my_name"))
//                .$("p").shouldHave(text("Hello Almas"))
//                .$("p").shouldHave("Learn");
//
//    }

    @Given("^open spring-mvc webapp hello rest service with name parameter \"([^\"]*)\"$")
    public void openSpringMVCWebapp(String name) {
//        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
//        System.setProperty("selenide.browser", "Chrome");
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        setWebDriver(driver);

        open("http://tomcat:8080/myapp/hello/" + name);
    }

    @Then("^loaded page should print out \"([^\"]*)\"$")
    public void elementWithTagShouldExist(String greeting) {
        $(By.id("my_name"))
                .$("p").shouldHave(text(greeting))
                .$("p").shouldHave("Learn");
    }


}