package com.pregueapalavra.posGraduationControl.packages.teacher;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pregueapalavra.posGraduationControl.exception.exceptions.DatabaseException;
import com.pregueapalavra.posGraduationControl.exception.exceptions.ResourceNotFoundException;
import com.pregueapalavra.posGraduationControl.exception.exceptions.student.EmailAlreadyExistsException;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.CreateTeacherRequest;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.TeacherResponse;
import com.pregueapalavra.posGraduationControl.packages.teacher.dto.UpdateTeacherRequest;
import com.pregueapalavra.posGraduationControl.packages.teacher.mapper.TeacherMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherResponse createTeacher(CreateTeacherRequest requestDTO) {
        if (teacherRepository.existsByEmail(requestDTO.email())) {
                throw new EmailAlreadyExistsException("Email já está sendo usado!");
            }
        TeacherEntity teacherEntity = TeacherMapper.toCreatedEntity(requestDTO);
        TeacherEntity savedTeacher = teacherRepository.save(teacherEntity);
        return TeacherMapper.toDTO(savedTeacher);
    }

    public TeacherResponse updateTeacher(Long id, UpdateTeacherRequest requestDTO) {
        TeacherEntity teacherEntity = findTeacherById(id);
        if (requestDTO.email() != null && !requestDTO.email().equals(teacherEntity.getEmail())) {
            if (teacherRepository.existsByEmail(requestDTO.email())) {
                throw new EmailAlreadyExistsException("Email já está sendo usado!");
            }
        }
        TeacherMapper.toUpdateEntity(teacherEntity, requestDTO);
        TeacherEntity updatedTeacher = teacherRepository.save(teacherEntity);
        return TeacherMapper.toDTO(updatedTeacher);
    }

    @Transactional(readOnly = true)
    public Page<TeacherResponse> getTeachers(Pageable pageable) {
        Page<TeacherEntity> pageTeachers = teacherRepository.findAll(pageable);
        return pageTeachers.map(TeacherMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public TeacherResponse getTeacherById(Long id) {
        TeacherEntity entity = findTeacherById(id);
        return TeacherMapper.toDTO(entity);
    }

    public void deleteTeacher(Long id) {

        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Teacher not found with id: " + id);
        }

        try {
            teacherRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Error deleting teacher with id: " + id);
        }
    }

    // ----------------------------------------------------------------------------

    private TeacherEntity findTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }
}
