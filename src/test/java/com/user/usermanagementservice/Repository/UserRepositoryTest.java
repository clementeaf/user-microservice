package com.user.usermanagementservice.Repository;

import com.user.usermanagementservice.Model.Usuarios;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    IUserRepository userRepository;

    @Test
    public void testFindByName() {
        // Crear un usuario para la prueba
        Usuarios user = new Usuarios();
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Verificar si se encuentra el mensaje "Ya existe" al buscar un usuario por nombre existente
        String message = userRepository.checkIfNameExists("John Doe");
        assertEquals("Ya existe", message);
    }


    @Test
    public void testExistsByName() {
        // Crear un usuario para la prueba
        Usuarios user = new Usuarios();
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // Verificar si el usuario existe por nombre en el repositorio
        boolean exists = userRepository.existsByName("Jane Doe");

        // Verificar si el usuario existe en el repositorio
        assertTrue(exists); // Verificar si el usuario existe por nombre
    }
}
