package com.user.usermanagementservice.Service.impl;

public interface PasswordServiceImpl {
    String generateSalt();
    String hashPassword(String password, String salt);
    boolean verifyPassword(String rawPassword, String hashedPassword, String salt);
}
