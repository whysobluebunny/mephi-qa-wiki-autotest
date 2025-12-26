package ru.mephi.abondarenko.test.mobile.common.driver;


import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class WikiAppAndroidDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        File app = new File(Objects.requireNonNull(
                        WikiAppAndroidDriver.class.getClassLoader().getResource("mobile/android/apk/wiki.apk"))
                .getFile());
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setApp(app.getAbsolutePath())
                .setAppPackage("org.wikipedia.alpha")
                .setAppActivity("org.wikipedia.main.MainActivity")
                .setNativeWebScreenshot(true).autoGrantPermissions().enforceAppInstall()
                .fullReset();
        options.merge(capabilities);

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
