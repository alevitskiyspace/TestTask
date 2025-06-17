package com.space307.qa.platform.tests.accounts

import com.space307.qa.BaseTest
import com.space307.qa.platform.pages.accounts.AccountsStep
import com.space307.qa.platform.pages.header.HeaderStep
import com.space307.qa.web.utils.annotations.Smoke
import org.junit.jupiter.api.DisplayName
import io.qameta.allure.*
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.NEW_NAME_ACCOUNT_USD
import com.space307.qa.web.utils.hermes.Currency.*
import com.space307.qa.web.utils.hermes.Currency.Constants.USD_CURRENCY

@Epic("Platform")
@Feature("Accounts")
@DisplayName("Accounts")
class AccountsTest : BaseTest() {
    private val accountsStep = AccountsStep()
    private val headerStep = HeaderStep()

    @Smoke
    @DisplayName("Create new account with USD currency")
    @AllureId("42726")
    fun createNewAccount() {
        headerStep
            .clickBalanceButton()
        accountsStep
            .checkCountRealAccount(1)
            .clickBtnAddAccount()
            .checkFormSelectCurrency()
            .chooseCurrencyAccount(USD_CURRENCY)
            .clickBtnNext()
            .checkFormNameAccount()
            .inputNameAccount(NEW_NAME_ACCOUNT_USD)
            .clickBtnCreateAccount()
            .checkCountRealAccount(2)

        val account = accountsStep.checkRealAccountInfo(2)

        accountsStep
            .checkNameAccount(account, NEW_NAME_ACCOUNT_USD)
            .closeMenuAccount()
        headerStep
            .checkNameAccountOpened(NEW_NAME_ACCOUNT_USD)
            .checkRealAccOpened(USD.name)
    }
}