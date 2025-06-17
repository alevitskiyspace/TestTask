package com.space307.qa.platform.tests.accounts;

import com.space307.qa.BaseTest;
import com.space307.qa.platform.pages.accounts.AccountsStep;
import com.space307.qa.platform.pages.header.HeaderStep;
import com.space307.qa.web.utils.annotations.Smoke;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.AllureId;
import static com.space307.qa.platform.pages.accounts.AccountsContainer.NEW_NAME_ACCOUNT_USD;
import com.space307.qa.web.utils.hermes.Currency;
import static com.space307.qa.web.utils.hermes.Currency.Constants.USD_CURRENCY;
import com.codeborne.selenide.SelenideElement;

@Epic("Platform")
@Feature("Accounts")
@DisplayName("Accounts")
public class AccountsTest extends BaseTest {
    private final AccountsStep accountsStep = new AccountsStep();
    private final HeaderStep headerStep = new HeaderStep();

    @Test
    @Smoke
    @DisplayName("Create new account with USD currency")
    @AllureId("42726")
    public void createNewAccount() {
        headerStep.clickBalanceButton();

        accountsStep
                .checkCountRealAccount(1)
                .clickBtnAddAccount()
                .checkFormSelectCurrency()
                .chooseCurrencyAccount(USD_CURRENCY)
                .clickBtnNext()
                .checkFormNameAccount()
                .inputNameAccount(NEW_NAME_ACCOUNT_USD)
                .clickBtnCreateAccount()
                .checkCountRealAccount(2);

        SelenideElement account = accountsStep.checkRealAccountInfo(2);

        accountsStep
                .checkNameAccount(account, NEW_NAME_ACCOUNT_USD)
                .closeMenuAccount();

        headerStep
                .checkNameAccountOpened(NEW_NAME_ACCOUNT_USD)
                .checkRealAccOpened(Currency.USD.name());
    }
}