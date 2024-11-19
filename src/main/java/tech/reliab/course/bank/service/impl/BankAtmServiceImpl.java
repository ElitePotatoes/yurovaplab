package tech.reliab.course.bank.service.impl;

import tech.reliab.course.bank.database.dao.BankAtmDAO;
import tech.reliab.course.bank.entity.BankAtm;
import tech.reliab.course.bank.service.BankAtmService;

import java.util.List;
import java.util.Optional;

public class BankAtmServiceImpl implements BankAtmService {
    private final BankAtmDAO bankAtmDAO = new BankAtmDAO();

    @Override
    public BankAtm get(long bankId) {
        return bankAtmDAO.get(bankId);
    }

    @Override
    public List<BankAtm> getAll() {
        return bankAtmDAO.getAll();
    }

    @Override
    public void insert(BankAtm bankAtm) {
        bankAtmDAO.insert(bankAtm);
    }

    public void update(BankAtm bankAtm) {
        bankAtmDAO.update(bankAtm);
    }

    public void delete(long bankAtmId) {
        bankAtmDAO.delete(bankAtmId);
    }

    @Override
    public void deleteAll() {
        bankAtmDAO.deleteAll();
    }
}
