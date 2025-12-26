package ru.mephi.abondarenko.data.pages.mobile;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import ru.mephi.abondarenko.model.PageElement;

@Getter
public class OnboardingPage {
    private final PageElement skipButton = new PageElement("Кнопка пропуска Онбординга",
            AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button"));
    private final PageElement continueButton = new PageElement("Кнопка далее",
            AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button"));
    private final PageElement onboardingContainer = new PageElement("Страница Онбординг",
            AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_pager_container"));
    private final PageElement onboardingHeader = new PageElement("Заголовок страницы онбординга",
            AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"));
    private final PageElement finishOnboardingButton = new PageElement("Кнопка завершения онбординга",
            AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));

    public OnboardingPage assertOnboardingOpened() {
        Assertions.assertTrue(onboardingContainer.should(Condition.appear).is(Condition.visible));
        Assertions.assertTrue(skipButton.should(Condition.appear).is(Condition.visible));
        Assertions.assertTrue(continueButton.should(Condition.appear).is(Condition.visible));
        return this;
    }

    public HomePage skipOnboarding() {
        skipButton.click();
        return new HomePage();
    }

    public OnboardingPage continueOnboarding(String expected) {
        continueButton.click();
        Assertions.assertEquals(expected, onboardingHeader.should(Condition.appear).getText());
        return this;
    }

    public HomePage finishOnboarding() {
        finishOnboardingButton.click();
        return new HomePage();
    }
}
