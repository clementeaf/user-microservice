package com.user.usermanagementservice.Repository;

import com.user.usermanagementservice.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(String name);
}
