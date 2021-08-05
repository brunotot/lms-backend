package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.domain.Exam;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.repository.ExamRepository;
import tvz.btot.zavrsni.infrastructure.service.ExamService;
import tvz.btot.zavrsni.web.converter.ExamConverter;
import tvz.btot.zavrsni.web.dto.ExamDto;
import tvz.btot.zavrsni.web.form.ExamForm;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ExamConverter examConverter;

    public ExamServiceImpl(final ExamRepository examRepository, final ExamConverter examConverter) {
        this.examRepository = examRepository;
        this.examConverter = examConverter;
    }

    @Override
    public List<ExamDto> findAllBySubjectId(final Integer subjectId) {
        return examConverter.sourceToDtoList(examRepository.findAllBySubjectId(subjectId));
    }

    @Override
    public ExamDto create(final ExamForm examForm) {
        Exam exam = examRepository.create(examForm);
        exam.setSubject(Subject.builder().id(examForm.getSubjectId()).build());
        return examConverter.sourceToDto(exam);
    }

    @Override
    public ExamForm getForm(final Integer examId) {
        return examConverter.sourceToForm(examRepository.findById(examId));
    }

    @Override
    public void delete(final Integer examId) {
        examRepository.delete(examId);
    }
}
