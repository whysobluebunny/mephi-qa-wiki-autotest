package ru.mephi.abondarenko.data.pages.mobile;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import ru.mephi.abondarenko.model.PageElement;

public class SubjectPage {
    private final PageElement popupDialog = new PageElement("Попап",
            AppiumBy.id("org.wikipedia.alpha:id/dialogContainer"));
    private final PageElement closeButton = new PageElement("Кнопка закрытия",
            AppiumBy.id("org.wikipedia.alpha:id/closeButton"));
    private final PageElement webView = new PageElement("Web view страницы",
            AppiumBy.id("org.wikipedia.alpha:id/page_web_view"));

    public SubjectPage closePopup() {
        Assertions.assertTrue(popupDialog.should(Condition.appear).is(Condition.visible));
        closeButton.click();
        return this;
    }

    public SubjectPage asserWebViewOpened() {
        Assertions.assertTrue(webView.is(Condition.visible));
        return this;
    }
}
