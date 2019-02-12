package ru.sbrf.rest.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;
import ru.sbrf.rest.model.Account;
import ru.sbrf.rest.model.RequestAmount;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Check logger
     */
    @Test
    public void loggerTest() {
        Field field = ReflectionUtils.findField(accountController.getClass(), "logger");
        Assert.assertNotNull(field);
        ReflectionUtils.makeAccessible(field);
        Object logger = ReflectionUtils.getField(field, accountController);
        Assert.assertNotNull(logger);
    }

    /**
     *
     */
    @Test
    public void getAccount() {
        Account account = accountController.getAccount("1").getBody();
        Assert.assertNotNull(account);
    }

    @Test
    public void changeAmount() {
        RequestAmount transaction1 =
                new RequestAmount("1", BigDecimal.valueOf(700), true);
        RequestAmount transaction2 =
                new RequestAmount("1", BigDecimal.valueOf(300), true);
        RequestAmount transaction3 =
                new RequestAmount("1", BigDecimal.valueOf(500), false);

        accountController.changeAmount(transaction1);
        accountController.changeAmount(transaction2);
        accountController.changeAmount(transaction3);

        Account account = accountController.getAccount("1").getBody();

        Assert.assertNotNull(account);
        Assert.assertEquals(500, account.getBigDecimal().intValue());
        Assert.assertEquals(3, account.getTransactions().size());
    }
}