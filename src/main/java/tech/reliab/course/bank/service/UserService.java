package tech.reliab.course.bank.service;

import tech.reliab.course.bank.entity.User;

import java.util.List;

public interface UserService {
    User get(long userId);

    void insert(User user);

    void update(User user);

    void delete(long userId);

    void deleteAll();

    List<User> getAll();

    void outputUserInfo(Long id);
}