package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.Course;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.CourseDto;
import tvz.btot.zavrsni.web.form.CourseForm;

@Component
public class CourseConverter implements BaseConverter<Course, CourseForm, CourseDto> {
    private final SubjectConverter subjectConverter;

    public CourseConverter(final SubjectConverter subjectConverter) {
        this.subjectConverter = subjectConverter;
    }

    @Override
    public CourseDto sourceToDto(final Course source) {
        return source == null ? null : CourseDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .subjects(subjectConverter.sourceToDtoList(source.getSubjects()))
                .build();
    }

    @Override
    public CourseForm sourceToForm(final Course source) {
        return source == null ? null : CourseForm.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .subjects(subjectConverter.sourceToFormList(source.getSubjects()))
                .build();
    }

    @Override
    public Course formToSource(final CourseForm form) {
        return form == null ? null : Course.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .subjects(subjectConverter.formToSourceList(form.getSubjects()))
                .build();
    }
}
