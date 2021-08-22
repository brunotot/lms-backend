package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Exam;
import tvz.btot.zavrsni.web.form.ExamForm;

import java.util.List;

public interface ExamRepository {
    List<Exam> findAllBySubjectId(Integer subjectId);
    Exam create(ExamForm examForm);
    Exam findById(Integer examId);
    void delete(Integer examId);
    Exam update(Integer examId, ExamForm examForm);
    List<Exam> findAll();
    void submitAnswer(Integer examId, Integer questionId, Integer userId, Integer answerId);
    void terminate(Integer examId, Integer userId);
    void startExam(Integer examId, Integer userId);
}
