package com.training.service;

import com.training.dto.UserRoleDto;
import com.training.entity.User;
import com.training.exception.UnauthorizedException;
import java.util.List;

public interface UserService {
    void addUser(User user) throws UnauthorizedException;
    void updateUser(User user);
    void deleteUser(int userId) throws UnauthorizedException;
    List<UserRoleDto> getAllUsers() throws UnauthorizedException;
}
