package com.user.usermanagementservice.Security;

import com.user.usermanagementservice.Model.Roles;
import com.user.usermanagementservice.Model.Usuarios;
import com.user.usermanagementservice.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final IUserRepository userRepo;

    @Autowired
    public CustomUserDetailService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Obtener lista de autoridades
    public Collection<GrantedAuthority> mapToAuthorities(List<Roles> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    //Obtener un usuario con todos sus datos por medio de username
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Usuarios usuarios = userRepo.findByName(name).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(usuarios.getName(), usuarios.getPassword(), mapToAuthorities(usuarios.getRoles()));
    }
}
