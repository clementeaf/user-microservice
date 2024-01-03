package com.user.usermanagementservice.Service;

import com.user.usermanagementservice.Exception.NoUsersFoundException;
import com.user.usermanagementservice.Model.Usuarios;
import com.user.usermanagementservice.Repository.IUserRepository;
import com.user.usermanagementservice.Service.impl.UserServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private LoggingService loggingService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers_WhenUsersExist() {
        Usuarios user1 = new Usuarios();
        Usuarios user2 = new Usuarios();
        List<Usuarios> userList = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(userList);

        List<Usuarios> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllUsers_WhenNoUsersExist() {
        when(userRepository.findAll()).thenReturn(List.of());

        assertThrows(NoUsersFoundException.class, () -> userService.getAllUsers());
    }
}
