package com.training.service;

import com.training.dao.RoleDao;
import com.training.dao.RoleDaoImpl;
import com.training.dao.UserRoleDao;
import com.training.dao.UserRoleDaoImpl;
import com.training.entity.Role;
import com.training.exception.RoleNotFoundException;

import java.util.Optional;

public class UserRoleServiceImpl implements UserRoleService{
    private final RoleDao roleDao = new RoleDaoImpl();
    private final UserRoleDao userRoleDao = new UserRoleDaoImpl();
    @Override
    public void assignRole(int userId, String roleName) throws RoleNotFoundException {
        Optional<Role> optionalRole = roleDao.getRoleByRoleName(roleName);
        if (optionalRole.isEmpty()){
            throw new RoleNotFoundException("Role with role name" + roleName + "Not Found");
        }
        else{
            userRoleDao.assignRole(userId, optionalRole.get().getId());
        }
        System.out.println("Role Assigned");
    }
}
