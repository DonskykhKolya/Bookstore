package ua.donskykh.mybookstore.service;

import ua.donskykh.mybookstore.domain.User;

public interface UserService {

    boolean saveUser(User user);

    User findUserByEmail(String email);
}
