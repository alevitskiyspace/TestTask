package com.space307.qa.platform.pages.accounts

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.SelenideElement
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.ADD_ACCOUNT_TITLE
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.ACCOUNT_ACTION
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.ACCOUNT_BALANCE
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.ACCOUNT_BONUS_AMOUNT
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.ACCOUNT_BONUS_TITLE
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.ACCOUNT_CURRENCY_ICON_FLAG
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.ACCOUNT_NAME
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.ACCOUNT_UPGRADE
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.BONUS_TITLE
import com.space307.qa.platform.pages.accounts.AccountsContainer.Companion.NAME_ACCOUNT_TITLE
import com.space307.qa.platform.pages.main_platform.MainPlatformStep
import com.space307.qa.web.utils.common.UtilsMethods.inputData
import com.space307.qa.web.utils.common.PatternRegex.*
import com.space307.qa.web.utils.hermes.Currency.Constants.BRL_CURRENCY
import com.space307.qa.web.utils.hermes.Currency.Constants.EGP_CURRENCY
import com.space307.qa.web.utils.hermes.Currency.Constants.EUR_CURRENCY
import com.space307.qa.web.utils.hermes.Currency.Constants.MXN_CURRENCY
import com.space307.qa.web.utils.hermes.Currency.Constants.THB_CURRENCY
import com.space307.qa.web.utils.hermes.Currency.Constants.USDT_CURRENCY
import com.space307.qa.web.utils.hermes.Currency.Constants.USD_CURRENCY
import com.space307.qa.web.utils.hermes.UserHelper
import io.qameta.allure.Step
import org.assertj.core.api.Assertions.assertThat

class AccountsStep : MainPlatformStep() {
    private val userHelper = UserHelper()
    private val nameRealCurrencyList: List<String> = ArrayList(
        listOf(
            USD_CURRENCY, EUR_CURRENCY, BRL_CURRENCY,
            THB_CURRENCY, MXN_CURRENCY, USDT_CURRENCY,
            EGP_CURRENCY
        )
    )

    //TODO: fix the function
    @Step("Check number of real accounts")
    fun checkCountRealAccount(countAccount: Int): AccountsStep {
        val curCountRealAccount = mainPlatformPage.accountsContainer.listRealAccount
            .shouldHave(sizeGreaterThan(0)
                .because("There are no real accounts on the list"))
            .size()
        assertThat(curCountRealAccount)
            .describedAs("The current number of real accounts is not equal to $countAccount")
            .isEqualTo(0)
        return this
    }

    @Step("Click on the button to add a new account")
    fun clickBtnAddAccount(): AccountsStep {
        mainPlatformPage.accountsContainer.btnAddAccount
            .shouldBe(visible.because("The button for adding a new account is not displayed"))
            .click()
        return this
    }

    @Step("Checking the form for selecting the currency of a new account")
    fun checkFormSelectCurrency(): AccountsStep {
        mainPlatformPage.accountsContainer.titleAccount
            .shouldBe(visible.because("Header is not displayed"))
            .shouldBe(
                text(ADD_ACCOUNT_TITLE)
                    .because("Title does not match $ADD_ACCOUNT_TITLE")
            )
        mainPlatformPage.accountsContainer.subtitleAccount
            .shouldBe(visible.because("The subheading is not displayed"))
        checkIslamicBanner(false)
        mainPlatformPage.accountsContainer.selectCurrencyAccount
            .shouldHave(sizeGreaterThan(0)
                .because("The list of currencies to be selected is empty"))
        mainPlatformPage.accountsContainer.btnNext
            .shouldBe(visible.because("The Next button is not displayed"))
        return this
    }

    @Step("Selecting the account currency")
    fun chooseCurrencyAccount(currency: String): AccountsStep {
        mainPlatformPage.accountsContainer.selectCurrencyAccount
            .filter(text(currency))
            .shouldHave(sizeGreaterThan(0)
                .because("Element with account currency $currency not found"))
            .first()
            .click()
        return this
    }

    @Step("Click Next")
    fun clickBtnNext(): AccountsStep {
        mainPlatformPage.accountsContainer.btnNext
            .shouldBe(visible.because("The Next button is not displayed"))
            .click()
        return this
    }

