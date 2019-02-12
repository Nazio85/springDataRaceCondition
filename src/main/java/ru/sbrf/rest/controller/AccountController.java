package ru.sbrf.rest.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.rest.conf.CustomLogger;
import ru.sbrf.rest.conf.LoggerAutowired;
import ru.sbrf.rest.exception.AccountNotFoundException;
import ru.sbrf.rest.model.Account;
import ru.sbrf.rest.model.RequestAmount;
import ru.sbrf.rest.service.AccountService;

import java.util.Objects;

/**
 * Rest controller позволяет делать операции над счетами
 */
@RestController
@RequestMapping("/")
public class AccountController {

    private final AccountService accountService;

    /**
     * Автоматическая инициализация логгера {@link ru.sbrf.rest.conf.InitBean}
     */
    @LoggerAutowired
    private CustomLogger logger;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> options(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST)
                .build();
    }

    @GetMapping
    public String init(){
        return "Objects.isNull(logger) - " +Objects.isNull(logger);
    }

    /**
     * Запрос счета
     *
     * @param accountId Номер счета
     * @return Итоговый счет
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountId){
        try {
            return ResponseEntity.ok(accountService.getAccount(accountId));
        } catch (NumberFormatException | AccountNotFoundException e){
            logger.debag(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Операции по снятию/пополнению
     *
     * @param request RequestAmount - устанавливает номер счета, сумму и тип операции
     * @return Итоговый счет
     */
    @PostMapping("/transaction")
    public ResponseEntity<Account> changeAmount(RequestAmount request){
        try {
            Account account = accountService
                    .addTransaction(request.getAccountId(), request.getAmount(), request.isIncreased());

            return ResponseEntity.ok(account);
        } catch (AccountNotFoundException e) {
            logger.debag(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
 }
