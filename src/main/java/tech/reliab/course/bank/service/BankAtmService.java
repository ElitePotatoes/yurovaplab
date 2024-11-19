package tech.reliab.course.bank.service;

import tech.reliab.course.bank.entity.BankAtm;

import java.util.List;
import java.util.Optional;

public interface BankAtmService {
    BankAtm get(long bankAtmId);

    void insert(BankAtm bankAtm);

    void update(BankAtm bankAtm);

    void delete(long bankAtmId);

    void deleteAll();

    List<BankAtm> getAll();
}
