package tech.reliab.course.bank.service;

import tech.reliab.course.bank.entity.PaymentAccount;

import java.util.List;
import java.util.Optional;

public interface PaymentAccountService {
    PaymentAccount get(long paymentAccountId);

    void insert(PaymentAccount paymentAccount);

    void update(PaymentAccount paymentAccount);

    void delete(long paymentAccountId);

    void deleteAll();

    List<PaymentAccount> getAll();
}