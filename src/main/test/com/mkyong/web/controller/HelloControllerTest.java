package com.mkyong.web.controller;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
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

    @Test
    public void userShouldSeeHisName(){
        System.setProperty("webdriver.chrome.driver", "f:\\spring_mvc\\spring3-mvc-maven-xml-hello-world-master\\chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
        open("http://localhost:8080/spring3/hello/Almas");
        $(By.id("my_name"))
                .$("p").shouldHave(text("Hello Almas"))
                .$("p").shouldHave("Learn");

    }
}