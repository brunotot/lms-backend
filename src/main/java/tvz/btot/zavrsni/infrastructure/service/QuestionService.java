package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.web.dto.QuestionDto;
import tvz.btot.zavrsni.web.form.QuestionForm;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> findAll();
    QuestionDto findById(Integer questionId);
    QuestionForm getFormById(Integer questionId);
    
    QuestionDto create(QuestionForm form);
    
    QuestionDto update(Integer questionId, QuestionForm questionForm);
    
    void delete(Integer questionId);
}
