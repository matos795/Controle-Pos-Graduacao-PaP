package com.pregueapalavra.posGraduationControl.teacher.factory;

import com.pregueapalavra.posGraduationControl.packages.teacher.TeacherEntity;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.CreateTeacherRequest;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.TeacherResponse;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.TeacherSummaryResponse;

public class CreateTeacherTestFactory {

    public static CreateTeacherRequest createRequest() {
        return new CreateTeacherRequest(
                "Timóteo Werner",
                "tim@gmail.com",
                "(11) 97777-2299",
                "Palavra da Vida - Atibaia SP");
    }

    public static TeacherEntity createEntity() {
        TeacherEntity entity = new TeacherEntity();
        entity.setId(1L);
        entity.setName("Timóteo Werner");
        entity.setEmail("tim@gmail.com");
        entity.setPhone("(11) 97777-2299");
        entity.setAddress("Palavra da Vida - Atibaia SP");
        return entity;
    }

    public static TeacherResponse createResponse() {
        return new TeacherResponse(
                1L,
                "Timóteo Werner",
                "tim@gmail.com",
                "(11) 97777-2299",
                "Palavra da Vida - Atibaia SP"
        );
    }

    public static TeacherSummaryResponse getSummaryResponse() {
        return new TeacherSummaryResponse(
                1L,
                "Timóteo Werner"
        );
    }
}
