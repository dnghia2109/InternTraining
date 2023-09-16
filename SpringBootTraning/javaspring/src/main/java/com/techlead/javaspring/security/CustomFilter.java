package com.techlead.javaspring.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lấy thông tin header
        String authHeader = request.getHeader("Authorization");
        log.info("authHeader : {}", authHeader);

        // Check xem header có "Authorization" hoặc header có chứa "Bearer" token hay không -
        // có thể dùng cách này hoặc ignoring để có thể truy cập vào các api ko cần authen
        if (authHeader == null || !authHeader.contains("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Lấy token từ trong header
        String jwtToken = authHeader.substring(7);
        log.info("jwtToken : {}", jwtToken);

        // Lấy ra userEmail từ trong token
        String userEmail = jwtUtils.extractUsername(jwtToken);
        log.info("userEmail : {}", userEmail);

        // Kiểm tra userEmail -> Kiểm tra token có hợp lệ ko -> Tạo đối tượng xác thực
        if (userEmail != null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
            log.info("userDetails : {}", userDetails);

            if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        null,
                        userDetails.getAuthorities()
                );
                // Xác thực thành công, lưu object Authentication vào SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
