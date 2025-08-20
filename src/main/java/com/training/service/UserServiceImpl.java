package com.training.service;

import com.training.dao.UserDao;
import com.training.dao.UserDaoImpl;
import com.training.dao.UserRoleDao;
import com.training.dao.UserRoleDaoImpl;
import com.training.dto.UserRoleDto;
import com.training.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    private final UserRoleDao userRoleDao = new UserRoleDaoImpl();

    @Override
    public void addUser(User user){

        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int userId){

        userDao.deleteUser(userId);
    }

    @Override
    public List<UserRoleDto> getAllUsers(){
        List<UserRoleDto> userRoleDtos = new ArrayList<>();
        List<User> users =  userDao.getAllUsers();
        for (User user: users){
            List<String> userRoles = userRoleDao.getRolesByUserId(user.getId());
            userRoleDtos.add(new UserRoleDto(user, userRoles));
        }
        return userRoleDtos;
    }
}
