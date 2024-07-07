package com.example.nhahang.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nhahang.model.request.CreateUserRequest;
import com.example.nhahang.model.request.LoginRequest;
import com.example.nhahang.model.response.MessageResponse;
import com.example.nhahang.model.response.UserInfoResponse;
import com.example.nhahang.security.jwt.JwtUtils;
import com.example.nhahang.security.service.UserDetailsImpl;
import com.example.nhahang.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth") // Định nghĩa URL cơ sở cho tất cả các phương thức
@CrossOrigin(origins = "*",maxAge = 3600) // Cho phép các yêu cầu từ bất kỳ nguồn nào (CORS) với thời gian tối đa lưu trữ kết quả CORS là 3600 giây.
public class AuthController {

    @Autowired // tự động tiêm phụ thuộc
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/login") // Định nghĩa phương thức xử lý yêu cầu HTTP
    @Operation(summary="Đăng nhập") // Cung cấp mô tả ngắn gọn cho phương thức
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword())); // xác thực thông tin và tạo đối tượng

        SecurityContextHolder.getContext().setAuthentication(authentication); // Lưu đối tượng vào context bảo mật của spring

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();// lấy thông tin người dùng

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails); // tạo một JWT và lưu vào cookie

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
//         return ResponseEntity.ok(jwtCookie);
    }

  

    @PostMapping("/register")
    @Operation(summary="Đăng ký")
    public ResponseEntity<?> register(@Valid @RequestBody CreateUserRequest request){ // chuyển chuỗi JSON trong request thành một Object Java
      
        userService.register(request);
      
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    @Operation(summary="Đăng xuất")
    public ResponseEntity<?> logoutUser() {
      ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
          .body(new MessageResponse("You've been logout!"));
    }
}
