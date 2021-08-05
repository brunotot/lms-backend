package tvz.btot.zavrsni.web.converter;

import org.springframework.stereotype.Component;
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
                .active(source.getActive())
                .build();
    }

    @Override
    public UserDto sourceToDto(final User source) {
        return source == null ? null : UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .email(source.getEmail())
                .roles(source.getRoles())
                .active(source.getActive())
                .build();
    }

    @Override
    public User formToSource(final UserForm form) {
        return form == null ? null : User.builder()
                .id(form.getId())
                .username(form.getUsername())
                .encryptedPassword(form.getEncryptedPassword())
                .email(form.getEmail())
                .roles(form.getRoles())
                .active(form.getActive())
                .build();
    }

    @Override
    public User dtoToSource(final UserDto dto) {
        return dto == null ? null : User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .roles(dto.getRoles())
                .active(dto.getActive())
                .build();
    }
}
