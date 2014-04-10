package com.accenture.courses.spring.bank.dao.inmemory;

import com.accenture.courses.spring.bank.dao.IAccountDao;
import com.accenture.courses.spring.bank.domain.Account;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryAccountDao implements IAccountDao{

    private final Map<String, Account> accountsById = new HashMap<String, Account>();

    public Account findById(String acctId) {
        return Account.copy(accountsById.get(acctId));
    }

    public void update(Account account) {
        if (!accountsById.containsKey(account.getId())) {
            throw new IllegalArgumentException("account [" + account.getId() + "] not found");
        }
        accountsById.put(account.getId(), Account.copy(account));
    }

    public void add(Account account) {
        if (accountsById.containsKey(account.getId())) {
            throw new IllegalArgumentException("account [" + account.getId() + "] already exists");
        }

        accountsById.put(account.getId(), Account.copy(account));
    }

    public Set<Account> findAll() {
        HashSet<Account> allAccounts = new HashSet<Account>();
        for (Account account : accountsById.values()) {
            allAccounts.add(Account.copy(account));
        }
        return allAccounts;
    }
}
