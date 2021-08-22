package tvz.btot.zavrsni.web.dto;

import lombok.*;
import tvz.btot.zavrsni.domain.ChatType;
import tvz.btot.zavrsni.domain.Question;
import tvz.btot.zavrsni.domain.Subject;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDto {
    private Integer id;
    private String name;
    private String description;
    private SubjectDto subject;
    private String dateStart;
    private String dateEnd;
    private Float totalPoints;
    private List<QuestionDto> questions;
    private Integer activeForUser;
}
