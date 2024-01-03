package com.user.usermanagementservice.Model;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class UsuariosTest {

    @Test
    public void testUsuariosCreation() {
        Roles role = new Roles();
        role.setIdRole(1L);
        role.setName("Admin");

        Usuarios usuario = new Usuarios();
        usuario.setId(1L);
        usuario.setName("John Doe");
        usuario.setEmail("john@example.com");
        usuario.setPassword("password");
        usuario.setSalt("salt");
        usuario.setRoles(Collections.singletonList(role));

        assertEquals(1L, usuario.getId());
        assertEquals("John Doe", usuario.getName());
        assertEquals("john@example.com", usuario.getEmail());
        assertEquals("password", usuario.getPassword());
        assertEquals("salt", usuario.getSalt());
        assertEquals(1, usuario.getRoles().size());
        assertEquals("Admin", usuario.getRoles().get(0).getName());
    }

}
