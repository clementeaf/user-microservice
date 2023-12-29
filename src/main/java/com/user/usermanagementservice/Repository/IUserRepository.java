package com.user.usermanagementservice.Repository;

import com.user.usermanagementservice.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByName(String name);
    Boolean existsByName(String name);
}
