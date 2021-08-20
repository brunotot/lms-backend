package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
import tvz.btot.zavrsni.domain.KeyValue;
import tvz.btot.zavrsni.domain.User;
import tvz.btot.zavrsni.web.converter.base.BaseConverter;
import tvz.btot.zavrsni.web.dto.UserDto;
import tvz.btot.zavrsni.web.form.UserForm;

@Component
public class UserConverter implements BaseConverter<User, UserForm, UserDto> {
    @Override
    public UserForm sourceToForm(final User source) {
        return source == null ? null : UserForm.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password(source.getEncryptedPassword())
                .email(source.getEmail())
                .roles(source.getRoles())
                .courseId(source.getCourse().getId())
                .active(source.getActive() == 1)
                .build();
    }

    @Override
    public UserDto sourceToDto(final User source) {
        return source == null ? null : UserDto.builder()
                .id(source.getId())
                .course(source.getCourse())
                .username(source.getUsername())
                .email(source.getEmail())
                .roles(source.getRoles())
                .active(Integer.valueOf(1).equals(source.getActive()))
                .build();
    }

    @Override
    public User formToSource(final UserForm form) {
        return form == null ? null : User.builder()
                .id(form.getId())
                .course(KeyValue.builder().id(form.getCourseId()).build())
                .username(form.getUsername())
                .encryptedPassword(form.getEncryptedPassword())
                .email(form.getEmail())
                .roles(form.getRoles())
                .active(form.isActive() ? 1 : 0)
                .build();
    }
}
