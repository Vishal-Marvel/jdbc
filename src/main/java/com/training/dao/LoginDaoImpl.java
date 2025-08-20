package com.training.dao;

import com.training.entity.Login;
import com.training.exception.DatabaseException;
import com.training.util.DbUtil;
import com.training.util.QueryMapper;

import java.sql.*;
import java.util.Optional;

public class LoginDaoImpl implements LoginDao {
    @Override
    public Optional<Login> getLoginByUsername(String username) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.GET_LOGIN)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Login(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("userId")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error fetching login details", e);
        }
        return Optional.empty();
    }

    @Override
    public void insertLogin(Login login) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryMapper.CREATE_LOGIN)) {

            ps.setString(1, login.getUserName());
            ps.setString(2, login.getPassword());
            ps.setInt(3, login.getUserId());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new DatabaseException("Error creating login details", e);
        }
    }

}
