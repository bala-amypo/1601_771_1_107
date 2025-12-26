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
    
//     @PostMapping("/login")
//     @GetMapping("/{email}")
//     public User getByEmail(@PathVariable String email) {
//         return service.findByEmail(email);
//     }
// }
package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    // LOGIN
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return service.login(request);
    }

    // GET USER BY EMAIL (optional utility endpoint)
    @GetMapping("/{email}")
    @SecurityRequirement(name = "bearerAuth")
    public User getByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
}
