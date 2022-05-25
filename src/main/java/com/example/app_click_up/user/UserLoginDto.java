package com.example.app_click_up.user;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;

}
