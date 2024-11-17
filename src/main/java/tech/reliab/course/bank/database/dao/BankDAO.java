package tech.reliab.course.bank.database.dao;

import tech.reliab.course.bank.database.config.DataSource;
import tech.reliab.course.bank.entity.Bank;
import tech.reliab.course.bank.service.BankService;
import tech.reliab.course.bank.service.impl.BankServiceImpl;
import tech.reliab.course.bank.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankDAO implements DAO<Bank, Long> {
    public static Bank builderBank(ResultSet rs) throws SQLException {
        return Bank.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .numberOffices(rs.getInt("number_offices"))
                .numberAtms(rs.getInt("number_atms"))
                .numberEmployees(rs.getInt("number_employees"))
                .numberUsers(rs.getInt("number_users"))
                .bankRating(rs.getInt("bank_rating"))
                .totalMoney(rs.getInt("total_money"))
                .interestRate(rs.getFloat("interest_rate"))
                .build();
    }

    @Override
    public Optional<Bank> get(Long id) {
        String sql = "SELECT * FROM bank where id=?";
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Bank bank = builderBank(rs);
                rs.close();
                statement.close();

                return Optional.ofNullable(bank);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Bank> getAll() {
        String sql = "SELECT * FROM bank";
        List<Bank> banks = new ArrayList<>();
        try (Connection conn = DataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Bank bank = builderBank(rs);
                banks.add(bank);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return banks;
    }

    @Override
    public void insert(Bank bank) {
        String sql = "INSERT INTO bank (name, number_offices, number_atms, number_employees," +
                "number_users, bank_rating, total_money, interest_rate) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = DataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, bank.getName());
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            statement.setInt(4, 0);
            statement.setInt(5, 0);

            int bankRating = Utils.randomFromAToB(0, 100);
            statement.setInt(6, bankRating);
            statement.setInt(7, Utils.randomFromAToB(1, 1000000));

            float interestRate =  Utils.randomFromAToB(0, 20);
            if (bankRating > 80) {
                interestRate *= 0.5F;
            } else if (bankRating > 60) {
                interestRate *= 0.7F;
            } else if (bankRating > 40) {
                interestRate *= 0.9F;
            }

            statement.setFloat(8, interestRate);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Bank bank) {
        String updateQuery = "UPDATE bank SET name=?, number_offices=?, number_atms=?, " +
                "number_employees=?, number_users=?, bank_rating=?, total_money=?, interest_rate=? " +
                "WHERE bank.id=?";

        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setString(1, bank.getName());
            statement.setInt(2, bank.getNumberOffices());
            statement.setInt(3, bank.getNumberAtms());
            statement.setInt(4, bank.getNumberEmployees());
            statement.setInt(5, bank.getNumberUsers());
            statement.setInt(6, bank.getBankRating());
            statement.setInt(7, bank.getTotalMoney());
            statement.setFloat(8, bank.getInterestRate());
            statement.setLong(9, bank.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM bank where bank.id =?";
        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void outputAllBankInfo(Long id) {
        BankService service = new BankServiceImpl();
        Bank bank = service.get(id);

        String sql_offices = "SELECT * FROM bank_office WHERE bank_id = ?";
        String sql_atms = "SELECT * FROM bank_atm WHERE bank_id = ?";
        String sql_employees = "SELECT * FROM employee WHERE bank_id = ?";
        String sql_users = "SELECT * FROM public.user WHERE bank_used = ?";
        try {
            Connection conn = DataSource.getConnection();

            PreparedStatement ps_offices = conn.prepareStatement(sql_offices);
            ps_offices.setLong(1, id);
            PreparedStatement ps_atms = conn.prepareStatement(sql_atms);
            ps_atms.setLong(1, id);
            PreparedStatement ps_employees = conn.prepareStatement(sql_employees);
            ps_employees.setLong(1, id);
            PreparedStatement ps_users = conn.prepareStatement(sql_users);
            ps_users.setString(1, bank.getName());

            ResultSet rs_offices = ps_offices.executeQuery();
            ResultSet rs_atms = ps_atms.executeQuery();
            ResultSet rs_employees = ps_employees.executeQuery();
            ResultSet rs_users = ps_users.executeQuery();

            System.out.println("Bank: " + bank);

            System.out.println("Offices:");
            while (rs_offices.next()) {
                System.out.println("\t" + BankOfficeDAO.builderBankOffice(rs_offices));
            }

            System.out.println("BankAtms:");
            while (rs_atms.next()) {
                System.out.println("\t" + BankAtmDAO.builderBankAtm(rs_atms));
            }

            System.out.println("Employees:");
            while (rs_employees.next()) {
                System.out.println("\t" + EmployeeDAO.builderEmployee(rs_employees));
            }

            System.out.println("Users:");
            while (rs_users.next()) {
                System.out.println("\t" + UserDAO.builderUser(rs_users));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM bank; " +
                "ALTER SEQUENCE bank_id_seq RESTART WITH 1";

        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
