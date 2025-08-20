package com.training.dao;

import com.training.dto.UserRoleDto;
import com.training.entity.Role;
import com.training.exception.DatabaseException;
import com.training.util.DbUtil;
import com.training.util.QueryMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    @Override
    public List<String> getRolesByUserId(int userId) {
        List<String> roles = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.GET_USER_ROLES)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roles.add(rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching user roles", e);
        }
        return roles;
    }

    @Override
    public void assignRole(int userId, int roleId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.ASSIGN_ROLE)) {

            ps.setInt(1, userId);
            ps.setInt(2, roleId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error assigning role to userId: " + userId, e);
        }
    }

}
