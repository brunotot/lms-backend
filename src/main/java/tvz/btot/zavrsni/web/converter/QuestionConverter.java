package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.KeyValue;
import tvz.btot.zavrsni.domain.Question;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.QuestionDto;
import tvz.btot.zavrsni.web.form.QuestionForm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class QuestionConverter implements BaseConverter<Question, QuestionForm, QuestionDto> {
    @Override
    public QuestionForm sourceToForm(final Question source) {
        return source == null ? null : QuestionForm.builder()
                .id(source.getId())
                .points(source.getPoints())
                .text(source.getText())
                .examId(source.getExam().getId())
                .correctAnswer(source.getCorrectAnswer())
                .incorrectAnswers(source.getIncorrectAnswers())
                .selectedAnswer(source.getSelectedAnswer())
                .build();
    }

    @Override
    public QuestionDto sourceToDto(final Question source) {
        return source == null ? null : QuestionDto.builder()
                .id(source.getId())
                .points(source.getPoints())
                .text(source.getText())
                .exam(source.getExam())
                .answers(Stream.concat(List.of(source.getCorrectAnswer()).stream(), source.getIncorrectAnswers().stream()).collect(Collectors.toList()))
                .selectedAnswer(source.getSelectedAnswer())
                .build();
    }

    @Override
    public Question formToSource(final QuestionForm form) {
        return form == null ? null : Question.builder()
                .id(form.getId())
                .text(form.getText())
                .points(form.getPoints())
                .exam(KeyValue.builder().id(form.getExamId()).build())
                .correctAnswer(form.getCorrectAnswer())
                .incorrectAnswers(form.getIncorrectAnswers())
                .selectedAnswer(form.getSelectedAnswer())
                .build();
    }
}
