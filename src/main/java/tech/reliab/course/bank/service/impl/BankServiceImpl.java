package tech.reliab.course.bank.service.impl;

import tech.reliab.course.bank.database.dao.BankDAO;
import tech.reliab.course.bank.entity.Bank;
import tech.reliab.course.bank.service.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {
    private final BankDAO bankDAO = new BankDAO();

    @Override
    public Bank get(long bankId) {
        return bankDAO.get(bankId).orElse(null);
    }

    @Override
    public List<Bank> getAll() {
        return bankDAO.getAll();
    }

    @Override
    public void insert(Bank bank) {
        bankDAO.insert(bank);
    }

    public void update(Bank bank) {
        bankDAO.update(bank);
    }

    public void delete(long bankId) {
        bankDAO.delete(bankId);
    }

    @Override
    public void outputBankInfo(Long id) {
        bankDAO.outputAllBankInfo(id);
    }

    @Override
    public void deleteAll() {
        bankDAO.deleteAll();
    }
}
