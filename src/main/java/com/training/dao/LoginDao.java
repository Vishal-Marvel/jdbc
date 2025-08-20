package com.training.dao;

import com.training.entity.Login;
import java.util.Optional;

public interface LoginDao {
    Optional<Login> getLoginByUsername(String username);
    void insertLogin(Login login);

}
