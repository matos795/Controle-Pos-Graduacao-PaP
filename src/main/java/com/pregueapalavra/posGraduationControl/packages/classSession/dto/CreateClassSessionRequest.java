package com.pregueapalavra.posGraduationControl.packages.classSession.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateClassSessionRequest(

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    String title,
    @NotNull(message = "Subject is required")
    Long subjectId,
    @NotNull(message = "Initial date is required")
    LocalDate initialDate,
    @NotNull(message = "Final date is required")
    LocalDate finalDate,
    @NotNull(message = "Teacher is required")
    Long teacherId
) {}
