package tvz.btot.zavrsni.security.preauthorization;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('SUPERADMIN') or hasAuthority('TEACHER') or hasAuthority('ADMIN')")
public @interface AllowTeacher {
}