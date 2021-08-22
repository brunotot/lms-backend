package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.Exam;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.infrastructure.utils.DateTimeUtils;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.ExamDto;
import tvz.btot.zavrsni.web.form.ExamForm;

@Component
public class ExamConverter implements BaseConverter<Exam, ExamForm, ExamDto> {
    private final SubjectConverter subjectConverter;
    private final QuestionConverter questionConverter;

    public ExamConverter(final SubjectConverter subjectConverter,
                         final QuestionConverter questionConverter) {
        this.subjectConverter = subjectConverter;
        this.questionConverter = questionConverter;
    }

    @Override
    public ExamForm sourceToForm(final Exam source) {
        return source == null ? null : ExamForm.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .subjectId(source.getSubject() == null ? null : source.getSubject().getId())
                .totalPoints(source.getTotalPoints())
                .dateEnd(source.getDateEnd())
                .dateStart(source.getDateStart())
                .activeForUser(source.getActiveForUser())
                .build();
    }

    @Override
    public ExamDto sourceToDto(final Exam source) {
        return source == null ? null : ExamDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .dateEnd(DateTimeUtils.convertTimestampToDateString(source.getDateEnd()))
                .dateStart(DateTimeUtils.convertTimestampToDateString(source.getDateStart()))
                .subject(subjectConverter.sourceToDto(source.getSubject()))
                .questions(questionConverter.sourceToDtoList(source.getQuestions()))
                .totalPoints(source.getTotalPoints())
                .activeForUser(source.getActiveForUser())
                .build();
    }

    @Override
    public Exam formToSource(final ExamForm form) {
        return form == null ? null : Exam.builder()
                .id(form.getId())
                .description(form.getDescription())
                .name(form.getName())
                .dateEnd(form.getDateEnd())
                .dateStart(form.getDateStart())
                .totalPoints(form.getTotalPoints())
                .subject(Subject.builder().id(form.getSubjectId()).build())
                .activeForUser(form.getActiveForUser())
                .build();
    }
}
