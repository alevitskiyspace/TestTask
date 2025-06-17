package com.space307.qa.platform.pages.header;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.element;

public class HeaderContainer {
    public final SelenideElement buttonBalance = element("[data-test='balance']");
    public final SelenideElement nameAccountTitle = element("[data-test='account-balance-title']");
    public final SelenideElement headerBalanceSum = element(ACCOUNT_BALANCE_VALUE);

    public static final String ACCOUNT_BALANCE_VALUE = "[data-test='account-balance-value']";
}