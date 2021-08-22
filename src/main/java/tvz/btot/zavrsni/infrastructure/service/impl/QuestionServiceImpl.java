package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.repository.QuestionRepository;
import tvz.btot.zavrsni.infrastructure.service.CourseService;
import tvz.btot.zavrsni.infrastructure.service.QuestionService;
import tvz.btot.zavrsni.web.converter.CourseConverter;
import tvz.btot.zavrsni.web.converter.QuestionConverter;
import tvz.btot.zavrsni.web.converter.SubjectConverter;
import tvz.btot.zavrsni.web.dto.CourseDto;
import tvz.btot.zavrsni.web.dto.QuestionDto;
import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.CourseForm;
import tvz.btot.zavrsni.web.form.QuestionForm;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionConverter questionConverter;
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(final QuestionConverter questionConverter,
                               final QuestionRepository questionRepository) {
        this.questionConverter = questionConverter;
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDto> findAll() {
        return questionConverter.sourceToDtoList(questionRepository.findAll());
    }

    @Override
    public QuestionDto findById(final Integer questionId) {
        return questionConverter.sourceToDto(questionRepository.findById(questionId));
    }

    @Override
    public QuestionForm getFormById(final Integer questionId) {
        return questionConverter.sourceToForm(questionRepository.findById(questionId));
    }

    @Override
    public QuestionDto create(final QuestionForm form) {
        return questionConverter.sourceToDto(questionRepository.create(form));
    }

    @Override
    public QuestionDto update(final Integer questionId,
                              final QuestionForm questionForm) {
        return questionConverter.sourceToDto(questionRepository.update(questionId, questionForm));
    }

    @Override
    public void delete(final Integer questionId) {
        questionRepository.delete(questionId);
    }
}
