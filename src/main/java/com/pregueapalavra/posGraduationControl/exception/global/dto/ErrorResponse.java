package com.pregueapalavra.posGraduationControl.exception.global.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime timestamp,
        int status,
        String error
) {}
