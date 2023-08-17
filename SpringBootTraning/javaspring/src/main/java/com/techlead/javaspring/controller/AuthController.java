package com.techlead.javaspring.controller;


import com.techlead.javaspring.entity.User;
import com.techlead.javaspring.mapper.UserMapper;
import com.techlead.javaspring.repository.UserRepository;
import com.techlead.javaspring.request.LoginRequest;
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
import org.springframework.web.bind.annotation.*;

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

    public static final int MAX_AGE_COOKIE = 7 * 24 * 60 * 60;
    @PostMapping("login-handle")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session, HttpServletResponse httpServletResponse) {
        // Tạo đối tượng xác thực
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        try {
            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(token);

            // Lưu dữ liệu vào context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lấy ra thông tin của user
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());

            // Tạo ra token
            String jwtToken = jwtUtils.generateToken(userDetails);


            // Tìm kiếm user
            User user = userRepository.findByEmail(authentication.getName()).orElse(null);

            return ResponseEntity.ok(jwtToken);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logout-handle")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout successful");
    }

}
