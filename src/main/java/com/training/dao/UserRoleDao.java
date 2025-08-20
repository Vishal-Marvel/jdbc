package com.training.dao;

import com.training.dto.UserRoleDto;

import java.util.List;


public interface UserRoleDao {
    List<String> getRolesByUserId(int userId);
    void assignRole(int userId, int roleId);
}