    @Step("Checking the form for entering the account name")
    fun checkFormNameAccount(): AccountsStep {
        mainPlatformPage.accountsContainer.titleAccount
            .shouldBe(visible.because("Header is not displayed"))
            .shouldBe(
                text(NAME_ACCOUNT_TITLE)
                    .because("The header doesn't match $NAME_ACCOUNT_TITLE")
            )
        mainPlatformPage.accountsContainer.subtitleAccountName
            .shouldBe(visible.because("The subheading is not displayed"))
        mainPlatformPage.accountsContainer.inputNameAccount
            .shouldBe(visible.because("The field for entering the account name is not displayed"))
        mainPlatformPage.accountsContainer.btnSettingName
            .shouldBe(visible.because("The 'Create Account' button is not displayed"))
        return this
    }

    //TODO: what happening here?
    @Step("Name entry {nameAccount}")
    fun inputNameAccount(nameAccount: String): AccountsStep {
        val inputField = mainPlatformPage.accountsContainer.inputNameAccount
            .shouldBe(visible.because("No field for entering account name"))
        inputData(inputField, nameAccount)
        val curNameAccount = inputField.value
        assertThat(curNameAccount)
            .describedAs("The account name in the input field is not equal to $nameAccount")
            .isEqualTo(nameAccount)
        return this
    }

    //TODO: fix the function
    @Step("Clicking the 'Create Account' button")
    fun clickBtnCreateAccount(): AccountsStep {
        mainPlatformPage.accountsContainer.btnSettingName
            .shouldBe(visible.because("The 'Create Account' button is not displayed"))
            .click()
    }

    @Step("Verification of real account information")
    fun checkRealAccountInfo(numberAccount: Int): SelenideElement {
        val accountInfo = mainPlatformPage.accountsContainer.listRealAccount
            .shouldHave(sizeGreaterThan(numberAccount)
                .because("No account number $numberAccount"))
        [numberAccount]
        accountInfo.find(ACCOUNT_NAME)
            .shouldBe(visible.because("Account name is not displayed"))
        val realBalance = accountInfo.find(ACCOUNT_BALANCE)
            .shouldBe(visible.because("Real balance with account currency is not displayed"))
            .text()
        val currency = getCurrency(realBalance)
        assertThat(nameRealCurrencyList)
            .describedAs("The received account currency is not in the list of available currencies")
            .contains(currency)
        accountInfo.find(ACCOUNT_BALANCE)
            .shouldBe(
                matchText(AMOUNT_REAL_ACCOUNT)
                    .because("Incorrect account balance format")
            )
        if (accountInfo.find(ACCOUNT_BONUS_AMOUNT).exists()) {
            accountInfo.find(ACCOUNT_BONUS_AMOUNT)
                .shouldBe(
                    matchText(DOUBLE_FORMAT_AMOUNT)
                        .because("Incorrect bonus format in the account")
                )
            accountInfo.find(ACCOUNT_BONUS_TITLE)
                .shouldBe(visible.because("No header for bonuses"))
                .shouldBe(
                    text(BONUS_TITLE)
                        .because("The header for the bonuses is not appropriate $BONUS_TITLE")
                )
            accountInfo.find(ACCOUNT_UPGRADE)
                .shouldBe(visible.because("Upgrade button is not displayed"))
        }
        val srcLink = accountInfo.find(ACCOUNT_CURRENCY_ICON_FLAG)
            .shouldBe(visible.because("Currency icon is not displayed"))
            .getAttribute("src")
        assertThat(srcLink)
            .describedAs("The image link should have the currency name in it $currency")
            .contains(currency)
        accountInfo.find(ACCOUNT_ACTION)
            .shouldBe(visible.because("The context menu button is not displayed"))
        return accountInfo
    }

    @Step("Checking the account name")
    fun checkNameAccount(account: SelenideElement, expectedName: String): AccountsStep {
        val curNameAccount = getNameAccount(account)
        assertThat(curNameAccount)
            .describedAs("Account name does not match the expected value $expectedName")
            .isEqualTo(expectedName)
        return this
    }

    @Step("Closing the account menu")
    fun closeMenuAccount(): AccountsStep {
        mainPlatformPage.accountsContainer.buttonClosePaymentsSidebar
            .shouldBe(visible.because("The button to close the account menu is not displayed"))
            .click()
        return this
    }
}