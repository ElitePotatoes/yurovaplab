package tech.reliab.course.bank.database.dao;

import tech.reliab.course.bank.database.config.DataSource;
import tech.reliab.course.bank.entity.BankOffice;
import tech.reliab.course.bank.entity.PaymentAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentAccountDAO implements DAO<PaymentAccount, Long> {
    public static PaymentAccount builderPaymentAccount(ResultSet rs) throws SQLException {
        return PaymentAccount.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .bankName(rs.getString("bank_name"))
                .currentAmount(rs.getInt("current_amount"))
                .build();
    }

    @Override
    public PaymentAccount get(Long id) {
        PaymentAccount payAcc = new PaymentAccount();

        String sql = "SELECT * FROM payment_account WHERE id=?";
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new NullPointerException("Exception: rs is null!");
            }

            payAcc = builderPaymentAccount(rs);
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return payAcc;
    }

    @Override
    public List<PaymentAccount> getAll() {
        String sql = "SELECT * FROM payment_account";
        List<PaymentAccount> payAccount = new ArrayList<>();
        try (Connection conn = DataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                PaymentAccount payAcc = builderPaymentAccount(rs);
                payAccount.add(payAcc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return payAccount;
    }

    @Override
    public void insert(PaymentAccount paymentAccount) {
        String sql = "INSERT INTO payment_account (user_id, bank_name, current_amount) " +
                "VALUES (?,?,?)";

        try (Connection conn = DataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            String bankName = paymentAccount.getBankName();

            statement.setLong(1, paymentAccount.getUserId());
            statement.setString(2, bankName);
            statement.setInt(3, 0);
            statement.executeUpdate();

            String sql2 = "SELECT id FROM payment_account WHERE user_id=?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setLong(1, paymentAccount.getUserId());
            ResultSet rs = ps2.executeQuery();

            if (rs.next()) {
                String sql3 = "UPDATE public.user SET payment_account_id=? WHERE id=?";

                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ps3.setLong(1, rs.getLong("id"));
                ps3.setLong(2, paymentAccount.getUserId());
                ps3.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(PaymentAccount paymentAccount) {
        String updateQuery = "UPDATE payment_account " +
                "SET user_id=?, bank_name=?, current_amount=? " +
                "WHERE payment_account.id=?";

        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setLong(1, paymentAccount.getUserId());
            statement.setString(2, paymentAccount.getBankName());
            statement.setInt(3, paymentAccount.getCurrentAmount());

            statement.setLong(4, paymentAccount.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM payment_account " +
                "WHERE payment_account.id =?";

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
        String sql = "DELETE FROM payment_account; " +
                "ALTER SEQUENCE payment_account_id_seq RESTART WITH 1";

        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
