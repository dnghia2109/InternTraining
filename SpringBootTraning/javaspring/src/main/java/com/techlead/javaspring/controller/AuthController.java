package com.techlead.javaspring.controller;


import com.techlead.javaspring.entity.Role;
import com.techlead.javaspring.entity.User;
import com.techlead.javaspring.exception.BadRequestException;
import com.techlead.javaspring.mapper.UserMapper;
import com.techlead.javaspring.repository.RoleRepository;
import com.techlead.javaspring.repository.UserRepository;
import com.techlead.javaspring.request.LoginRequest;
import com.techlead.javaspring.request.RegisterRequest;
import com.techlead.javaspring.response.AuthLoginResponse;
import com.techlead.javaspring.security.CustomUserDetailsService;
import com.techlead.javaspring.security.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Documented;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("login-handle")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session, HttpServletResponse httpServletResponse) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {
            // Tiến hành xác thực -> sẽ gọi đến UserDetailService để xác thực username và password đã mã hóa trog db
            // -> Xác thực fail trả ra message tbao
            Authentication authentication = authenticationManager.authenticate(token);

            // Xác thực thành công -> Lưu dữ liệu vào context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lấy ra thông tin của user
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());

            // Tạo ra token
            String jwtToken = jwtUtils.generateToken(userDetails);

            // Tìm kiếm user (optional, thêm đoạn này chỉdddeer trả ra thông tin user login, chỉ cần return jwt là đủ)
            User user = userRepository.findByEmail(authentication.getName()).orElse(null);

            //return ResponseEntity.ok(jwtToken);
            return ResponseEntity.ok(new AuthLoginResponse(UserMapper.toUserDto(user), jwtToken, true));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Email/Password is not correct. Please check again!");
        }
    }


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new BadRequestException("Email already exists in the system. Please use another email! ");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Password confirm doesn't match password. Please check again!");
        }

        Role userRole = roleRepository.findByName("USER").orElse(null);

        User user1 = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(userRole))
                .build();


        userRepository.save(user1);
        return ResponseEntity.ok("Register successful!");
    }

    // TODO: register and send token to email to active account



    @GetMapping("/logout-handle")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout successful!");
    }

}
