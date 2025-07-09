package com.space307.qa.platform.pages.accounts;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.space307.qa.platform.pages.main_platform.MainPlatformStep;
import com.space307.qa.web.utils.hermes.UserHelper;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.space307.qa.platform.pages.accounts.AccountsContainer.*;
import static com.space307.qa.web.utils.common.UtilsMethods.inputData;
import static com.space307.qa.web.utils.common.PatternRegex.*;
import static com.space307.qa.web.utils.hermes.Currency.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountsStep extends MainPlatformStep {
    private final UserHelper userHelper = new UserHelper();
    private final List<String> nameRealCurrencyList = new ArrayList<>(Arrays.asList(
            USD_CURRENCY, EUR_CURRENCY, BRL_CURRENCY,
            THB_CURRENCY, MXN_CURRENCY, USDT_CURRENCY,
            EGP_CURRENCY
    ));

    //TODO: fix the function
    @Step("Check number of real accounts")
    public AccountsStep checkCountRealAccount(int countAccount) {
        int curCountRealAccount = mainPlatformPage.getAccountsContainer().getListRealAccount()
                .shouldHave(sizeGreaterThan(0)
                        .because("There are no real accounts on the list"))
                .size();
        assertThat(curCountRealAccount)
                .describedAs("The current number of real accounts is not equal to " + countAccount)
                .isEqualTo(0);
        return this;
    }

    @Step("Click on the button to add a new account")
    public AccountsStep clickBtnAddAccount() {
        mainPlatformPage.getAccountsContainer().getBtnAddAccount()
                .shouldBe(visible.because("The button for adding a new account is not displayed"))
                .click();
        return this;
    }

    @Step("Checking the form for selecting the currency of a new account")
    public AccountsStep checkFormSelectCurrency() {
        mainPlatformPage.getAccountsContainer().getTitleAccount()
                .shouldBe(visible.because("Header is not displayed"))
                .shouldBe(
                        text(ADD_ACCOUNT_TITLE)
                                .because("Title does not match " + ADD_ACCOUNT_TITLE)
                );
        mainPlatformPage.getAccountsContainer().getSubtitleAccount()
                .shouldBe(visible.because("The subheading is not displayed"));
        checkIslamicBanner(false);
        mainPlatformPage.getAccountsContainer().getSelectCurrencyAccount()
                .shouldHave(sizeGreaterThan(0)
                        .because("The list of currencies to be selected is empty"));
        mainPlatformPage.getAccountsContainer().getBtnNext()
                .shouldBe(visible.because("The Next button is not displayed"));
        return this;
    }

    @Step("Selecting the account currency")
    public AccountsStep chooseCurrencyAccount(String currency) {
        mainPlatformPage.getAccountsContainer().getSelectCurrencyAccount()
                .filter(text(currency))
                .shouldHave(sizeGreaterThan(0)
                        .because("Element with account currency " + currency + " not found"))
                .first()
                .click();
        return this;
    }

    @Step("Click Next")
    public AccountsStep clickBtnNext() {
        mainPlatformPage.getAccountsContainer().getBtnNext()
                .shouldBe(visible.because("The Next button is not displayed"))
                .click();
        return this;
    }

    @Step("Checking the form for entering the account name")
    public AccountsStep checkFormNameAccount() {
        mainPlatformPage.getAccountsContainer().getTitleAccount()
                .shouldBe(visible.because("Header is not displayed"))
                .shouldBe(
                        text(NAME_ACCOUNT_TITLE)
                                .because("The header doesn't match " + NAME_ACCOUNT_TITLE)
                );
        mainPlatformPage.getAccountsContainer().getSubtitleAccountName()
                .shouldBe(visible.because("The subheading is not displayed"));
        mainPlatformPage.getAccountsContainer().getInputNameAccount()
                .shouldBe(visible.because("The field for entering the account name is not displayed"));
        mainPlatformPage.getAccountsContainer().getBtnSettingName()
                .shouldBe(visible.because("The 'Create Account' button is not displayed"));
        return this;
    }

    //TODO: what is happening here?
    @Step("Name entry {nameAccount}")
    public AccountsStep inputNameAccount(String nameAccount) {
        SelenideElement inputField = mainPlatformPage.getAccountsContainer().getInputNameAccount()
                .shouldBe(visible.because("No field for entering account name"));
        inputData(inputField, nameAccount);
        String curNameAccount = inputField.getValue();
        assertThat(curNameAccount)
                .describedAs("The account name in the input field is not equal to " + nameAccount)
                .isEqualTo(nameAccount);
        return this;
    }

    //TODO: fix the function
    @Step("Clicking the 'Create Account' button")
    public AccountsStep clickBtnCreateAccount() {
        mainPlatformPage.getAccountsContainer().getBtnSettingName()
                .shouldBe(visible.because("The 'Create Account' button is not displayed"))
                .click();
        return this;
    }

    @Step("Verification of real account information")
    public SelenideElement checkRealAccountInfo(int numberAccount) {
        ElementsCollection accountInfo = mainPlatformPage.getAccountsContainer().getListRealAccount()
                .shouldHave(sizeGreaterThan(numberAccount)
                        .because("No account number " + numberAccount));
        accountInfo.find(ACCOUNT_NAME)
                .shouldBe(visible.because("Account name is not displayed"));
        String realBalance = accountInfo.find(ACCOUNT_BALANCE)
                .shouldBe(visible.because("Real balance with account currency is not displayed"))
                .text();
        String currency = getCurrency(realBalance);
        assertThat(nameRealCurrencyList)
                .describedAs("The received account currency is not in the list of available currencies")
                .contains(currency);
        accountInfo.find(ACCOUNT_BALANCE)
                .shouldBe(
                        matchText(AMOUNT_REAL_ACCOUNT)
                                .because("Incorrect account balance format")
                );
        if (accountInfo.find(ACCOUNT_BONUS_AMOUNT).exists()) {
            accountInfo.find(ACCOUNT_BONUS_AMOUNT)
                    .shouldBe(
                            matchText(DOUBLE_FORMAT_AMOUNT)
                                    .because("Incorrect bonus format in the account")
                    );
            accountInfo.find(ACCOUNT_BONUS_TITLE)
                    .shouldBe(visible.because("No header for bonuses"))
                    .shouldBe(
                            text(BONUS_TITLE)
                                    .because("The header for the bonuses is not appropriate " + BONUS_TITLE)
                    );
            accountInfo.find(ACCOUNT_UPGRADE)
                    .shouldBe(visible.because("Upgrade button is not displayed"));
        }
        String srcLink = accountInfo.find(ACCOUNT_CURRENCY_ICON_FLAG)
                .shouldBe(visible.because("Currency icon is not displayed"))
                .getAttribute("src");
        assertThat(srcLink)
                .describedAs("The image link should have the currency name in it " + currency)
                .contains(currency);
        accountInfo.find(ACCOUNT_ACTION)
                .shouldBe(visible.because("The context menu button is not displayed"));
        return accountInfo;
    }

    @Step("Checking the account name")
    public AccountsStep checkNameAccount(SelenideElement account, String expectedName) {
        String curNameAccount = getNameAccount(account);
        assertThat(curNameAccount)
                .describedAs("Account name does not match the expected value " + expectedName)
                .isEqualTo(expectedName);
        return this;
    }

    @Step("Closing the account menu")
    public AccountsStep closeMenuAccount() {
        mainPlatformPage.getAccountsContainer().getButtonClosePaymentsSidebar()
                .shouldBe(visible.because("The button to close the account menu is not displayed"))
                .click();
        return this;
    }
}