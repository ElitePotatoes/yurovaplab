package tech.reliab.course.bank.service.impl;

import tech.reliab.course.bank.database.dao.BankOfficeDAO;
import tech.reliab.course.bank.entity.BankOffice;
import tech.reliab.course.bank.service.BankOfficeService;

import java.util.List;

public class BankOfficeServiceImpl implements BankOfficeService {
    private final BankOfficeDAO bankOfficeDAO = new BankOfficeDAO();

    @Override
    public BankOffice get(long bankId) {
        return bankOfficeDAO.get(bankId).orElse(null);
    }

    @Override
    public List<BankOffice> getAll() {
        return bankOfficeDAO.getAll();
    }

    @Override
    public void insert(BankOffice bankOffice) {
        bankOfficeDAO.insert(bankOffice);
    }

    public void update(BankOffice bankOffice) {
        bankOfficeDAO.update(bankOffice);
    }

    public void delete(long bankOfficeId) {
        bankOfficeDAO.delete(bankOfficeId);
    }

    @Override
    public void deleteAll() {
        bankOfficeDAO.deleteAll();
    }
}
