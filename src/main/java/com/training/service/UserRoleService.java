package com.training.service;

import com.training.exception.RoleNotFoundException;

public interface UserRoleService {
    void assignRole(int userId, String roleName ) throws RoleNotFoundException;
}
