package ru.mephi.abondarenko.test.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mephi.abondarenko.data.pages.web.WikiHomePage;

@Tag("web")
public class WikiTests {
    private final WikiHomePage wikiHomePage = new WikiHomePage();

    @BeforeEach
    public void openWikiEnglishVersion() {
        Selenide.open("https://ru.wikipedia.org");
        wikiHomePage.switchToEnglish();
    }

    @Test
    public void testWikiHomePage() {
        wikiHomePage.assertHomePageEnglish();
    }

    @Test
    public void testNavigation() {
        wikiHomePage.navigateTo("Contents")
                .assertSubjectTitle("Contents")
                .navigateTo("Current events")
                .assertSubjectTitle("Current events");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Cucumber", "Quality assurance"})
    public void testSearch(String subject) {
        wikiHomePage.searchPage(subject)
                .assertSubjectTitle(subject);
    }

    @Test
    public void testRandomPage() {
        String title = wikiHomePage.navigateTo("Random article")
                .getPageTitle().should(Condition.appear).getText();
        String secondTitle = wikiHomePage.navigateTo("Random article")
                .getPageTitle().should(Condition.appear).getText();
        Assertions.assertNotEquals(title, secondTitle);
    }
}
