package ru.mephi.abondarenko.data.pages.mobile;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import ru.mephi.abondarenko.model.PageElement;

public class HomePage {
    private final PageElement mainToolbar = new PageElement("Главный тулбар",
            AppiumBy.id("org.wikipedia.alpha:id/main_toolbar"));
    private final PageElement navigationToolbar = new PageElement("Навигация",
            AppiumBy.id("org.wikipedia.alpha:id/main_nav_tab_layout"));
    private final PageElement searchButton = new PageElement("Кнопка поиска",
            AppiumBy.id("org.wikipedia.alpha:id/search_container"));
    private final PageElement searchInputField = new PageElement("Поле ввода поиска",
            AppiumBy.id("org.wikipedia.alpha:id/search_src_text"));
    private final PageElement searchItemHeader = new PageElement("Заголовок результата поиска",
            AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));

    public HomePage assertHomePageOpened() {
        Assertions.assertTrue(mainToolbar.should(Condition.appear).is(Condition.visible));
        Assertions.assertTrue(navigationToolbar.should(Condition.appear).is(Condition.visible));
        Assertions.assertTrue(searchButton.should(Condition.appear).is(Condition.visible));
        return this;
    }

    public HomePage search(String subject) {
        searchButton.click();
        searchInputField.setValue(subject);
        return this;
    }

    public SubjectPage selectSearchResult(int index) {
        searchItemHeader.getSelenideElementList().get(index).click();
        return new SubjectPage();
    }
}
