package com.mkyong.web.controller.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

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
        System.setProperty("webdriver.chrome.driver", "..\\chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
        open("http://spring3-mvc-maven-xml-hello-world-master_tomcat_1:8080/myapp/hello/" + name);
    }

    @Then("^loaded page should print out \"([^\"]*)\"$")
    public void elementWithTagShouldExist(String greeting) {
        $(By.id("my_name"))
                .$("p").shouldHave(text(greeting))
                .$("p").shouldHave("Learn");
    }


}