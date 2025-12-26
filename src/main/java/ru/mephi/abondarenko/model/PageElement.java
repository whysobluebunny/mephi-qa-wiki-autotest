package ru.mephi.abondarenko.model;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebElementCondition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;


@RequiredArgsConstructor
public class PageElement {
    @Getter
    private final String name;
    private final By locator;

    public PageElement modifyXpathLocator(String... values) {
        step(String.format("Получить элемент %s по параметрам %s", name, String.join(", ", values)));
        return new PageElement(name, By.xpath(
                String.format(locator.toString().replace("By.xpath: ", ""), values)
        ));
    }

    public PageElement click() {
        step(String.format("Нажать на элемент %s", name), () ->
                $(locator).shouldBe(Condition.enabled, Condition.exist).click());
        return this;
    }

    public PageElement should(WebElementCondition... conditions) {
        $(locator).should(conditions);
        return this;
    }

    public PageElement shouldNot(WebElementCondition... conditions) {
        $(locator).shouldNot(conditions);
        return this;
    }

    public boolean is(WebElementCondition condition) {
        return $(locator).is(condition);
    }

    public PageElement scrollTo() {
        step(String.format("Долистать до элемента %s", name), () ->
                $(locator).scrollTo());
        return this;
    }

    public PageElement find(PageElement subElement) {
        return new PageElement(name + ". " + subElement.getName(), new ByChained(locator, subElement.locator));
    }

    public PageElement setValue(String value) {
        step(String.format("Установить значение в поле %s", name), () ->
                $(locator).shouldBe(Condition.enabled, Condition.exist).setValue(value));
        return this;
    }

    public String getText() {
        return $(locator).getText();
    }

    public ElementsCollection getSelenideElementList() {
        return $$(locator);
    }
}
