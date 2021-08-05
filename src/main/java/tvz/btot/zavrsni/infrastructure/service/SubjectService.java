package tvz.btot.zavrsni.infrastructure.service;

import tvz.btot.zavrsni.web.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getAllByCourseId(int courseId);
    List<SubjectDto> getAllByUserId(int userId);
    SubjectDto findById(Integer subjectId);
}
