package ru.mephi.abondarenko.test.mobile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mephi.abondarenko.data.pages.mobile.OnboardingPage;
import ru.mephi.abondarenko.test.mobile.common.BaseWikiAndroidTest;
import ru.mephi.abondarenko.test.mobile.common.extension.LocalMobileTestExtension;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(LocalMobileTestExtension.class)
public class WikiTests extends BaseWikiAndroidTest {
    private final OnboardingPage onboardingPage = new OnboardingPage();

    @BeforeEach
    public void openApp() {
        open();
    }

    @Test
    public void testOnboardingSkip() {
        onboardingPage.assertOnboardingOpened()
                .skipOnboarding()
                .assertHomePageOpened();
    }

    @Test
    public void testOnboarding() {
        onboardingPage.assertOnboardingOpened()
                .continueOnboarding("New ways to explore")
                .continueOnboarding("Reading lists with sync")
                .continueOnboarding("Data & Privacy")
                .finishOnboarding()
                .assertHomePageOpened();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Cucumber", "Quality assurance"})
    public void testSearch(String subject) {
        onboardingPage.skipOnboarding()
                .search(subject)
                .selectSearchResult(0)
                .closePopup()
                .asserWebViewOpened();
    }
}
