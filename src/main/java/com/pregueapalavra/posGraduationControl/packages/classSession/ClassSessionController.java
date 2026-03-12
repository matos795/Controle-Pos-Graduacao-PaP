package com.pregueapalavra.posGraduationControl.packages.classSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pregueapalavra.posGraduationControl.packages.classSession.dto.ClassSessionResponse;
import com.pregueapalavra.posGraduationControl.packages.classSession.dto.CreateClassSessionRequest;
import com.pregueapalavra.posGraduationControl.packages.classSession.dto.UpdateClassSessionRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/class-sessions")
@RequiredArgsConstructor
public class ClassSessionController {

    private final ClassSessionService classSessionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassSessionResponse createClassSession(@Valid @RequestBody CreateClassSessionRequest requestDTO) {
        return classSessionService.createClassSession(requestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClassSessionResponse> getClassSessions(Pageable pageable) {
        return classSessionService.getClassSessions(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassSessionResponse getClassSessionById(@PathVariable Long id) {
        return classSessionService.getClassSessionById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassSessionResponse updateClassSession(@PathVariable Long id, @Valid @RequestBody UpdateClassSessionRequest requestDTO) {
        return classSessionService.updateClassSession(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassSession(@PathVariable Long id) {
        classSessionService.deleteClassSession(id);
    }
}
