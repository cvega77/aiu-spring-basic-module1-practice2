package com.accenture.courses.spring.bank.services;

import com.accenture.courses.spring.bank.dao.IAccountDao;
import org.springframework.context.ApplicationContext;
import com.accenture.courses.spring.bank.domain.Account;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransferServiceTest extends TestCase {

    public void testTransfer100Dollars() {
        ApplicationContext ctx =
            new ClassPathXmlApplicationContext(new String[] {"spring-config.xml"});

        // retrieve the beans we'll use during testing
        IAccountDao accountRepository = ctx.getBean(IAccountDao.class);
        ITransferService transferService = ctx.getBean(ITransferService.class);

        // create accounts to test against
        accountRepository.add(new Account("A123", 1000.00));
        accountRepository.add(new Account("C456", 0.00));

        // check account balances before transfer
        assertEquals(accountRepository.findById("A123").getBalance(), 1000.00);
        assertEquals(accountRepository.findById("C456").getBalance(), 0.00);

        // perform transfer
        transferService.transfer(100.00, "A123", "C456");

        // check account balances after transfer
        assertEquals(accountRepository.findById("A123").getBalance(), 900.00);
        assertEquals(accountRepository.findById("C456").getBalance(), 100.00);
    }
}