package tech.reliab.course.bank.service.impl;

import tech.reliab.course.bank.database.dao.UserDAO;
import tech.reliab.course.bank.entity.User;
import tech.reliab.course.bank.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAO();

    @Override
    public User get(long userId) {
        return userDAO.get(userId).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public void insert(User user) {
        userDAO.insert(user);
    }


    public void update(User user) {
        userDAO.update(user);
    }

    public void delete(long userId) {
        userDAO.delete(userId);
    }

    @Override
    public void outputUserInfo(Long id) {
        userDAO.outputUserInfo(id);
    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }
}
