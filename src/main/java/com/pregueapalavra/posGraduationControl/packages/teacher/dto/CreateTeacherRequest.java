package com.pregueapalavra.posGraduationControl.packages.teacher.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTeacherRequest(
    
    @NotBlank(message = "Name is required")
    @Size(max = 100)
    String name,

    @Email(message = "Email is invalid")
    @Size(max = 150)
    String email,

    @Size(max = 20, message = "Phone is too long")
    String phone,

    @Size(max = 200, message = "Address is too long")
    String address
) {

}
