package com.training.service;

import com.training.dao.*;
import com.training.dto.UserRoleDto;
import com.training.entity.Login;
import com.training.entity.User;
import com.training.exception.AuthenticationException;
import com.training.exception.UserNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    private final LoginDao loginDao = new LoginDaoImpl();
    private final UserRoleDao userRoleDao = new UserRoleDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public UserRoleDto authenticate(String username, String password) throws AuthenticationException, UserNotFoundException {
        UserRoleDto userRoleDto = new UserRoleDto();
        Login login = loginDao.getLoginByUsername(username).orElseThrow(() -> new AuthenticationException(("Username not found")));

        if (!Objects.equals(login.getPassword(), password)) {
            throw new AuthenticationException("Password is Incorrect");
        }

        userRoleDto.setUser(userDao.getUserById(login.getUserId()).orElseThrow(() -> new UserNotFoundException("User Not Found")));
        userRoleDto.setCurrentRoles(userRoleDao.getRolesByUserId(login.getId()));


        return userRoleDto;


    }

    @Override
    public void createLogin(Login login) {
        loginDao.insertLogin(login);
    }
}
