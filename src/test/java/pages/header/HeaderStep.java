package com.space307.qa.platform.pages.header;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.space307.qa.platform.pages.main_platform.MainPlatformStep;
import io.qameta.allure.Step;

public class HeaderStep extends MainPlatformStep {

    //TODO: why it is bad written test?
    @Step("Click on balance button")
    public HeaderStep clickBalanceButton() {
        mainPlatformPage.getHeaderContainer().getButtonBalance()
                .click();
        return this;
    }

    @Step("Check that real account has been opened by the name {nameAccount}")
    public HeaderStep checkNameAccountOpened(String nameAccount) {
        SelenideElement title = mainPlatformPage.getHeaderContainer().getNameAccountTitle();
        title.shouldHave(Condition.text(nameAccount));
        return this;
    }

    @Step("Check that real account has been opened in {currency}")
    public HeaderStep checkRealAccOpened(String currency) {
        SelenideElement balance = mainPlatformPage.getHeaderContainer().getHeaderBalanceSum();
        balance.shouldHave(Condition.text(currency));
        return this;
    }
}