package com.pregueapalavra.posGraduationControl.packages.classSession.dto;

import java.time.LocalDate;

import com.pregueapalavra.posGraduationControl.packages.subject.dto.SubjectResponse;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.TeacherSummaryResponse;

public record ClassSessionResponse( 
    Long id,
    String title,
    SubjectResponse subject,
    LocalDate initialDate,
    LocalDate finalDate,
    TeacherSummaryResponse teacher
) {}
