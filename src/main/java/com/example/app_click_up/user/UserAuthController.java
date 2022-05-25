package com.example.app_click_up.user;

import com.example.app_click_up.base.ApiResponse;
import com.example.app_click_up.security.AuthService;
import com.example.app_click_up.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody UserRegisterDto registerDto) {
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@RequestBody UserLoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            ));

            User user = (User) authentication.getPrincipal();
            String token = jwtProvider.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }catch (Exception e){
            return ResponseEntity.status(409).body(new ApiResponse("invalid email or password", false));
        }
    }

    @PutMapping("/verifyEmail")
    public HttpEntity<?> verifyUser(@RequestParam String email,
                                   @RequestParam String emailCode) {
        ApiResponse apiResponse = authService.verifyEmail(email, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
