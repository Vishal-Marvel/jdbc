package com.training.dao;

import com.training.entity.Role;

import java.util.Optional;

public interface RoleDao {
    Optional<Role> getRoleByRoleName(String name);
}
