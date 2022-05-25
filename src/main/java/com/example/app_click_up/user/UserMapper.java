package com.example.app_click_up.user;

import com.example.app_click_up.base.CustomMapper;
import com.example.app_click_up.base.SystemRoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper implements CustomMapper<User, UserRegisterDto> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User dtoToObject(UserRegisterDto registerDto) {
        if (registerDto==null){
            return null;
        }

        return User.builder()
                .fullName(registerDto.getFullName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .systemRoleName(SystemRoleName.SYSTEM_USER)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

    @Override
    public UserRegisterDto objectToDto(User user) {
        return null;
    }

    @Override
    public User dtoToObject(UserRegisterDto registerDto, User user) {
        return null;
    }
}
