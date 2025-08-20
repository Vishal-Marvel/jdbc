package com.training.dao;

import com.training.entity.Role;
import com.training.entity.User;
import com.training.exception.DatabaseException;
import com.training.util.DbUtil;
import com.training.util.QueryMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {

    @Override
    public Optional<Role> getRoleByRoleName(String name) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.GET_ROLE_BY_NAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    return Optional.of(new Role(rs.getInt("id"), rs.getString("name")));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete user", e);
        }
        return Optional.empty();
    }
}
