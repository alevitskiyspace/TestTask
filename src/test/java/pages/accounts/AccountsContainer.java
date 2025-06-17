package com.space307.qa.platform.pages.accounts;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.elements;
import static com.codeborne.selenide.Condition.matchText;
import static com.space307.qa.web.utils.common.PatternRegex.NOT_EMPTY;

public class AccountsContainer {
    public final SelenideElement btnAddAccount =
            element("[data-test='create-account-icon-widget']");

    public final SelenideElement subtitleAccount =
            element("[data-test='account-create-select-currency-description']");

    public final SelenideElement subtitleAccountName =
            element("[data-test='account-create-select-name-description']");

    public final SelenideElement accountSwitcher =
            element("[data-test='account-switcher']");

    public final ElementsCollection selectCurrencyAccount =
            elements("[data-test='account-create-select-currency-radio']");

    public final SelenideElement btnNext =
            element("[data-test='account-create-select-currency-button']");

    public SelenideElement getTitleAccount() {
        return elements(
                "[data-test='cor-panel-content-title'],[data-test='cor-panel-l-header-title']")
                .find(matchText(NOT_EMPTY));
    }

    public final SelenideElement inputNameAccount =
            element("[name='AccountName']");

    public final SelenideElement btnSettingName =
            element("[data-test='account-create-select-name-submit-button']");

    public final ElementsCollection listRealAccount =
            elements("[data-test~='account-item-real']");

    public final SelenideElement buttonClosePaymentsSidebar =
            element(".payments-sidebar [data-test='cor-w-panel-close']");

    public static final String ADD_ACCOUNT_TITLE = "Add Account";
    public static final String NAME_ACCOUNT_TITLE = "Account Name";
    public static final String ACCOUNT_NAME = "[data-test='account-item-title-name']";
    public static final String ACCOUNT_BALANCE = "[data-test='account-item-balance']";
    public static final String ACCOUNT_BONUS_AMOUNT = "[data-test='account-item-bonus-amount']";
    public static final String ACCOUNT_BONUS_TITLE = "[data-test='account-item-bonus-title']";
    public static final String ACCOUNT_ICON = "[data-test='account-item-icon']";
    public static final String ACCOUNT_CURRENCY_ICON_FLAG = ACCOUNT_ICON + " img";
    public static final String BONUS_TITLE = "Bonus";
    public static final String ACCOUNT_UPGRADE = "[data-test='account-item_upgrade-icon']";
    public static final String ACCOUNT_ACTION = "[data-test='account-item-action']";
}