package tech.reliab.course.bank;

import tech.reliab.course.bank.entity.*;
import tech.reliab.course.bank.service.*;
import tech.reliab.course.bank.service.impl.*;

import java.sql.Date;

import static tech.reliab.course.bank.utils.Utils.randomFromAToB;

public class Main {
    public static void clearDB() {
        // Bank
        BankService service1 = new BankServiceImpl();
        service1.deleteAll();

        // BankAtm
        BankAtmService service2 = new BankAtmServiceImpl();
        service2.deleteAll();

        // BankOffice
        BankOfficeService service3 = new BankOfficeServiceImpl();
        service3.deleteAll();

        // CreditAccount
        CreditAccountService service4 = new CreditAccountServiceImpl();
        service4.deleteAll();

        // Employee
        EmployeeService service5 = new EmployeeServiceImpl();
        service5.deleteAll();

        //PaymentAccount
        PaymentAccountService service6 = new PaymentAccountServiceImpl();
        service6.deleteAll();

        // User
        UserService service7 = new UserServiceImpl();
        service7.deleteAll();
    }

    public static void createBank(String name) {
//        System.out.println("Bank init");
        Bank bank = Bank.builder()
                .name(name)
                .build();

        BankService service1 = new BankServiceImpl();
        service1.insert(bank);
    }

    public static void createBankOffice(String name, Long idBank) {
//        System.out.println("BankOffice init");
        BankOffice bankOffice = BankOffice.builder()
                .name(name)
                .address("Somewhere")
                .status(true)
                .freePlaceForAtm(true)
                .creditServices(false)
                .issuesMoney(true)
                .depositMoney(false)
                .rent(randomFromAToB(100000, 999999))
                .bankId(idBank)
                .build();

        BankOfficeService service2 = new BankOfficeServiceImpl();
        service2.insert(bankOffice);
    }

    public static void createEmployee(String name, Long idBank, Long idOffice) {
//        System.out.println("Employee init");
        Employee employee = Employee.builder()
                .fullName(name)
                .dateOfBirth(new Date(100, 7, 28))
                .post("Someone")
                .bankId(idBank)
                .officeWorkFormat(true)
                .bankOfficeId(idOffice)
                .creditServices(true)
                .salary(15000)
                .build();

        EmployeeService service5 = new EmployeeServiceImpl();
        service5.insert(employee);
    }

    public static void createBankAtm(String name, Long idBank, Long idOffice, Long idEmployee) {
//        System.out.println("BankAtm init");
        BankAtm bankAtm = BankAtm.builder()
                .name(name)
                .status("Работаю")
                .bankId(idBank)
                .bankOfficeId(idOffice)
                .employeeId(idEmployee)
                .issuesMoney(true)
                .depositMoney(false)
                .costMaintenance(3200)
                .build();

        BankAtmService service3 = new BankAtmServiceImpl();
        service3.insert(bankAtm);
    }

    public static void createUser(String name, String nameBank) {
//        String[] banksName = {"Sberbank"};
        User user = User.builder()
                .fullName(name)
                .dateOfBirth(new Date(103, 11, 10))
                .workplace("Somewhere")
//                .bankUsed(Arrays.toString(banksName))
                .bankUsed(nameBank)
                .build();

        UserService service7 = new UserServiceImpl();
        service7.insert(user);
    }

    public static void createPaymentAccount(Long idUser, String nameBank) {
        PaymentAccount paymentAccount = PaymentAccount.builder()
                .userId(idUser)
                .bankName(nameBank)
                .build();

        PaymentAccountService service6 = new PaymentAccountServiceImpl();
        service6.insert(paymentAccount);
    }

    public static void createCreditAccount(Long idUser, String nameBank) {
        CreditAccount creditAccount = CreditAccount.builder()
                .userId(idUser)
                .bankName(nameBank)
                .creditStartDate(new Date(124, 3, 20))
                .creditEndDate(new Date(124, 8, 20))
                .creditMonthlyDuration(5)
                .creditAmount(150000)
                .monthlyPayment(32000)
                .employeeId(Long.parseLong("11"))
                .paymentAccountId(Long.parseLong("16"))
                .build();

        CreditAccountService service4 = new CreditAccountServiceImpl();
        service4.insert(creditAccount);
    }

    public static void initializeAll() {
        for (long numBank = 1; numBank <= 5; numBank++) {
            String nameBank = "Bank" + numBank;
            createBank(nameBank);

            for (long numOffice = 1; numOffice <= 3; numOffice++) {
                createBankOffice("Office" + numOffice, numBank);

                for (long numEmployee = 1; numEmployee <= 5; numEmployee++) {
                    createEmployee("Employee" + numEmployee, numBank, numOffice);
                    createBankAtm("Atm" + numEmployee, numBank, numOffice, numEmployee);
                }

                for (long numUser = 1; numUser <= 5; numUser++) {
                    createUser("User" + numUser, nameBank);

                    for (long numAccount = 1; numAccount <= 2; numAccount++) {
                        createPaymentAccount(numUser, nameBank);
                        createCreditAccount(numUser, nameBank);
                    }
                }
            }
        }
    }

    public static void printAll() {
        // Bank
        BankService service1 = new BankServiceImpl();
        System.out.println(service1.getAll().toString());

        // BankAtm
        BankAtmService service2 = new BankAtmServiceImpl();
        System.out.println(service2.getAll().toString());

        // BankOffice
        BankOfficeService service3 = new BankOfficeServiceImpl();
        System.out.println(service3.getAll().toString());

        // CreditAccount
        CreditAccountService service4 = new CreditAccountServiceImpl();
        System.out.println(service4.getAll().toString());

        // Employee
        EmployeeService service5 = new EmployeeServiceImpl();
        System.out.println(service5.getAll().toString());

        //PaymentAccount
        PaymentAccountService service6 = new PaymentAccountServiceImpl();
        System.out.println(service6.getAll().toString());

        // User
        UserService service7 = new UserServiceImpl();
        System.out.println(service7.getAll().toString());

        System.out.println("\n\n\n");
        service1.outputBankInfo(1L);

        System.out.println("\n\n\n");
        service7.outputUserInfo(1L);

    }

    public static void main(String[] args) {
//        clearDB();
//        taskInitialized();
        printAll();


    }
}