package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.SubjectForm;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getAllByCourseId(int courseId);
    List<SubjectDto> getAllByUserId(int userId);
    SubjectDto findById(Integer subjectId);
    void delete(Integer subjectId);
    List<SubjectDto> findAll();
    SubjectDto create(SubjectForm form);
    SubjectDto update(Integer subjectId, SubjectForm subjectForm);
    SubjectForm getFormById(Integer subjectId);
}
