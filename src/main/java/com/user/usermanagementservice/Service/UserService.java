package com.user.usermanagementservice.Service;

import com.user.usermanagementservice.Exception.NoUsersFoundException;
import com.user.usermanagementservice.Model.Usuarios;
import com.user.usermanagementservice.Repository.IUserRepository;
import com.user.usermanagementservice.Service.impl.UserServiceImpl;
import com.user.usermanagementservice.Exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class UserService implements UserServiceImpl {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    LoggingService loggingService;

    @Override
    public List<Usuarios> getAllUsers() {
        List<Usuarios> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new NoUsersFoundException("No se encontraron usuarios registrados");
        }

        return users;
    }

    @Override
    public Usuarios getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public Usuarios createUser(Usuarios user) throws UserNotFoundException{
        return userRepository.save(user);
    }

    @Override
    public Usuarios updateUser(Long id, Usuarios updatedUser) {
        Usuarios existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Obtener todos los campos de User
        Field[] fields = Usuarios.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                // Obtener el valor del campo actualizado
                Object value = field.get(updatedUser);
                if (value != null) {
                    // Establecer el valor en el usuario existente
                    field.set(existingUser, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Manejo adecuado de la excepción en la práctica
            }
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        Usuarios user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        userRepository.deleteById(id);
        System.out.println("User with id: " + id + " deleted");
    }
}
