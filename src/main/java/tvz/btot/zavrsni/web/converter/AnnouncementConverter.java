package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.Announcement;
import tvz.btot.zavrsni.domain.KeyValue;
import tvz.btot.zavrsni.infrastructure.utils.DateTimeUtils;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.AnnouncementDto;
import tvz.btot.zavrsni.web.form.AnnouncementForm;

@Component
public class AnnouncementConverter implements BaseConverter<Announcement, AnnouncementForm, AnnouncementDto> {
    @Override
    public AnnouncementForm sourceToForm(final Announcement source) {
        return source == null ? null : AnnouncementForm.builder()
                .id(source.getId())
                .title(source.getTitle())
                .body(source.getBody())
                .type(source.getType())
                .creatorId(source.getCreator().getId())
                .subjectId(source.getSubject().getId())
                .dateStart(source.getDateStart())
                .dateEnd(source.getDateEnd())
                .build();
    }

    @Override
    public AnnouncementDto sourceToDto(final Announcement source) {
        return source == null ? null : AnnouncementDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .body(source.getBody())
                .type(source.getType())
                .creator(source.getCreator())
                .subject(source.getSubject())
                .dateStart(DateTimeUtils.convertTimestampToDateString(source.getDateStart()))
                .dateEnd(DateTimeUtils.convertTimestampToDateString(source.getDateEnd()))
                .build();
    }

    @Override
    public Announcement formToSource(final AnnouncementForm form) {
        return form == null ? null : Announcement.builder()
                .id(form.getId())
                .title(form.getTitle())
                .body(form.getBody())
                .type(form.getType())
                .creator(KeyValue.builder().id(form.getCreatorId()).build())
                .subject(KeyValue.builder().id(form.getSubjectId()).build())
                .dateStart(form.getDateStart())
                .dateEnd(form.getDateEnd())
                .build();
    }
}