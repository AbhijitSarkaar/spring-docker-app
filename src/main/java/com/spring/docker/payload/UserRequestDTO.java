package com.spring.docker.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank
    @Email
    @Size(min = 3, max = 30)
    private String email;

    @NotBlank
    @Size(min = 2, max = 20)
    private String username;
}
