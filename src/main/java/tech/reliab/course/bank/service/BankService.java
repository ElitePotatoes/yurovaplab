package tech.reliab.course.bank.service;

import tech.reliab.course.bank.entity.Bank;

import java.util.List;

public interface BankService {
    Bank get(long bankId);

    void insert(Bank bank);

    void update(Bank bank);

    void delete(long bankId);

    List<Bank> getAll();

    void deleteAll();

    void outputBankInfo(Long id);
}
