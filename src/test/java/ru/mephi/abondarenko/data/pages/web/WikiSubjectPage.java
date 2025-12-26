package ru.mephi.abondarenko.data.pages.web;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import ru.mephi.abondarenko.model.PageElement;

@Getter
public class WikiSubjectPage extends WikiCorePage {
    private final PageElement pageTitle = new PageElement("Заголовок страницы",
            By.xpath("//*[@class='mw-page-title-main']"));

    public WikiSubjectPage assertSubjectTitle(String subject) {
        Assertions.assertEquals(subject, pageTitle.getText());
        return this;
    }
}
