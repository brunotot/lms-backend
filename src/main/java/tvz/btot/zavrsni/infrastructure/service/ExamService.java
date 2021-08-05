package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.web.dto.ExamDto;
import tvz.btot.zavrsni.web.form.ExamForm;

import java.util.List;

public interface ExamService {
    List<ExamDto> findAllBySubjectId(Integer subjectId);
    ExamDto create(ExamForm examForm);
    ExamForm getForm(Integer examId);
    void delete(Integer examId);
}
