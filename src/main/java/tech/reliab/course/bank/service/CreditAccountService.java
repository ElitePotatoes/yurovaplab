package tech.reliab.course.bank.service;

import tech.reliab.course.bank.entity.CreditAccount;

import java.util.List;

public interface CreditAccountService {
    CreditAccount get(long creditAccountId);

    void insert(CreditAccount creditAccount);

    void update(CreditAccount creditAccount);

    void delete(long creditAccountId);

    void deleteAll();

    List<CreditAccount> getAll();
}
