package com.patika.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(max=100)
    @NotBlank(message="provide your First Name")
    private String firstName;

    @Size(max=50)
    @NotBlank(message="provide your Last Name")
    private String lastName;

    @Size(min=5 ,max=80)
    @Email(message = "provide valid e-mail")
    private String email;

    @Size(min=4 , max=20)
    @NotBlank(message="provide your password")
    private String password;
}
