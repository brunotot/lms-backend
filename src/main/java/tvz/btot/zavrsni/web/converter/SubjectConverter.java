package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.Subject;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.SubjectForm;

@Component
public class SubjectConverter implements BaseConverter<Subject, SubjectForm, SubjectDto> {
    @Override
    public SubjectDto sourceToDto(final Subject source) {
        return source == null ? null : SubjectDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .courseIds(source.getCourseIds())
                .build();
    }

    @Override
    public SubjectForm sourceToForm(final Subject source) {
        return source == null ? null : SubjectForm.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .courseIds(source.getCourseIds())
                .build();
    }

    @Override
    public Subject formToSource(final SubjectForm form) {
        return form == null ? null : Subject.builder()
                .id(form.getId())
                .name(form.getName())
                .description(form.getDescription())
                .courseIds(form.getCourseIds())
                .build();
    }
}
