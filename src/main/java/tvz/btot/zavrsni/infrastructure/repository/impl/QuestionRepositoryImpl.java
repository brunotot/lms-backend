package tvz.btot.zavrsni.infrastructure.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tvz.btot.zavrsni.domain.KeyValue;
import tvz.btot.zavrsni.domain.Question;
import tvz.btot.zavrsni.infrastructure.dao.QuestionDao;
import tvz.btot.zavrsni.infrastructure.repository.QuestionRepository;
import tvz.btot.zavrsni.infrastructure.utils.SqlQueryParams;
import tvz.btot.zavrsni.web.converter.QuestionConverter;
import tvz.btot.zavrsni.web.form.QuestionForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private final QuestionDao questionDao;
    private final QuestionConverter questionConverter;

    public QuestionRepositoryImpl(final QuestionDao questionDao,
                                  final QuestionConverter questionConverter) {
        this.questionDao = questionDao;
        this.questionConverter = questionConverter;
    }

    @Override
    public List<Question> findAll() {
        return questionDao.findAll(SqlQueryParams.newInstance());
    }

    @Override
    public Question findById(final Integer questionId) {
        return questionDao.findById(SqlQueryParams.newInstance("questionId", questionId));
    }

    @Override
    @Transactional
    public Question create(final QuestionForm form) {
        Question question = questionConverter.formToSource(form);
        KeyValue newCorrectAnswer = adaptAnswer(question.getCorrectAnswer());
        List<KeyValue> newIncorrectAnswers = adaptIncorrectAnswers(question.getIncorrectAnswers());
        question.setCorrectAnswer(newCorrectAnswer);
        question.setIncorrectAnswers(newIncorrectAnswers);
        SqlQueryParams params = SqlQueryParams.newInstance(question);
        questionDao.create(params);
        question.setId(params.getOutputParam("newQuestionId", Integer.class));
        return question;
    }

    @Override
    public Question update(final Integer questionId, final QuestionForm questionForm) {
        Question question = questionConverter.formToSource(questionForm);
        question.setId(questionId);
        questionDao.update(SqlQueryParams.newInstance(question));
        return question;
    }

    @Override
    public void delete(final Integer questionId) {
        questionDao.delete(SqlQueryParams.newInstance("questionId", questionId));
    }

    public KeyValue adaptAnswer(final KeyValue answer) {
        if (answer == null) return null;
        KeyValue answerCopy = KeyValue.builder()
                .id(answer.getId())
                .idString(answer.getIdString())
                .value(answer.getValue())
                .valueObject(answer.getValueObject())
                .build();
        if (answerCopy.getId() == null) {
            SqlQueryParams params = SqlQueryParams.newInstance("answer", answerCopy.getValue());
            Integer answerId = questionDao.createAnswer(params);
            answerCopy.setId(answerId);
        }
        return answerCopy;
    }

    public List<KeyValue> adaptIncorrectAnswers(final List<KeyValue> currentIncorrectAnswers) {
        List<KeyValue> listCopy = new ArrayList<>(Optional.ofNullable(currentIncorrectAnswers)
                .orElse(List.of()));
        listCopy.forEach(incorrectAnswer -> incorrectAnswer.setId(this.adaptAnswer(incorrectAnswer).getId()));
        return listCopy;
    }
}
