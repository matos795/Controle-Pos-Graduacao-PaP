package com.pregueapalavra.posGraduationControl.teacher.factory;

import com.pregueapalavra.posGraduationControl.packages.teacher.TeacherEntity;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.TeacherResponse;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.UpdateTeacherRequest;

public class UpdateTeacherTestFactory {

    public static UpdateTeacherRequest updateRequest() {
        return new UpdateTeacherRequest(
                "Timóteo",
                "tim@gmail.com",
                null,
                null);
    }

    public static TeacherEntity updateEntity() {
        TeacherEntity entity = new TeacherEntity();
        entity.setId(1L);
        entity.setName("Timóteo");
        entity.setEmail("tim@gmail.com");
        entity.setPhone(null);
        entity.setAddress(null);
        return entity;
    }

    public static TeacherResponse updateResponse() {
        return new TeacherResponse(
                1L,
                "Timóteo",
                "tim@gmail.com",
                "(11) 97777-2299",
                "Palavra da Vida - Atibaia SP");
    }
}
