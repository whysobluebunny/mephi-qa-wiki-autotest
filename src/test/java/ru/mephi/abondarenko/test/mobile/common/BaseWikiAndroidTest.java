package ru.mephi.abondarenko.test.mobile.common;


import com.codeborne.selenide.Configuration;
import ru.mephi.abondarenko.test.mobile.common.driver.WikiAppAndroidDriver;

public class BaseWikiAndroidTest {
    public BaseWikiAndroidTest() {
        Configuration.browser = WikiAppAndroidDriver.class.getName();
        Configuration.timeout = 20000;
    }
}
