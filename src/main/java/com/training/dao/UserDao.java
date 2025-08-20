package com.training.dao;

import com.training.dto.UserRoleDto;
import com.training.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
}
