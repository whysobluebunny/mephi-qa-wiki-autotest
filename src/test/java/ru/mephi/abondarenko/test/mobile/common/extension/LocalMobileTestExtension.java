package ru.mephi.abondarenko.test.mobile.common.extension;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;

public class LocalMobileTestExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback {
    private static AppiumDriverLocalService service;

    @Override
    public void beforeAll(ExtensionContext context) {
        service = new AppiumServiceBuilder()
                .withArgument(RELAXED_SECURITY)
                .withIPAddress("127.0.0.1")
                .withTimeout(Duration.ofMinutes(5))
                .build();
        service.start();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        if (service != null) {
            service.stop();
            service = null;
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        closeWebDriver();
        Configuration.browserSize = null;
        Configuration.pageLoadTimeout = -1;
        WebDriverRunner.addListener(new WebDriverListener() {
            @Override
            public void beforeAnyCall(Object target, Method method, Object[] args) {
                WebDriverListener.super.beforeAnyCall(target, method, args);
            }
        });
    }
}
