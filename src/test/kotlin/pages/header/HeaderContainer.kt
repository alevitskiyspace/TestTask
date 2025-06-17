package com.space307.qa.platform.pages.header

import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement

class HeaderContainer {
    val buttonBalance = element("[data-test='balance']")
    val nameAccountTitle element("[data-test='account-balance-title']")
    val headerBalanceSum = element(ACCOUNT_BALANCE_VALUE)

    companion object {
        const val ACCOUNT_BALANCE_VALUE: String = "[data-test='account-balance-value']"
    }
}