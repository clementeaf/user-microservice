package com.user.usermanagementservice.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RolesTest {

    @Test
    public void testRolesCreation() {
        Roles roles = new Roles();
        roles.setIdRole(1L);
        roles.setName("Admin");

        assertEquals(1L, roles.getIdRole());
        assertEquals("Admin", roles.getName());
    }
}
