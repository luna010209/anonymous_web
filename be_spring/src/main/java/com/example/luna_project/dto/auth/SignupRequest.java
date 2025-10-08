package com.example.luna_project.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank(message = "Username is required!!!")
        String username,

        @NotBlank(message = "Password is required!!!")
        @Size(min = 6, message = "Password should be at least 6 characters")
        String password,

        @NotBlank(message = "Please confirm password!!!")
        String cfPassword,

        @NotBlank(message = "Phone number is required!!!")
        String phone,

        @NotBlank(message = "Email is required!!!")
        @Email(message = "Email format is invalid!")
        String email
) {
}
