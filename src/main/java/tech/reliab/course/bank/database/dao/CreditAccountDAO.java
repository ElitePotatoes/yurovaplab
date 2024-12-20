package tech.reliab.course.bank.database.dao;

import tech.reliab.course.bank.database.config.DataSource;
import tech.reliab.course.bank.entity.BankOffice;
import tech.reliab.course.bank.entity.CreditAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditAccountDAO implements DAO<CreditAccount, Long> {
    public static CreditAccount builderCreditAccount(ResultSet rs) throws SQLException {
        return CreditAccount.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .bankName(rs.getString("bank_name"))
                .creditStartDate(rs.getDate("credit_start_date"))
                .creditEndDate(rs.getDate("credit_end_date"))
                .creditMonthlyDuration(rs.getInt("credit_monthly_duration"))
                .creditAmount(rs.getInt("credit_amount"))
                .monthlyPayment(rs.getInt("monthly_payment"))
                .interestRate(rs.getFloat("interest_rate"))
                .employeeId(rs.getLong("employee_id"))
                .paymentAccountId(rs.getLong("payment_account_id"))
                .build();
    }

    @Override
    public CreditAccount get(Long id) {
        CreditAccount credAcc = new CreditAccount();

        String sql = "SELECT * FROM credit_account where id=?";
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new NullPointerException("Exception: rs is null!");
            }

            credAcc = builderCreditAccount(rs);
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return credAcc;
    }

    @Override
    public List<CreditAccount> getAll() {
        String sql = "SELECT * FROM credit_account";
        List<CreditAccount> credAccs = new ArrayList<>();
        try (Connection conn = DataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                CreditAccount credAcc = builderCreditAccount(rs);
                credAccs.add(credAcc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return credAccs;
    }

    @Override
    public void insert(CreditAccount creditAccount) {
        String sql = "INSERT INTO credit_account (user_id, bank_name, credit_start_date, credit_end_date," +
                " credit_monthly_duration, credit_amount, monthly_payment, interest_rate, employee_id, payment_account_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setLong(1, creditAccount.getUserId());
            statement.setString(2, creditAccount.getBankName());
            statement.setDate(3, (Date) creditAccount.getCreditStartDate());
            statement.setDate(4, (Date) creditAccount.getCreditEndDate());
            statement.setInt(5, creditAccount.getCreditMonthlyDuration());
            statement.setInt(6, creditAccount.getCreditAmount());
            statement.setInt(7, creditAccount.getMonthlyPayment());

            // получение процентной ставки в банке
            String sql2 = "SELECT interest_rate FROM bank WHERE bank.name=?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, creditAccount.getBankName());
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                statement.setFloat(8, rs2.getFloat("interest_rate"));
            }

            statement.setLong(9, creditAccount.getEmployeeId());
            statement.setLong(10, creditAccount.getPaymentAccountId());
            statement.executeUpdate();

            String sql3 = "SELECT id FROM credit_account WHERE user_id=?";

            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setLong(1, creditAccount.getUserId());
            ResultSet rs = ps3.executeQuery();
            if (rs.next()) {
                String sql4 = "UPDATE public.user SET credit_account_id=? WHERE id=?";

                PreparedStatement ps4 = conn.prepareStatement(sql4);
                ps4.setLong(1, rs.getLong("id"));
                ps4.setLong(2, creditAccount.getUserId());
                ps4.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(CreditAccount creditAccount) {
        String updateQuery = "UPDATE credit_account SET" +
                " user_id=?, bank_name=?, credit_start_date=?, credit_end_date=?, credit_monthly_duration=?," +
                " credit_amount=?, monthly_payment=?, interest_rate=?, employee_id=?, payment_account_id=? " +
                "WHERE credit_account.id=?";

        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setLong(1, creditAccount.getUserId());
            statement.setString(2, creditAccount.getBankName());
            statement.setDate(3, (Date) creditAccount.getCreditStartDate());
            statement.setDate(4, (Date) creditAccount.getCreditEndDate());
            statement.setInt(5, creditAccount.getCreditMonthlyDuration());
            statement.setInt(6, creditAccount.getCreditAmount());
            statement.setInt(7, creditAccount.getMonthlyPayment());
            statement.setFloat(8, creditAccount.getInterestRate());
            statement.setLong(9, creditAccount.getEmployeeId());
            statement.setLong(10, creditAccount.getPaymentAccountId());
            statement.setLong(11, creditAccount.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM credit_account WHERE credit_account.id =?";
        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM credit_account; " +
                "ALTER SEQUENCE credit_account_id_seq RESTART WITH 1";

        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
