package com.user.usermanagementservice.Service;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    USER_NOT_FOUND("User not found with id: ");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
