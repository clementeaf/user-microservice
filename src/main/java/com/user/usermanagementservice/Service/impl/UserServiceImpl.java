package com.user.usermanagementservice.Service.impl;

import com.user.usermanagementservice.Exception.UserNotFoundException;
import com.user.usermanagementservice.Model.Usuarios;

import java.util.List;

public interface UserServiceImpl {
    List<Usuarios> getAllUsers();
    Usuarios getUserById(Long id) throws UserNotFoundException, UserNotFoundException;
    Usuarios createUser(Usuarios user);
    Usuarios updateUser(Long id, Usuarios updatedUser) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
}
