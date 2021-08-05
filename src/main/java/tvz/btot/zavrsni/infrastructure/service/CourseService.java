package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.web.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> getAll();
}
