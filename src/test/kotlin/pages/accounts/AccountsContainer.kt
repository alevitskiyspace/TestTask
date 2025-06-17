package com.space307.qa.platform.pages.accounts

import com.codeborne.selenide.Condition.matchText
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import com.space307.qa.web.utils.common.PatternRegex.NOT_EMPTY

class AccountsContainer {
    val btnAddAccount = element("[data-test='create-account-icon-widget']")
    val subtitleAccount = element("[data-test='account-create-select-currency-description']")
    val subtitleAccountName = element("[data-test='account-create-select-name-description']")
    val accountSwitcher = element("[data-test='account-switcher']")
    val selectCurrencyAccount = elements("[data-test='account-create-select-currency-radio']")
    val btnNext = element("[data-test='account-create-select-currency-button']")
    val titleAccount: SelenideElement
        get() = elements(
            "[data-test='cor-panel-content-title'],[data-test='cor-panel-l-header-title']"
        ).find(matchText(NOT_EMPTY))
    val inputNameAccount = element("[name='AccountName']")
    val btnSettingName = element("[data-test='account-create-select-name-submit-button']")
    val listRealAccount = elements("[data-test~='account-item-real']")
    val buttonClosePaymentsSidebar = element(".payments-sidebar [data-test='cor-w-panel-close']")

    companion object {
        const val ADD_ACCOUNT_TITLE = "Add Account"
        const val NAME_ACCOUNT_TITLE = "Account Name"
        const val ACCOUNT_NAME = "[data-test='account-item-title-name']"
        const val ACCOUNT_BALANCE = "[data-test='account-item-balance']"
        const val ACCOUNT_BONUS_AMOUNT = "[data-test='account-item-bonus-amount']"
        const val ACCOUNT_BONUS_TITLE = "[data-test='account-item-bonus-title']"
        const val ACCOUNT_ICON = "[data-test='account-item-icon']"
        const val ACCOUNT_CURRENCY_ICON_FLAG = "$ACCOUNT_ICON img"
        const val BONUS_TITLE = "Bonus"
        const val ACCOUNT_UPGRADE = "[data-test='account-item_upgrade-icon']"
        const val ACCOUNT_ACTION = "[data-test='account-item-action']"
    }
}