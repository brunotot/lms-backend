package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.service.CourseService;
import tvz.btot.zavrsni.web.converter.CourseConverter;
import tvz.btot.zavrsni.web.dto.CourseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseConverter courseConverter;

    public CourseServiceImpl(final CourseRepository courseRepository, final CourseConverter courseConverter) {
        this.courseRepository = courseRepository;
        this.courseConverter = courseConverter;
    }

    @Override
    public List<CourseDto> getAll() {
        return courseConverter.sourceToDtoList(courseRepository.getAll());
    }
}
