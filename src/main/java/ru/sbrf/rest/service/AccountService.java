package ru.sbrf.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sbrf.rest.dao.AccountRepository;
import ru.sbrf.rest.exception.AccountNotFoundException;
import ru.sbrf.rest.model.Account;
import ru.sbrf.rest.model.Transactions;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    @PostConstruct
    private void init() {
        accountRepository.save(new Account());
    }


    /**
     * Получаем счет
     * @param accountId номер счета
     * @return новый счет
     * @throws AccountNotFoundException счет не найден
     */
    public Account getAccount(String accountId) throws AccountNotFoundException {
        Long id = Long.parseLong(accountId);
        return accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }


    /**
     * Метод добавляет транзакцию к счет
     * @param accountId номер счета
     * @param amount на какое значение изменяем счет
     * @param isIncreased тип операции
     * @return новый счет
     * @throws AccountNotFoundException счет не найден
     */
    @Transactional
    public Account addTransaction(String accountId, BigDecimal amount, boolean isIncreased) throws AccountNotFoundException {
        Account account = getAccount(accountId);

        Transactions transactions;
        if (isIncreased) {
            account.setBigDecimal(account.getBigDecimal().add(amount));
            transactions = new Transactions(account, amount, true);
        } else {
            account.setBigDecimal(account.getBigDecimal().subtract(amount));
            transactions = new Transactions(account, amount, false);
        }

        account.getTransactions().add(transactions);
        return accountRepository.save(account);
    }

}
