package tech.reliab.course.bank.service.impl;

import tech.reliab.course.bank.database.dao.PaymentAccountDAO;
import tech.reliab.course.bank.entity.PaymentAccount;
import tech.reliab.course.bank.service.PaymentAccountService;

import java.util.List;
import java.util.Optional;

public class PaymentAccountServiceImpl implements PaymentAccountService {
    private final PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

    @Override
    public PaymentAccount get(long paymentAccountId) {
        return paymentAccountDAO.get(paymentAccountId);
    }

    @Override
    public List<PaymentAccount> getAll() {
        return paymentAccountDAO.getAll();
    }

    @Override
    public void insert(PaymentAccount paymentAccount) {
        paymentAccountDAO.insert(paymentAccount);
    }

    public void update(PaymentAccount paymentAccount) {
        paymentAccountDAO.update(paymentAccount);
    }

    public void delete(long paymentAccountId) {
        paymentAccountDAO.delete(paymentAccountId);
    }

    @Override
    public void deleteAll() {
        paymentAccountDAO.deleteAll();
    }
}
