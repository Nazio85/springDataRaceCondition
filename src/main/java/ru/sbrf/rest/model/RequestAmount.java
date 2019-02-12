package ru.sbrf.rest.model;

import java.math.BigDecimal;

public class RequestAmount {

    private String accountId;
    private BigDecimal amount;
    private boolean isIncreased;

    public RequestAmount(String accountId, BigDecimal amount, boolean isIncreased) {
        this.accountId = accountId;
        this.amount = amount;
        this.isIncreased = isIncreased;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isIncreased() {
        return isIncreased;
    }
}
