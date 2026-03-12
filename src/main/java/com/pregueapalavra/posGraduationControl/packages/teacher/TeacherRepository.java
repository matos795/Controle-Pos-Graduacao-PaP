package com.pregueapalavra.posGraduationControl.packages.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    boolean existsByEmail(String email);

}
