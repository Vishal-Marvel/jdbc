package com.training.dto;

import com.training.entity.User;

import java.util.List;

public class UserRoleDto {
    private User user;
    private List<String> currentRoles;

    public UserRoleDto(User user, List<String> currentRoles) {
        this.user = user;
        this.currentRoles = currentRoles;
    }

    public UserRoleDto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCurrentRoles() {
        return currentRoles;
    }

    public void setCurrentRoles(List<String> currentRoles) {
        this.currentRoles = currentRoles;
    }

    @Override
    public String toString() {
        return "UserRoleDto{" +
                "user=" + user +
                ", currentRoles=" + currentRoles +
                '}';
    }
}
