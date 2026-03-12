package com.pregueapalavra.posGraduationControl.packages.classSession;

import java.time.LocalDate;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pregueapalavra.posGraduationControl.exception.exceptions.DatabaseException;
import com.pregueapalavra.posGraduationControl.exception.exceptions.ResourceNotFoundException;
import com.pregueapalavra.posGraduationControl.packages.classSession.dto.ClassSessionResponse;
import com.pregueapalavra.posGraduationControl.packages.classSession.dto.CreateClassSessionRequest;
import com.pregueapalavra.posGraduationControl.packages.classSession.dto.UpdateClassSessionRequest;
import com.pregueapalavra.posGraduationControl.packages.classSession.mapper.ClassSessionMapper;
import com.pregueapalavra.posGraduationControl.packages.subject.SubjectEntity;
import com.pregueapalavra.posGraduationControl.packages.subject.SubjectRepository;
import com.pregueapalavra.posGraduationControl.packages.teacher.TeacherEntity;
import com.pregueapalavra.posGraduationControl.packages.teacher.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassSessionService {

    private final ClassSessionRepository classSessionRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public ClassSessionResponse createClassSession(CreateClassSessionRequest requestDTO) {

        SubjectEntity subject = subjectRepository
                .findById(requestDTO.subjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        TeacherEntity teacher = teacherRepository
                .findById(requestDTO.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        validateDates(requestDTO.initialDate(), requestDTO.finalDate());

        ClassSessionEntity classSessionEntity = ClassSessionMapper.toCreatedEntity(requestDTO, teacher, subject);
        ClassSessionEntity savedClassSession = classSessionRepository.save(classSessionEntity);
        return ClassSessionMapper.toDTO(savedClassSession);
    }

    public ClassSessionResponse updateClassSession(Long id, UpdateClassSessionRequest requestDTO) {

        TeacherEntity teacher = null;

        if (requestDTO.teacherId() != null) {
            teacher = teacherRepository
                    .findById(requestDTO.teacherId())
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        }

        ClassSessionEntity classSessionEntity = findClassSessionById(id);

        LocalDate initial = requestDTO.initialDate() != null
                ? requestDTO.initialDate()
                : classSessionEntity.getInitialDate();

        LocalDate end = requestDTO.finalDate() != null
                ? requestDTO.finalDate()
                : classSessionEntity.getFinalDate();

        validateDates(initial, end);

        ClassSessionMapper.toUpdateEntity(classSessionEntity, requestDTO, teacher);
        ClassSessionEntity updatedClassSession = classSessionRepository.save(classSessionEntity);
        return ClassSessionMapper.toDTO(updatedClassSession);
    }

    @Transactional(readOnly = true)
    public Page<ClassSessionResponse> getClassSessions(Pageable pageable) {
        Page<ClassSessionEntity> pageClassSessions = classSessionRepository.findAll(pageable);
        return pageClassSessions.map(ClassSessionMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ClassSessionResponse getClassSessionById(Long id) {
        ClassSessionEntity entity = findClassSessionById(id);
        return ClassSessionMapper.toDTO(entity);
    }

    public void deleteClassSession(Long id) {

        if (!classSessionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Class session not found with id: " + id);
        }

        try {
            classSessionRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Error deleting class session with id: " + id);
        }
    }

    // ----------------------------------------------------------------------------

    private ClassSessionEntity findClassSessionById(Long id) {
        return classSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class session not found with id: " + id));
    }

    private void validateDates(LocalDate initial, LocalDate end) {
        if (initial != null && end != null && end.isBefore(initial)) {
            throw new IllegalArgumentException("Final date cannot be before initial date");
        }
    }
}
