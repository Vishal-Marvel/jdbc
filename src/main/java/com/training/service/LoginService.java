package com.training.service;

import com.training.dto.UserRoleDto;
import com.training.entity.Login;
import com.training.exception.AuthenticationException;
import com.training.exception.UserNotFoundException;

public interface LoginService {
    UserRoleDto authenticate(String username, String password) throws AuthenticationException, UserNotFoundException;
    void createLogin (Login login);
}
