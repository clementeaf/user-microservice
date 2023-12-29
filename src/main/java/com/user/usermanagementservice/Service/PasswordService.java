package com.user.usermanagementservice.Service;

import com.user.usermanagementservice.Service.impl.PasswordServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordService implements PasswordServiceImpl {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String generateSalt() {
        return passwordEncoder.encode("unique_salt_for_each_user");
    }

    public String hashPassword(String password, String salt) {
        return passwordEncoder.encode(password + salt);
    }

    public boolean verifyPassword(String rawPassword, String hashedPassword, String salt) {
        String hashedAttempt = hashPassword(rawPassword, salt);
        return hashedAttempt.equals(hashedPassword);
    }
}
