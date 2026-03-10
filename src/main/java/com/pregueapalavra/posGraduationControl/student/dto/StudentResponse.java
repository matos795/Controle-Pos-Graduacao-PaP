package com.pregueapalavra.posGraduationControl.student.dto;

public record StudentResponse(
        Long id,
        String name,
        String email,
        String phone,
        String address
) {}