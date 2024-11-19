package tech.reliab.course.bank.database.dao;

import tech.reliab.course.bank.database.config.DataSource;
import tech.reliab.course.bank.entity.User;
import tech.reliab.course.bank.service.UserService;
import tech.reliab.course.bank.service.impl.UserServiceImpl;
import tech.reliab.course.bank.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User, Long> {
    public static User builderUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .fullName(rs.getString("full_name"))
                .dateOfBirth(rs.getDate("date_of_birth"))
                .workplace(rs.getString("workplace"))
                .monthlyIncome(rs.getInt("monthly_income"))
                .bankUsed(rs.getString("bank_used"))
                .creditAccountId(rs.getLong("credit_account_id"))
                .paymentAccountId(rs.getLong("payment_account_id"))
                .creditRating(rs.getInt("credit_rating"))
                .build();
    }

    @Override
    public User get(Long id) {
        User user = new User();

        String sql = "SELECT * FROM public.user where id=?";
        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new NullPointerException("Exception: rs is null!");
            }

            user = builderUser(rs);
            rs.close();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM public.user";
        List<User> users = new ArrayList<>();

        try (Connection conn = DataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                User user = builderUser(rs);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO public.user (full_name, date_of_birth, workplace, monthly_income," +
                " bank_used, credit_rating) VALUES (?,?,?,?,?,?)";

        try (Connection conn = DataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getFullName());
            statement.setDate(2, (Date) user.getDateOfBirth());
            statement.setString(3, user.getWorkplace());

            int monthlyIncome = Utils.randomFromAToB(0, 10000);
            statement.setInt(4, monthlyIncome);
            statement.setString(5, user.getBankUsed());

            int a, b;
            if (monthlyIncome <= 1000) {
                b = 100;
            } else {
                b = (int) Math.ceil((double) monthlyIncome / 1000) * 100;
            }
            a = b - 100;
            statement.setInt(6, Utils.randomFromAToB(a, b));
            statement.executeUpdate();

            // обновление кол-ва пользователей в банках
            String sql2 = "UPDATE bank SET number_users = bank.number_users + 1 WHERE bank.name=?";

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, user.getBankUsed());
            ps2.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String updateQuery = "UPDATE public.user SET" +
                " full_name=?, date_of_birth=?, workplace=?, monthly_income=?, bank_used=?" +
                ", credit_account_id=?, payment_account_id=?, credit_rating=? "
                + "WHERE public.user.id=?";

        try (Connection conn = DataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setString(1, user.getFullName());
            statement.setDate(2, (Date) user.getDateOfBirth());
            statement.setString(3, user.getWorkplace());
            statement.setInt(1, user.getMonthlyIncome());
            statement.setString(2, user.getBankUsed());
            statement.setLong(3, user.getCreditAccountId());
            statement.setLong(1, user.getPaymentAccountId());
            statement.setInt(2, user.getCreditRating());

            statement.setLong(4, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM public.user where public.user.id =?";

        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void outputUserInfo(Long id) {
        UserService service = new UserServiceImpl();
        User user = service.get(id);

        String sql_payment = "SELECT * FROM payment_account WHERE id = ?";
        String sql_credit = "SELECT * FROM credit_account WHERE id = ?";
        try {
            Connection conn = DataSource.getConnection();

            PreparedStatement ps_payment = conn.prepareStatement(sql_payment);
            ps_payment.setLong(1, user.getPaymentAccountId());
            ResultSet rs_payment = ps_payment.executeQuery();

            PreparedStatement ps_credit = conn.prepareStatement(sql_credit);
            ps_credit.setLong(1, user.getCreditAccountId());
            ResultSet rs_credit = ps_credit.executeQuery();

            System.out.println("User: " + user);

            if (rs_payment.next()) {
                System.out.println("PaymentAccount: " + PaymentAccountDAO.builderPaymentAccount(rs_payment));
            }

            if (rs_credit.next()) {
                System.out.println("CreditAccount: " + CreditAccountDAO.builderCreditAccount(rs_credit));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM public.user; " +
                "ALTER SEQUENCE user_id_seq RESTART WITH 1";

        try {
            Connection conn = DataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
