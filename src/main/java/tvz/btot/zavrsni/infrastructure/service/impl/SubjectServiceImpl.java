package tvz.btot.zavrsni.infrastructure.service.impl;

import org.springframework.stereotype.Service;
import tvz.btot.zavrsni.infrastructure.repository.CourseRepository;
import tvz.btot.zavrsni.infrastructure.repository.SubjectRepository;
import tvz.btot.zavrsni.infrastructure.service.CourseService;
import tvz.btot.zavrsni.infrastructure.service.SubjectService;
import tvz.btot.zavrsni.web.converter.CourseConverter;
import tvz.btot.zavrsni.web.converter.SubjectConverter;
import tvz.btot.zavrsni.web.dto.CourseDto;
import tvz.btot.zavrsni.web.dto.SubjectDto;
import tvz.btot.zavrsni.web.form.SubjectForm;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectConverter subjectConverter;

    public SubjectServiceImpl(final SubjectRepository subjectRepository, final SubjectConverter subjectConverter) {
        this.subjectRepository = subjectRepository;
        this.subjectConverter = subjectConverter;
    }

    @Override
    public List<SubjectDto> getAllByCourseId(int courseId) {
        return subjectConverter.sourceToDtoList(subjectRepository.getAllByCourseId(courseId));
    }

    @Override
    public List<SubjectDto> getAllByUserId(int userId) {
        return subjectConverter.sourceToDtoList(subjectRepository.getAllByUserId(userId));
    }

    @Override
    public SubjectDto findById(final Integer subjectId) {
        return subjectConverter.sourceToDto(subjectRepository.findById(subjectId));
    }

    @Override
    public void delete(final Integer subjectId) {
        subjectRepository.delete(subjectId);
    }

    @Override
    public List<SubjectDto> findAll() {
        return subjectConverter.sourceToDtoList(subjectRepository.findAll());
    }

    @Override
    public SubjectDto create(final SubjectForm form) {
        return subjectConverter.sourceToDto(subjectRepository.create(form));
    }

    @Override
    public SubjectDto update(Integer subjectId,
                             SubjectForm subjectForm) {
        subjectForm.setId(subjectId);
        return subjectConverter.sourceToDto(subjectRepository.update(subjectForm));
    }

    @Override
    public SubjectForm getFormById(final Integer subjectId) {
        return subjectConverter.sourceToForm(subjectRepository.findById(subjectId));
    }
}
