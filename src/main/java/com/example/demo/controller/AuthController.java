// package com.example.demo.controller;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
// import com.example.demo.model.User;
// import com.example.demo.service.UserService;
// import org.springframework.web.bind.annotation.*;


// @RestController
// @SecurityRequirement(name = "bearerAuth")
// @RequestMapping("/users")
// public class AuthController {

//     private final UserService service;

//     public AuthController(UserService service) {
//         this.service = service;
//     }

//     @PostMapping("/register")
//     public User register(@RequestBody User user) {
//         return service.register(user);
//     }
    
//     @GetMapping("/{email}")
//     public User getByEmail(@PathVariable String email) {
//         return service.findByEmail(email);
//     }
// }
package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/users")
public class AuthController {

    private final UserService service;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService service,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return service.register(user);
    }

    // ✅ LOGIN ENDPOINT
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = service.findByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
}
