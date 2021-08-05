package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.Question;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.QuestionDto;
import tvz.btot.zavrsni.web.form.QuestionForm;

@Component
public class QuestionConverter implements BaseConverter<Question, QuestionForm, QuestionDto> {
    @Override
    public QuestionDto sourceToDto(final Question source) {
        return source == null ? null : QuestionDto.builder()
                .id(source.getId())
                .questionType(source.getQuestionType())
                .points(source.getPoints())
                .text(source.getText())
                .build();
    }

    @Override
    public Question formToSource(final QuestionForm form) {
        return form == null ? null : Question.builder()
                .id(form.getId())
                .questionType(form.getQuestionType())
                .points(form.getPoints())
                .text(form.getText())
                .build();
    }
}
