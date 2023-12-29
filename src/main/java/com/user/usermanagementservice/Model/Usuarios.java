package com.user.usermanagementservice.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "El nombre solo puede contener letras y espacios")
    private String name;

    @Email
    private String email;

    private String password;

    @JsonIgnore
    private String salt;

    // fetchType en EAGER para que cada vez que se acceda o se extraiga un usuario de la BD, este traiga consigo todos sus roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    /* COn JoinTable estaremos creando una tabla que unirá la tabla de usuario y role, con el cual tendremos un total de 3 tablas relacionadas en la tabla
    * "user_role", a través de sus columnas usuario_id que apuntará al ID de la tabla usuario y role_id que apuntará al Id de la tabla role */
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id_role"))
    private List<Roles> roles = new ArrayList<>();
}
