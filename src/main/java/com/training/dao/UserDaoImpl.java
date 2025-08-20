package com.training.dao;

import com.training.dto.UserRoleDto;
import com.training.entity.User;
import com.training.util.DbUtil;
import com.training.util.QueryMapper;
import com.training.exception.DatabaseException;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {

    @Override
    public void addUser(User user) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to insert user", e);
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.UPDATE_USER)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update user", e);
        }
    }

    @Override
    public void deleteUser(int userId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.DELETE_USER)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.GET_ALL_USERS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName")));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch users", e);
        }
        return list;
    }

    @Override
    public Optional<User> getUserById(int id) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.GET_USER_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    return Optional.of(new User(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName")));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch user", e);
        }
        return Optional.empty();
    }
}
