package com.user.usermanagementservice.Repository;

import com.user.usermanagementservice.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByName(String name);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'Ya existe' ELSE 'No existe' END FROM Usuarios u WHERE u.name = ?1")
    String checkIfNameExists(String name);
    Boolean existsByName(String name);
}
