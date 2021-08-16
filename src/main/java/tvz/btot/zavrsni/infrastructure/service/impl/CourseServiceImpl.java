package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.service.CourseService;
import tvz.btot.zavrsni.web.converter.CourseConverter;
import tvz.btot.zavrsni.web.converter.SubjectConverter;
import tvz.btot.zavrsni.web.dto.CourseDto;
import tvz.btot.zavrsni.web.dto.ExamDto;
import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.CourseForm;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final SubjectConverter subjectConverter;
    private final CourseRepository courseRepository;
    private final CourseConverter courseConverter;

    public CourseServiceImpl(final CourseRepository courseRepository,
                             final CourseConverter courseConverter,
                             final SubjectConverter subjectConverter) {
        this.courseRepository = courseRepository;
        this.courseConverter = courseConverter;
        this.subjectConverter = subjectConverter;
    }

    @Override
    public List<CourseDto> getAll() {
        return courseConverter.sourceToDtoList(courseRepository.getAll());
    }

    @Override
    public CourseDto create(final CourseForm courseForm) {
        return courseConverter.sourceToDto(courseRepository.create(courseForm));
    }

    @Override
    public CourseForm getForm(final Integer courseId) {
        return courseRepository.getForm(courseId);
    }

    @Override
    public void delete(final Integer courseId) {
        courseRepository.delete(courseId);
    }

    @Override
    public CourseDto update(final Integer courseId, final CourseForm courseForm) {
        return courseConverter.sourceToDto(courseRepository.update(courseId, courseForm));
    }

    @Override
    public CourseDto findById(final Integer courseId) {
        return courseConverter.sourceToDto(courseRepository.findById(courseId));
    }

    @Override
    public SubjectDto addSubject(final Integer courseId, final Integer subjectId) {
        return subjectConverter.sourceToDto(courseRepository.addSubject(courseId, subjectId));
    }

    @Override
    public void removeSubject(Integer courseId, Integer subjectId) {
        courseRepository.removeSubject(courseId, subjectId);
    }
}
