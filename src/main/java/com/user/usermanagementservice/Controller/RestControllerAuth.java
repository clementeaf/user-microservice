package com.user.usermanagementservice.Controller;

import com.user.usermanagementservice.DTOs.DtoAuthRespuesta;
import com.user.usermanagementservice.DTOs.DtoLogin;
import com.user.usermanagementservice.DTOs.DtoRegistro;
import com.user.usermanagementservice.Model.Roles;
import com.user.usermanagementservice.Model.Usuarios;
import com.user.usermanagementservice.Repository.IRoleRepository;
import com.user.usermanagementservice.Repository.IUserRepository;
import com.user.usermanagementservice.Security.JwtTokenProvider;
import com.user.usermanagementservice.Security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(origins = "http://localhost:3200")
public class RestControllerAuth {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final JwtTokenProvider jwtGenerador;

    @Autowired
    public RestControllerAuth(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, IRoleRepository roleRepository, IUserRepository userRepository, JwtTokenProvider jwtGenerador){
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtGenerador = jwtGenerador;
    }

    // Métdodo para poder registrar usuarios con rol "USER"
    @PostMapping("registerUser")
    public ResponseEntity<String> registrarUsuario(@RequestBody DtoRegistro dtoRegistro) {
        if (userRepository.existsByName(dtoRegistro.getUsername())){
            return new ResponseEntity<>("Usuario ya existe, intentar con otro nombre", HttpStatus.BAD_REQUEST);
        }
        Usuarios usuarios = new Usuarios();
        usuarios.setName(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Roles roles = roleRepository.findByName("USER").orElse(null);
        usuarios.setRoles(Collections.singletonList(roles));
        userRepository.save(usuarios);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }

    // Métdodo para poder registrar usuarios con rol "ADMIN"
    @PostMapping("registerAdm")
    public ResponseEntity<String> registrarAdmin(@RequestBody DtoRegistro dtoRegistro) {
        if (userRepository.existsByName(dtoRegistro.getUsername())){
            return new ResponseEntity<>("Usuario ya existe, intentar con otro nombre", HttpStatus.BAD_REQUEST);
        }
        Usuarios usuarios = new Usuarios();
        usuarios.setName(dtoRegistro.getUsername());
        usuarios.setPassword(passwordEncoder.encode(dtoRegistro.getPassword()));
        Roles roles = roleRepository.findByName("ADMIN").orElse(null);
        usuarios.setRoles(Collections.singletonList(roles));
        userRepository.save(usuarios);
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }

    // Método para login y obtener token
    @PostMapping("login")
    public ResponseEntity<DtoAuthRespuesta> login(@RequestBody DtoLogin dtoLogin){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dtoLogin.getUsername(), dtoLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);
        return new ResponseEntity<>(new DtoAuthRespuesta(token), HttpStatus.OK);
    }
}
