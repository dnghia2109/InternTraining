package com.techlead.javaspring;

import com.techlead.javaspring.entity.Role;
import com.techlead.javaspring.entity.User;
import com.techlead.javaspring.repository.RoleRepository;
import com.techlead.javaspring.repository.UserRepository;
import com.techlead.javaspring.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class JavaspringApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;


    @Test
    void save_roles() {
        List<Role> roles = List.of(
                new Role(null, "ADMIN"),
                new Role(null, "USER"),
                new Role(null, "AUTHOR")
        );

        roleRepository.saveAll(roles);
    }

    @Test
    void save_users() {
        Role userRole = roleRepository.findByName("USER").orElse(null);
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        Role authorRole = roleRepository.findByName("AUTHOR").orElse(null);

        List<User> users = List.of(
                new User(null, "Nghĩa", "nghia@gmail.com", passwordEncoder.encode("111"), List.of(adminRole, userRole)),
                new User(null, "Minh Duy", "duy@gmail.com", passwordEncoder.encode("111"), List.of(userRole)),
                new User(null, "Thu Hằng", "hang@gmail.com", passwordEncoder.encode("111"), List.of(authorRole, userRole))
        );

        userRepository.saveAll(users);
    }

    @Test
    void generateToken() {
        User user = userRepository.findByEmail("nghia@gmail.com").get();
        String token = jwtUtils.generateToken(user);

        System.out.println(token);
    }

//    @Test
//    void

}
