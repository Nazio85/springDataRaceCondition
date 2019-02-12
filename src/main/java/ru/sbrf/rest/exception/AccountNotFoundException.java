package ru.sbrf.rest.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String accountId) {
        super("Account with id = " + accountId + "not found");
    }
}
