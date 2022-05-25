package com.example.app_click_up.security;

import com.example.app_click_up.base.ApiResponse;
import com.example.app_click_up.exception.BadRequestException;
import com.example.app_click_up.exception.ConflictException;
import com.example.app_click_up.exception.NotFoundException;
import com.example.app_click_up.user.User;
import com.example.app_click_up.user.UserMapper;
import com.example.app_click_up.user.UserRegisterDto;
import com.example.app_click_up.user.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JavaMailSender javaMailSender;

    public AuthService(UserRepository userRepository,
                       @Lazy UserMapper userMapper,
                       JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException(email)
        );
    }

    public ApiResponse registerUser(UserRegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail()))
            throw new ConflictException("email already registered", "email");

        User user = userMapper.dtoToObject(registerDto);

        int code = new Random().nextInt(999999);
        user.setEmailCode(String.valueOf(code).substring(0,4));
        userRepository.save(user);
        Boolean sendEmail = sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("User saved successfully", sendEmail);
    }

    public Boolean sendEmail(String sendingEmail, String emailCode) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("sample@sample.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Verification email");
            mailMessage.setText("'http://localhost:8080/api/auth/verifyEmail?emailCode="+emailCode+"&email="+sendingEmail);
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getEmailCode().equals(emailCode)) {
                user.setEnabled(true);
                userRepository.save(user);
                return new ApiResponse("account verified successfully", true);
            }
            throw new BadRequestException("something went wrong", "verification fail");
        }
        throw new NotFoundException("user not found", "email");
    }
}
