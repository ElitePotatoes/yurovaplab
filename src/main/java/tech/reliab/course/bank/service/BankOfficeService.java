package tech.reliab.course.bank.service;

import tech.reliab.course.bank.entity.BankOffice;

import java.util.List;

public interface BankOfficeService {
    BankOffice get(long bankOfficeId);

    void insert(BankOffice bankOffice);

    void update(BankOffice bankOffice);

    void delete(long bankOfficeId);

    void deleteAll();

    List<BankOffice> getAll();
}