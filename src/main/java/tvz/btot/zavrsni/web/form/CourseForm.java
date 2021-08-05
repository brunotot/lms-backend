package tvz.btot.zavrsni.web.form;

import lombok.*;
import tvz.btot.zavrsni.web.dto.SubjectDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseForm {
    private Integer id;
    private String name;
    private String description;
    private List<SubjectForm> subjects;
}
