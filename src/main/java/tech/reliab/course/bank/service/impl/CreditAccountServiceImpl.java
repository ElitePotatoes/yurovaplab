package tech.reliab.course.bank.service.impl;

import tech.reliab.course.bank.database.dao.CreditAccountDAO;
import tech.reliab.course.bank.entity.CreditAccount;
import tech.reliab.course.bank.service.CreditAccountService;

import java.util.List;
import java.util.Optional;

public class CreditAccountServiceImpl implements CreditAccountService {
    private final CreditAccountDAO creditAccountDAO = new CreditAccountDAO();

    @Override
    public CreditAccount get(long creditAccountId) {
        return creditAccountDAO.get(creditAccountId);
    }

    @Override
    public List<CreditAccount> getAll() {
        return creditAccountDAO.getAll();
    }

    @Override
    public void insert(CreditAccount creditAccount) {
        creditAccountDAO.insert(creditAccount);
    }

    public void update(CreditAccount creditAccount) {
        creditAccountDAO.update(creditAccount);
    }

    public void delete(long creditAccountId) {
        creditAccountDAO.delete(creditAccountId);
    }

    @Override
    public void deleteAll() {
        creditAccountDAO.deleteAll();
    }
}
