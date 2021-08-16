package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import tvz.btot.zavrsni.domain.Exam;
import tvz.btot.zavrsni.infrastructure.dao.ExamDao;
import tvz.btot.zavrsni.infrastructure.repository.ExamRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.web.converter.ExamConverter;
import tvz.btot.zavrsni.web.form.ExamForm;

import java.util.List;
import java.util.Optional;

@Repository
public class ExamRepositoryImpl implements ExamRepository {
    private final ExamDao examDao;
    private final ExamConverter examConverter;

    public ExamRepositoryImpl(final ExamDao examDao, final ExamConverter examConverter) {
        this.examDao = examDao;
        this.examConverter = examConverter;
    }

    @Override
    public List<Exam> findAllBySubjectId(final Integer subjectId) {
        return examDao.findAllBySubjectId(SqlQueryParams.newInstance("subjectId", subjectId));
    }

    @Override
    public Exam create(final ExamForm examForm) {
        Exam exam = examConverter.formToSource(examForm);
        Integer id = examDao.create(SqlQueryParams.newInstance(exam));
        exam.setId(id);
        return exam;
    }

    @Override
    public Exam findById(final Integer examId) {
        return Optional.ofNullable(examDao.findById(SqlQueryParams.newInstance("examId", examId)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void delete(final Integer examId) {
        examDao.delete(SqlQueryParams.newInstance("examId", examId));
    }

    @Override
    public Exam update(final Integer examId, final ExamForm examForm) {
        Exam exam = examConverter.formToSource(examForm);
        examDao.update(SqlQueryParams.newInstance(examForm));
        return exam;
    }
}
