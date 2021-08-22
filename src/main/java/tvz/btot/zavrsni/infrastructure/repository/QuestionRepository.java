package tvz.btot.zavrsni.infrastructure.repository;

import tvz.btot.zavrsni.domain.Question;
import tvz.btot.zavrsni.web.form.QuestionForm;

import java.util.List;

public interface QuestionRepository {
    List<Question> findAll();
    Question findById(Integer questionId);
    Question create(QuestionForm form);
    Question update(Integer questionId, QuestionForm questionForm);
    void delete(Integer questionId);
}
