package ru.mephi.abondarenko.data.pages.web;

import com.codeborne.selenide.Condition;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import ru.mephi.abondarenko.model.PageElement;

@Getter
public class WikiHomePage extends WikiCorePage {
    private final PageElement englishSelectLanguage = new PageElement("Выбор Языка. Английский",
            By.xpath("//*[@class='interlanguage-link-target' and contains(.,'English')]"));
    private final PageElement welcomeLabel = new PageElement("Приветствие на главной странице",
            By.id("mp-welcome"));
    private final PageElement wikipediaLogo = new PageElement("Логотип Википедия",
            By.xpath("//*[@class='mw-logo-icon']"));

    public WikiHomePage switchToEnglish() {
        englishSelectLanguage.scrollTo().click();
        return this;
    }

    public WikiHomePage assertHomePageEnglish() {
        Assertions.assertTrue(wikipediaLogo.should(Condition.appear).is(Condition.visible));
        Assertions.assertTrue(welcomeLabel.should(Condition.appear).is(Condition.visible));
        return this;
    }
}
