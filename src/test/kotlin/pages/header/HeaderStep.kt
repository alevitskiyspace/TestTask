package com.space307.qa.platform.pages.header

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Condition.text
import com.space307.qa.platform.pages.main_platform.MainPlatformStep
import io.qameta.allure.Step

class HeaderStep : MainPlatformStep() {

    //TODO: why it is bad written step?
    @Step("Click on balance button")
    fun clickBalanceButton(): HeaderStep {
        mainPlatformPage.headerContainer.buttonBalance
            .click()
        return this
    }

    @Step("Check that real account has been opened by the name {nameAccount}")
    fun checkNameAccountOpened(nameAccount: String): HeaderStep {
        mainPlatformPage
            .headerContainer
            .nameAccountTitle
            .shouldHave(text(nameAccount))
        return this
    }

    @Step("Check that real account has been opened in {currency}")
    fun checkRealAccOpened(currency: String): HeaderStep {
        mainPlatformPage
            .headerContainer
            .headerBalanceSum
            .shouldHave(text(currency))
        return this
    }
}