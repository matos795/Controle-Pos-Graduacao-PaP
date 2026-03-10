package com.pregueapalavra.posGraduationControl.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

    boolean existsByEmail(String email);
}
