package tvz.btot.zavrsni.web.form;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamForm {
    private Integer id;
    private String name;
    private String description;
    private Integer subjectId;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private Float totalPoints;
}
