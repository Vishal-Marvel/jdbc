package com.training.util;

public class QueryMapper {
    public static final String CREATE_USER = "INSERT INTO User(firstName, lastName) VALUES (?, ?)";
    public static final String UPDATE_USER = "UPDATE User SET firstName=?, lastName=? WHERE id=?";
    public static final String DELETE_USER = "DELETE FROM User WHERE id=?";
    public static final String GET_ALL_USERS = "SELECT * FROM User";
    public static final String GET_USER_BY_ID = "SELECT * FROM User WHERE id=?";


    public static final String GET_ROLE_BY_NAME = "SELECT * FROM Role WHERE name=?";

    public static final String CREATE_LOGIN = "INSERT INTO Login(username, password, userId) VALUES (?, ?, ?)";
    public static final String GET_LOGIN = "SELECT * FROM Login WHERE username=?";

    public static final String ASSIGN_ROLE = "INSERT INTO User_role(user_id, role_id) VALUES (?, ?)";
    public static final String GET_USER_ROLES = "SELECT r.name FROM Role r JOIN User_role ur ON r.id=ur.role_id WHERE ur.user_id=?";
}
