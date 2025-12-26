package ru.mephi.abondarenko.data.pages.web;

import lombok.Getter;
import org.openqa.selenium.By;
import ru.mephi.abondarenko.model.PageElement;

@Getter
public abstract class WikiCorePage {
    protected final PageElement menuDropDown = new PageElement("Навигация",
            By.id("vector-main-menu-dropdown"));
    protected final PageElement menuDropDownSubElementByText = new PageElement("Раздел навигации",
            By.xpath(".//*[text()='%s']"));
    protected final PageElement searchInput = new PageElement("Поле ввода Поиска",
            By.xpath("//*[@id='searchform']//input"));
    protected final PageElement searchButton = new PageElement("Кнопка Поиска",
            By.xpath("//*[@id='searchform']//button"));

    public WikiSubjectPage navigateTo(String target) {
        menuDropDown.click()
                .find(menuDropDownSubElementByText.modifyXpathLocator(target))
                .click();
        return new WikiSubjectPage();
    }

    public WikiSubjectPage searchPage(String subject) {
        searchInput.setValue(subject);
        searchButton.click();
        return new WikiSubjectPage();
    }
}
