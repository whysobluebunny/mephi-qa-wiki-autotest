package ru.mephi.abondarenko.test.web.common;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class BaseWebTest {
    @BeforeEach
    public void setUp() {
        Configuration.timeout = 20000;
        Configuration.browserSize = "1920x1080";
    }
}
